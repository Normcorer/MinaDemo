package simplecodec;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPServerHandler extends IoHandlerAdapter {
    private final static Logger log = LoggerFactory.getLogger(TCPServerHandler.class);

    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        ioSession.close(true);
    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

    }

    @Override
    public void messageReceived(IoSession ioSession, Object message) throws Exception {
        SmsObject sm = (SmsObject) message;
        ioSession.write(sm);
        System.out.println(sm.getMessage());
    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {

    }
}
