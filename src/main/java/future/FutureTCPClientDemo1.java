package future;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class FutureTCPClientDemo1 {
    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));

        connector.setHandler(new TCPClientHandler("你好！\r\n 大家好！"));
        ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 8888));


        // 等待是否连接成功，相当于是转异步执行为同步执行
        future.awaitUninterruptibly();
        // 连接成功后获取会话对象。如果没有上面的等待，由于connect()方法是异步的，session可能无法被获取
        IoSession session = future.getSession();
        System.out.println(session);
    }
}
