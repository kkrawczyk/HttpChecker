package eu.novait.httpchecker.utils;

import eu.novait.httpchecker.utils.handlers.BasicHttpResponseHandler;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Class to manage http connections.
 *
 * @author Krzysztof Krawczyk
 */
public class HttpDownloader {

    public static final int METHOD_GET = 0;
    public static final int METHOD_POST = 1;
    public static final int METHOD_HEAD = 2;

    private String host;
    private int port;
    private int method = 0;
    private BasicHttpResponseHandler response = null;
    private long responseTime = 0;

    /**
     * Constructor initializing host attribute
     *
     * @param host url to process
     */
    public HttpDownloader(String host) {
        this.setPort(80);
        this.setHost(host);
    }

    /**
     * Constructor initializing host and port
     *
     * @param host url to process
     * @param port url port to process
     * @deprecated
     */
    public HttpDownloader(String host, int port) {
        this.setPort(port);
        this.setHost(host);
    }

    /**
     * Returns url to process
     *
     * @return url to process
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets url to process
     *
     * @param host url to process
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Returns url port
     *
     * @return port
     * @deprecated
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets port
     *
     * @param port
     * @deprecated
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Downloads content under url
     */
    public void download() {
        this.download(null);
    }

    /**
     * Downloads content under url and store it in a file
     *
     * @param filename output filename
     */
    public void download(String filename) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpRequestBase hrb = null;
            switch (this.method) {
                case HttpDownloader.METHOD_POST:
                    hrb = new HttpPost(this.host);
                case HttpDownloader.METHOD_HEAD:
                    hrb = new HttpHead(this.host);
                    break;
                case HttpDownloader.METHOD_GET:
                default:
                    hrb = new HttpGet(this.host);
            }
            this.response = new BasicHttpResponseHandler();
            long requestStart = System.currentTimeMillis();
            httpclient.execute(hrb, this.getResponse());
            long requestStop = System.currentTimeMillis();
            this.responseTime = requestStop - requestStart;
            httpclient.close();
        } catch (IOException ex) {
        } finally {
        }
    }

    /**
     * Returns method attribute
     *
     * @return method attribute
     */
    public int getMethod() {
        return method;
    }

    /**
     * Sets method attribute
     *
     * @param method method to set
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * Returns response
     *
     * @return response
     */
    public BasicHttpResponseHandler getResponse() {
        return response;
    }

    /**
     * Returns url response time.
     *
     * @return url response time
     */
    public long getResponseTime() {
        return responseTime;
    }
}
