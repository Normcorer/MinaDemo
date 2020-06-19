package http;

import java.util.Map;
import java.util.Map.Entry;

/**
 * A HTTP request message.
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev: 576402 $, $Date: 2007-09-17 21:37:27 +0900 (?, 17  9? 2007) $
 */
public class HttpRequestMessage {
    
    private Map<String, String[]> headers = null;

    public void setHeaders(Map<String, String[]> headers) {
        this.headers = headers;
    }

    public Map<String, String[]> getHeaders() {
        return headers;
    }

    public String getContext() {
        String[] context = headers.get("Context");
        return context == null ? "" : context[0];
    }

    public String getParameter(String name) {
        String[] param = headers.get("@".concat(name));
        if(param == null || param.length == 0){
        	return "";
        }else {
            return param[0];
        }
    }

    public String[] getParameters(String name) {
        String[] param = headers.get("@".concat(name));
        return param == null ? new String[] {} : param;
    }

    public String[] getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (Entry<String, String[]> e: headers.entrySet()) {
            str.append(e.getKey() + " : "
                    + arrayToString(e.getValue(), ',') + "\n");
        }
        return str.toString();
    }

    public static String arrayToString(String[] s, char sep) {
        if (s == null || s.length == 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        if (s != null) {
            for (int i = 0; i < s.length; i++) {
                if (i > 0) {
                    buf.append(sep);
                }
                buf.append(s[i]);
            }
        }
        return buf.toString();
    }
}
