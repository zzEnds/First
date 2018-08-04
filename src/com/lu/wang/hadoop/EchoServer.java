package com.lu.wang.hadoop;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 
 * 采用NIO编写的服务器
 * 接收客户端发送过来的字符串，不经任何处理，直接再次返回给客户端
 * 初始化、监听、连接、读、写
 *
 */
public class EchoServer {
	
	String bindAddress = "";
	int port;
	int backlogLength;
	int numRead;
	InetSocketAddress address;
	ServerSocketChannel acceptChannel;
	Selector selector;
	
	/**
	 * @Description: 初始化
	 */
	public Selector initSelector() throws IOException{
		
		address = new InetSocketAddress(bindAddress, port);
		
		//创建一个ServerSocketChannel并将其设置为非阻塞模式
		acceptChannel = ServerSocketChannel.open();
		acceptChannel.configureBlocking(false);
		
		//将sever socket 绑定到address地址上，并设置监听队列长度
		acceptChannel.socket().bind(address, backlogLength);
		selector = Selector.open();
		
		//向Selector注册ServerSocketChannel，注册事件为SelectionKey.OP_ACCEPT
		acceptChannel.register(selector, SelectionKey.OP_ACCEPT);
		return selector;
		
	}
	
	
	/**
	 * @Description: 监听客户端事件，并调用对应事件处理函数
	 */
	public void run() {
		
		while(true) {
			
			SelectionKey key = null;
			
			try {
				
				selector.select();//处于阻塞状态，直到有新事件发生
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				
				while(iter.hasNext()) {
					
					key = iter.next();
					iter.remove();
					
					if(!key.isValid()) {
						continue;
					}
					if(key.isAcceptable()) {
						doAccept(key);//新客户端要求建立连接
					} else if(key.isReadable()) {
						receive(key);//从客户端接收数据
					} else if(key.isWritable()) {
						send(key);//向客户端发送数据
					}
					
					key = null;
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	/**
	 * @Description: 接受新客户端连接请求
	 */
	public void doAccept(SelectionKey key) throws IOException {
		
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
		
		//接受连接请求，并将其设置为非阻塞模式
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		
		//将新的SocketChannel注册到Selector中，一旦有需要读或者写的数据，就会通知相应的程序
		SelectionKey clientKey = socketChannel.register(this.selector, SelectionKey.OP_READ);
		ByteBuffer buffer = ByteBuffer.allocate(8192);
		
		//关联一个缓冲区
		clientKey.attach(buffer);
	}
	
	/**
	 * @Description: 处理读数据请求
	 */
	public void receive(SelectionKey key) throws IOException {
		
		SocketChannel socketChannel = (SocketChannel) key.channel();
		ByteBuffer readBuffer = (ByteBuffer) key.attachment();
		socketChannel.read(readBuffer);
		
		if(numRead > 0) {
			readBuffer.flip();
			key.interestOps(SelectionKey.OP_WRITE);//切换至OP_WRITE
		}
		
	}
	
	/**
	 * @Description: 处理写数据请求
	 */
	public void send(SelectionKey key) throws IOException {
		
		SocketChannel socketChannel = (SocketChannel) key.channel();
		ByteBuffer writeBuffer = (ByteBuffer) key.attachment();
		socketChannel.write(writeBuffer);
		
		//写完成后，切换至OP_READ
		if(writeBuffer.remaining() == 0) {
			writeBuffer.clear();
			key.interestOps(SelectionKey.OP_READ);
		}
		
	}

}
