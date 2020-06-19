package http;

import org.apache.mina.core.buffer.IoBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * A HTTP response message.
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev: 581234 $, $Date: 2007-10-02 22:39:48 +0900 (?, 02 10? 2007) $
 */
public class HttpResponseMessage {
    /** HTTP response codes */
    public static final int HTTP_STATUS_SUCCESS = 200;

    public static final int HTTP_STATUS_NOT_FOUND = 404;

    /** Map<String, String> */
    private final Map<String, String> headers = new HashMap<String, String>();

    /** Storage for body of HTTP response. */
    private final ByteArrayOutputStream body = new ByteArrayOutputStream(1024);

    private int responseCode = HTTP_STATUS_SUCCESS;

    public HttpResponseMessage() {
        headers.put("Server", "HttpServer (" + HttpServer.VERSION_STRING + ')');
        headers.put("Cache-Control", "private");
        headers.put("Content-Type", "text/xml; charset=utf-8");
        headers.put("Connection", "keep-alive");
        headers.put("Keep-Alive", "timeout=60, max=100");
        headers.put("Date", new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date()));
        headers.put("Last-Modified", new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date()));
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setContentType(String contentType) {
        headers.put("Content-Type", contentType);
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void appendBody(byte[] b) {
        try {
            body.write(b);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void appendBody(String s) {
        try {
            body.write(s.getBytes("utf-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public IoBuffer getBody() {
        return IoBuffer.wrap(body.toByteArray());
    }

    public int getBodyLength() {
        return body.size();
    }

    @Override
    public String toString(){
		try {
			return new String(body.toByteArray(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
    }
}
