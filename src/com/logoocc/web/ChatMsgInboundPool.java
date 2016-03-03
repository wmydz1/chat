package com.logoocc.web;

import org.apache.catalina.websocket.MessageInbound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samchen on 3/3/16.
 * 记录客户端连接信息
 */
public class ChatMsgInboundPool {

    public static List<MessageInbound> msgList = new ArrayList<MessageInbound>();

}
