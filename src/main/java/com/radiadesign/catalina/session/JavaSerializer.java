package main.java.com.radiadesign.catalina.session;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpSession;


public class JavaSerializer implements Serializer {
  private ClassLoader loader;

  @Override
  public void setClassLoader(ClassLoader loader) {
    this.loader = loader;
  }

  @Override
  public byte[] serializeFrom(HttpSession session) throws IOException {

    RedisSession redisSession = (RedisSession) session;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(bos));
    oos.writeLong(redisSession.getCreationTime());
    redisSession.writeObjectData(oos);

    oos.close();

    return bos.toByteArray();
  }

  @Override
  public HttpSession deserializeInto(byte[] data, HttpSession session) throws IOException, ClassNotFoundException {

    RedisSession redisSession = (RedisSession) session;

    BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(data));

    ObjectInputStream ois = new CustomObjectInputStream(bis, loader);
    redisSession.setCreationTime(ois.readLong());
    redisSession.readObjectData(ois);

    return session;
  }
}
