package eu.novait.httpchecker.utils.handlers;

/**
 * Class to store http connection response.
 *
 * @author Krzysztof Krawczyk
 */
public class CheckHandler {

    private long time;
    private int statusCode;
    private String statusPhrase;
    private String content;
    private String url;

    /**
     * Default constructor used to initialize inner class attributes.
     *
     * @param time time in ms to get response from server
     * @param statusCode response status code
     * @param statusPhrase response status phrase
     * @param content response content
     * @param url requested url
     */
    public CheckHandler(long time, int statusCode, String statusPhrase, String content, String url) {
        this.time = time;
        this.statusCode = statusCode;
        this.statusPhrase = statusPhrase;
        this.content = content;
        this.url = url;
    }

    /**
     * Returns response content
     *
     * @return response content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Returns time in ms to get response from server
     *
     * @return time in ms to get response from server
     */
    public long getTime() {
        return time;
    }

    /**
     * Returns response status code
     *
     * @return response status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Returns response status phrase
     *
     * @return response status phrase
     */
    public String getStatusPhrase() {
        return statusPhrase;
    }

    /**
     * Returns content
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns processed url
     *
     * @return processed url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
