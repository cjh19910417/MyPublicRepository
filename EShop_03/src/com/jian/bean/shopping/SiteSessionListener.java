package com.jian.bean.shopping;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * 监听session的创建和销毁
 * 创建时,加入到map里面
 * 销毁时,从map里移除
 * @author JOJO
 * @date 2012-8-22
 */
public class SiteSessionListener implements HttpSessionListener
{
    /**
     * sessions:    用来存储网站所有的session
     */
    private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent)
    {
        sessions.put(sessionEvent.getSession().getId(), sessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent)
    {
        sessions.remove(sessionEvent.getSession().getId());
    }
    
    public static HttpSession getSession(String sessionId)
    {
        return sessions.get(sessionId);
    }

}
