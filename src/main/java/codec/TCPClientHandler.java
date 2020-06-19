package codec;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPClientHandler extends IoHandlerAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(TCPClientHandler.class);

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        for (int i = 0; i < 3; i++) {
            SmsObject sms = new SmsObject();
            session.write(sms);
            System.out.println("****************" + i);
        }
        super.sessionOpened(session);
    }

    @Override
    public void messageReceived(IoSession ioSession, Object message) throws Exception {
        SmsObject rs = (SmsObject) message;
        System.out.println(String.valueOf(rs.getMessage()));
        ioSession.write(rs);

    }
}
