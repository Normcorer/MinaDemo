package multichannel;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class MathProtocolCodecFactory extends DemuxingProtocolCodecFactory {
    public MathProtocolCodecFactory(boolean server) {
        if (server) {
            super.addMessageEncoder(ResultMessage.class, ResultMessageEncoder.class);
            super.addMessageDecoder(SendMessageDecoderPositive.class);
            super.addMessageDecoder(SendSmsMessageDecoderNegative.class);
        } else {
            super.addMessageEncoder(SendMessage.class, SendSmsMessageEncoder.class);
            super.addMessageDecoder(ResultMessageDecoder.class);
        }
    }
}