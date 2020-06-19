package simpletcpserver;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class TCPServerHandler extends IoHandlerAdapter {
    private final static Logger log = LoggerFactory.getLogger(TCPServerHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        System.out.println("The message received is [" + str + "]");
        if (str.endsWith("quit")) {
            session.close(true);
            return;
        }
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
}
