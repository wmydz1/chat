package com.logoocc.web;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samchen on 3/3/16.
 * 处理每一个客户端websocket的连接和关闭,以及发送消息
 */
public class ChatMsgInbound extends MessageInbound {

    @Override
    protected void onOpen(WsOutbound outbound) {
        super.onOpen(outbound);
        ChatMsgInboundPool.msgList.add(this);
    }

    @Override
    protected void onClose(int status) {
        super.onClose(status);
        ChatMsgInboundPool.msgList.remove(this);
    }

    @Override
    protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {
        for (MessageInbound msgInbound : ChatMsgInboundPool.msgList) {
            WsOutbound out = msgInbound.getWsOutbound();
            out.writeBinaryMessage(byteBuffer);
            out.flush();
        }
    }

    @Override
    protected void onTextMessage(CharBuffer charBuffer) throws IOException {

        // 循环发个所有客户端
        for (MessageInbound msgInbound : ChatMsgInboundPool.msgList) {
            WsOutbound out = msgInbound.getWsOutbound();
            out.writeTextMessage(CharBuffer.wrap(charBuffer));
            out.flush();
        }
    }
}
