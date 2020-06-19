package multichannel;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

public interface SmsMessageDecoder extends MessageDecoder {
    static MessageDecoderResult OK = MessageDecoderResult.OK;
    static MessageDecoderResult NEED_DATA = MessageDecoderResult.NEED_DATA;
    static MessageDecoderResult NOT_OK = MessageDecoderResult.NOT_OK;
    @Override
    MessageDecoderResult decodable(IoSession session, IoBuffer in);
    MessageDecoderResult decode(IoSession session, IoBuffer in,
                                ProtocolDecoderOutput out) throws Exception;
    void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception;
}
