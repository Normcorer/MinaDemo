package multichannel;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {
    private final static Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(true);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        SendMessage sm = (SendMessage) message;
        System.out.println("The message received is [ " + sm.getI() + " " + sm.getSymbol() + " " + sm.getJ() + " ]");
        ResultMessage rm = new ResultMessage();
        rm.setResult(sm.getI() + sm.getJ());
        session.write(rm);
    }
}
