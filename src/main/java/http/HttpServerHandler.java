package http;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class HttpServerHandler extends IoHandlerAdapter {
    private final static Logger log = LoggerFactory.getLogger(HttpServerHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        HttpResponseMessage response = new HttpResponseMessage();
        response.setContentType("text/plain");
        response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);

        HttpRequestMessage reqMsg = (HttpRequestMessage) message;
        String path = reqMsg.getHeader("Context")[0];
        String postData = reqMsg.getHeader("Content")[0];
        //=======================json格式返回======//
        if ("getReport".equalsIgnoreCase(path)) {
            String result = "getReport";
            response.appendBody(result);
            session.write(response);
            return;
        }
        String result = "hello";
        response.appendBody(result);
        session.write(response);
    }

    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        System.out.println("server session created");
        System.out.println(((InetSocketAddress) ioSession.getRemoteAddress())
                .getAddress().getHostAddress());
        super.sessionCreated(ioSession);
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        System.out.println("server session Opend");
        super.sessionOpened(ioSession);
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        System.out.println("server session Closed");
        super.sessionClosed(ioSession);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(false);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        HttpResponseMessage response = (HttpResponseMessage) message;
        System.out.println("HTTP RESP: " + response);
    }
}
