package http;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * Provides a protocol codec for HTTP server.
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev: 590006 $, $Date: 2007-10-30 18:44:02 +0900 (?, 30 10? 2007) $
 */
public class HttpServerProtocolCodecFactory extends
        DemuxingProtocolCodecFactory {
    public HttpServerProtocolCodecFactory() {
        super.addMessageDecoder(HttpRequestDecoder.class);
        super.addMessageEncoder(HttpResponseMessage.class, HttpResponseEncoder.class);
    }
}
