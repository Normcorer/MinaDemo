package multichannel;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class ResultMessageEncoder implements MessageEncoder<ResultMessage> {
    @Override
    public void encode(IoSession session, ResultMessage message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(4);
        buffer.putInt(message.getResult());
        buffer.flip();
        out.write(buffer);
    }
}