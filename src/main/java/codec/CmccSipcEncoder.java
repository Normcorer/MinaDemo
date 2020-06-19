package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class CmccSipcEncoder extends ProtocolEncoderAdapter {
    private final Charset charset;

    public CmccSipcEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message,
                       ProtocolEncoderOutput out) throws Exception {
        SmsObject sms = (SmsObject) message;
        CharsetEncoder ce = charset.newEncoder();
        String statusLine = "M sip:wap.fetion.com.cn SIP-C/2.0";
        String sender = "15801012253";
        String receiver = "15866332698";
        String smsContent = "你好！Hello World!";
//        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
//        buffer.putString(statusLine + "/r/n", ce);
//        buffer.putString("S: " + sender + "/r/n", ce);
//        buffer.putString("R: " + receiver + "/r/n", ce);
//        buffer.flip();
//        out.write(buffer);
//        IoBuffer buffer2 = IoBuffer.allocate(100).setAutoExpand(true);
//        buffer2.putString("L: " + (smsContent.getBytes(charset).length)
//                + "/r/n",ce);
//        buffer2.putString(smsContent, ce);
//        buffer2.putString(statusLine + "/r/n", ce);
//        buffer2.flip();
//        out.write(buffer2);
        IoBuffer buffer3 = IoBuffer.allocate(100).setAutoExpand(true);
        buffer3.putString("S: " + sender + "/r/n", ce);
        buffer3.putString("R: " + receiver + "/r/n", ce);
        buffer3.putString("L: " + (smsContent.getBytes(charset).length)
                + "/r/n",ce);
        buffer3.putString(smsContent, ce);
        buffer3.putString(statusLine + "/r/n", ce);
        buffer3.flip();
        out.write(buffer3);
    }
}
