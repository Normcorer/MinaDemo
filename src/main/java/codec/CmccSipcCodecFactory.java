package codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;

import java.nio.charset.Charset;

public class CmccSipcCodecFactory implements ProtocolCodecFactory {
    private final CmccSipcEncoder encoder;
    private final CmccSipcDecoder decoder;

    public CmccSipcCodecFactory() {
        this(Charset.defaultCharset());
    }

    public CmccSipcCodecFactory(Charset charSet) {
        this.encoder = new CmccSipcEncoder(charSet);
        this.decoder = new CmccSipcDecoder(charSet);
    }

    @Override
    public CmccSipcDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }

    @Override
    public CmccSipcEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }
}
