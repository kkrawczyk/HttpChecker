package eu.novait.httpchecker.utils;

import eu.novait.httpchecker.utils.handlers.BasicHttpResponseHandler;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpDownloader {

    public static final int METHOD_GET = 0;
    public static final int METHOD_POST = 1;
    public static final int METHOD_HEAD = 2;

    private String host;
    private int port;
    private int method = 0;
    private BasicHttpResponseHandler response = null;
    private long responseTime = 0;

    public HttpDownloader(String host) {
        this.setPort(80);
        this.setHost(host);
    }

    public HttpDownloader(String host, int port) {
        this.setPort(port);
        this.setHost(host);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void download() {
        this.download(null);
    }

    public void download(String filename) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        try {
            HttpRequestBase hrb = null;
            switch(this.method){
                case HttpDownloader.METHOD_POST:
                    hrb = new HttpPost(this.host);
                case HttpDownloader.METHOD_HEAD:
                    hrb = new HttpHead(this.host);
                    break;
                case HttpDownloader.METHOD_GET:
                default:
                    hrb = new HttpGet(this.host);
            }
            this.setResponse(new BasicHttpResponseHandler());
            long requestStart = System.currentTimeMillis();
            httpclient.execute(hrb, this.getResponse());
            long requestStop = System.currentTimeMillis();
            this.setResponseTime(requestStop-requestStart);
            httpclient.close();
        } catch (IOException ex) {
        } finally {
        }
    }

    /**
     * @return the method
     */
    public int getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * @return the response
     */
    public BasicHttpResponseHandler getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(BasicHttpResponseHandler response) {
        this.response = response;
    }

    /**
     * @return the responseTime
     */
    public long getResponseTime() {
        return responseTime;
    }

    /**
     * @param responseTime the responseTime to set
     */
    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
