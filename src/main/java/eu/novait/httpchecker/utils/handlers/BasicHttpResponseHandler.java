package eu.novait.httpchecker.utils.handlers;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

/**
 * Class representing response of HttpClient class.
 *
 * @author Krzysztof Krawczyk
 */
public class BasicHttpResponseHandler implements ResponseHandler {

    private int statusCode;
    private String statusMessage;
    private String content;

    /**
     * Handles HttpClient response.
     *
     * @param hr HttpClient response
     * @return EntityUtils as byte array
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Override
    public Object handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
        this.statusCode = hr.getStatusLine().getStatusCode();
        this.statusMessage = hr.getStatusLine().getReasonPhrase();
        HttpEntity he = hr.getEntity();
        Object ret = he != null ? EntityUtils.toByteArray(he) : null;
        if (ret != null && ret instanceof byte[]) {
            this.content = new String((byte[]) ret);
        }
        return ret;
    }

    /**
     * Returns response status code
     *
     * @return http response status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Returns response status phrase
     *
     * @return http response status phrase
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Returns response content (whitout headers).
     *
     * @return response content (without headers).
     */
    public String getContent() {
        return content;
    }

}
