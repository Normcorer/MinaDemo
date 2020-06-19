package simplecodec;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPClientHandler extends IoHandlerAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(TCPClientHandler.class);

    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        SmsObject sm = new SmsObject();
        sm.setMessage("hemaceshi");
        sm.setReceiver("1799999999");
        sm.setSender("17888888888");
        ioSession.write(sm);
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

    }

    @Override
    public void messageReceived(IoSession ioSession, Object message) throws Exception {
        SmsObject rs = (SmsObject) message;
        System.out.println(String.valueOf(rs.getMessage()));
    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {

    }
}
