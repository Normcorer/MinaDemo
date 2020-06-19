package multichannel;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public interface SmsMessageEncoder<T> extends MessageEncoder<T> {
    void encode(IoSession session, T message, ProtocolEncoderOutput out) throws Exception;
}
