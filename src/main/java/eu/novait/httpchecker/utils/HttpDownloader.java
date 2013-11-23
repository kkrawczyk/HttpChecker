package eu.novait.httpchecker.utils;

/**
 * Created with IntelliJ IDEA.
 * User: kkrawczyk
 * Date: 23.11.13
 * Time: 08:09
 * To change this template use File | Settings | File Templates.
 */
public class HttpDownloader {

    private String host;
    private int port;

    public HttpDownloader(String host){
        this.setPort(80);
        this.setHost(host);
    }

    public HttpDownloader(String host, int port){
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

    public void download(){
        this.download(null);
    }
    public void download(String filename){

    }
}
