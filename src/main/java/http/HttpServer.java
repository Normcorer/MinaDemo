package http;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class HttpServer {

    public static final String VERSION_STRING = "HM v1.0";

    public static void main(String[] args) throws IOException {
        // Create an acceptor
        NioSocketAcceptor acceptor = new NioSocketAcceptor();

        // Create a service configuration
        acceptor.getFilterChain().addLast(
                "protocolFilter",
                new ProtocolCodecFilter(
                        new HttpServerProtocolCodecFactory()));
        if (PublicConstants.httpPacklog.equalsIgnoreCase("yes")) {
            acceptor.getFilterChain()
                    .addLast("logger", new LoggingFilter());
        }
        acceptor.getFilterChain().addLast("executor", new ExecutorFilter());
        acceptor.setReuseAddress(true);
        acceptor.getSessionConfig().setTcpNoDelay(true);
        acceptor.getSessionConfig().setSoLinger(0);
        acceptor.getSessionConfig().setKeepAlive(true);
        acceptor.setHandler(new HttpServerHandler());
        acceptor.bind(new InetSocketAddress(8888));
    }
}
