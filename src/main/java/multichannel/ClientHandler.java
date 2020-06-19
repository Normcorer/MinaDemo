package multichannel;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        SendMessage sm = new SendMessage();
        sm.setI(100);
        sm.setJ(99);
        sm.setSymbol('+');
        session.write(sm);
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        ResultMessage rs = (ResultMessage) message;
        System.out.println(String.valueOf(rs.getResult()));
    }
}
