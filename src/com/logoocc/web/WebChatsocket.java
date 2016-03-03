package com.logoocc.web;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by samchen on 3/3/16.
 */
@WebServlet(urlPatterns = "/webSocket")
public class WebChatsocket extends WebSocketServlet {

    @Override
    protected StreamInbound createWebSocketInbound(String s, HttpServletRequest httpServletRequest) {

        return new ChatMsgInbound();
    }
}
