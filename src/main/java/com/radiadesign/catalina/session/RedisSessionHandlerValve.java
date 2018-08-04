package main.java.com.radiadesign.catalina.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.helper.HttpConnection.Request;

import redis.clients.jedis.Response;


public class RedisSessionHandlerValve extends ValveBase {
  private final Log log = LogFactory.getLog(RedisSessionManager.class);
  private RedisSessionManager manager;

  public void setRedisSessionManager(RedisSessionManager manager) {
    this.manager = manager;
  }

  @Override
  public void invoke(Request request, Response response) throws IOException, ServletException {
    try {
      getNext().invoke(request, response);
    } finally {
      final Session session = request.getSessionInternal(false);
      storeOrRemoveSession(session);
      manager.afterRequest();
    }
  }

  private void storeOrRemoveSession(Session session) {
    try {
      if (session != null) {
        if (session.isValid()) {
          log.trace("Request with session completed, saving session " + session.getId());
          if (session.getSession() != null) {
            log.trace("HTTP Session present, saving " + session.getId());
            manager.save(session);
          } else {
            log.trace("No HTTP Session present, Not saving " + session.getId());
          }
        } else {
          log.trace("HTTP Session has been invalidated, removing :" + session.getId());
          manager.remove(session);
        }
      }
    } catch (Exception e) {
      // Do nothing.
    }
  }
}
