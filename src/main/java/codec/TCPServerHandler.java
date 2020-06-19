package codec;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;


public class TCPServerHandler extends IoHandlerAdapter {
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpen");
        super.sessionOpened(session);
    }

    @Override
    public void messageReceived(IoSession ioSession, Object message) throws Exception {
        SmsObject sm = (SmsObject) message;
        System.out.println(sm.getReceiver());
        System.out.println(sm.getSender());
        System.out.println(sm.getMessage());
        ioSession.write(sm);
    }
}
