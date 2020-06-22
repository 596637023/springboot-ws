package com.dx.ws.server;

import com.dx.ws.tool.SerializeUtil;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;

/**
 * @author dx
 * @version 1.0
 * @date 2020/6/22 0022 10:08
 */
@ServerEndpoint("/test/{msg}")
@Component
@Slf4j
public class WsServer {

    @OnOpen
    public void onOpen(Session session,@PathParam("msg") String msg) {
        log.info("{}连接上了说:{}", session.getAsyncRemote(),msg);
        String reMsg="hello,客户端";
        session.getAsyncRemote().sendText(reMsg);
    }

    @OnClose
    public void onClose(Session session){
        log.info("{}已下线:",session.getAsyncRemote());
    }

    @OnMessage
    public void onMessage(String msg,Session session){
        log.info("{}发送消息:{}",session.getAsyncRemote(),msg);
    }

    @OnError
    public void onError (Session session,Throwable error){
        log.error("{}出现异常:",session.getAsyncRemote(),error);
    }
}
