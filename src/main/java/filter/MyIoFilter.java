package filter;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.FilterEvent;

public class MyIoFilter implements IoFilter {
    @Override
    public void destroy() throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%�stroy");
    }

    @Override
    public void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%exceptionCaught");
        nextFilter.exceptionCaught(session, cause);
    }

    @Override
    public void inputClosed(NextFilter nextFilter, IoSession ioSession) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%inputClosed");
        nextFilter.inputClosed(ioSession);
    }

    @Override
    public void filterClose(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%filterClose");
        nextFilter.filterClose(session);
    }

    @Override
    public void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%filterWrite");
        nextFilter.filterWrite(session, writeRequest);
    }

    @Override
    public void event(NextFilter nextFilter, IoSession ioSession, FilterEvent filterEvent) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%event");
        nextFilter.event(ioSession, filterEvent);
    }

    @Override
    public void init() throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%init");
    }

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%messageReceived");
        nextFilter.messageReceived(session, message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%messageSent");
        nextFilter.messageSent(session, writeRequest);
    }

    @Override
    public void onPostAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%onPostAdd");
    }

    @Override
    public void onPostRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%onPostRemove");
    }

    @Override
    public void onPreAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%onPreAdd");
    }

    @Override
    public void onPreRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%onPreRemove");
    }

    @Override
    public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%sessionClosed");
        nextFilter.sessionClosed(session);
    }

    @Override
    public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%sessionCreated");
        nextFilter.sessionCreated(session);
    }

    @Override
    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%sessionIdle");
        nextFilter.sessionIdle(session, status);
    }

    @Override
    public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%sessionOpened");
        nextFilter.sessionOpened(session);
    }
}
