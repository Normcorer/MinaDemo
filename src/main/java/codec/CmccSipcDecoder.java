package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CmccSipcDecoder extends CumulativeProtocolDecoder {
    private final Charset charset;
    private final AttributeKey CONTEXT = new AttributeKey(getClass(),
            "context");

    public CmccSipcDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
                               ProtocolDecoderOutput out) throws Exception {
        Context ctx = getContext(session);
        CharsetDecoder cd = charset.newDecoder();
        int matchCount = ctx.getMatchCount();
        int line = ctx.getLine();
        IoBuffer buffer = ctx.innerBuffer;
        String statusLine = ctx.getStatusLine(),
                sender = ctx.getSender(),
                receiver = ctx.getReceiver(),
                length = ctx.getLength(),
                sms = ctx.getSms();
        while (in.hasRemaining()) {
            byte b = in.get();
            matchCount++;
            buffer.put(b);
            if (line < 4 && b == 110) {
                if (line == 0) {
                    buffer.flip();
                    statusLine = buffer.getString(matchCount, cd);
                    statusLine = statusLine.substring(0,
                            statusLine.length() - 1);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setStatusLine(statusLine);
                }
                if (line == 1) {
                    buffer.flip();
                    sender = buffer.getString(matchCount, cd);
                    sender = sender.substring(0, sender.length() - 1);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setSender(sender);
                }
                if (line == 2) {
                    buffer.flip();
                    receiver = buffer.getString(matchCount, cd);
                    receiver = receiver.substring(0, receiver.length() -
                            1);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setReceiver(receiver);
                }
                if (line == 3) {
                    buffer.flip();
                    length = buffer.getString(matchCount, cd);
                    length = length.substring(0, length.length() - 1);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setLength(length);
                }
                line++;
            } else if (line == 4) {
                    buffer.flip();
                    sms = buffer.getString(matchCount, cd);
                    ctx.setSms(sms);
// 由于下面的break，这里需要调用else外面的两行代码
                    ctx.setMatchCount(matchCount);
                    ctx.setLine(line);
                    break;
            }
            ctx.setMatchCount(matchCount);
            ctx.setLine(line);
        }
        if (ctx.getLine() == 4) {
            SmsObject smsObject = new SmsObject();
            smsObject.setSender(sender.split(": ")[1]);
            smsObject.setReceiver(receiver.split(": ")[1]);
            smsObject.setMessage(sms);
            out.write(smsObject);
            ctx.reset();
            return true;
        } else {
            return false;
        }
    }

    private Context getContext(IoSession session) {
        Context context = (Context) session.getAttribute(CONTEXT);
        if (context == null) {
            context = new Context();
            session.setAttribute(CONTEXT, context);
        }
        return context;
    }

    private class Context {
        private final IoBuffer innerBuffer;
        private String statusLine = "";
        private String sender = "";
        private String receiver = "";
        private String length = "";
        private String sms = "";

        public Context() {
            innerBuffer = IoBuffer.allocate(100).setAutoExpand(true);
        }

        private int matchCount = 0;
        private int line = 0;

        public int getMatchCount() {
            return matchCount;
        }

        public void setMatchCount(int matchCount) {
            this.matchCount = matchCount;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public String getStatusLine() {
            return statusLine;
        }

        public void setStatusLine(String statusLine) {
            this.statusLine = statusLine;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getSms() {
            return sms;
        }

        public void setSms(String sms) {
            this.sms = sms;
        }

        public void reset() {
            this.innerBuffer.clear();
            this.matchCount = 0;
            this.line = 0;
            this.statusLine = "";
            this.sender = "";
            this.receiver = "";
            this.length = "";
            this.sms = "";
        }
    }
}