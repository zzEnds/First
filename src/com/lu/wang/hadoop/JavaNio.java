package com.lu.wang.hadoop;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class JavaNio {
	
	static int NIO_BUFFER_LIMIT = 8 * 1024;//chunk大小：8KB
	
	//将buffer中的数据写入channel中，其中channel处于非阻塞模式
	int channelWrite(WritableByteChannel channel, ByteBuffer buffer) throws IOException {
		
		//如果缓冲区数据小于chunk大小，则直接写入channel中，否则以chunk为单位写入
		return (buffer.remaining() <= NIO_BUFFER_LIMIT) ? channel.write(buffer) : channelIO(null, channel, buffer);
		
	}
	
	private static int channelIO(
			ReadableByteChannel readCh, 
			WritableByteChannel writeCh, 
			ByteBuffer buf) throws IOException {
		
		int originalLimit = buf.limit();//缓冲区的当前存放数据的终点，不能对超过limit的区域进行读写
		int initialRemaining = buf.remaining();//获取剩余空间，大小为limit-position
		int ret = 0;
		
		while(buf.remaining() > 0) {
			
			try {
				
				int ioSize = Math.min(buf.remaining(), NIO_BUFFER_LIMIT);
				buf.limit(buf.position() + ioSize);
				ret = (readCh == null) ? writeCh.write(buf) : readCh.read(buf);
				if(ret < ioSize) {
					break;
				}
				
			} finally {
				buf.limit(originalLimit);
			}
			
		}
		
		int nBytes = initialRemaining - buf.remaining();
		
		return (nBytes > 0) ? nBytes : ret;
		
	}

}
