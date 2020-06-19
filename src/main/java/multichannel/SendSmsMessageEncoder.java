package multichannel;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class SendSmsMessageEncoder implements SmsMessageEncoder<SendMessage> {
    @Override
    public void encode(IoSession session, SendMessage message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(10);
        buffer.putChar(message.getSymbol());
        buffer.putInt(message.getI());
        buffer.putInt(message.getJ());
        buffer.flip();
        out.write(buffer);
    }
}
