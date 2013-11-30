package eu.novait.httpchecker.utils.handlers;

public class CheckHandler {
    private long time;
    private int statusCode;
    private String statusPhrase;
    private String content;
    private String url;
    
    public CheckHandler(long time, int statusCode, String statusPhrase, String content,String url){
        this.time = time;
        this.statusCode = statusCode;
        this.statusPhrase = statusPhrase;
        this.content = content;
        this.url = url;
    }
    
    public String getContent(){
        return this.content;
    }

    public long getTime() {
        return time;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusPhrase() {
        return statusPhrase;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
