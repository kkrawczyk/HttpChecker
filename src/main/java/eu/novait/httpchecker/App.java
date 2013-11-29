package eu.novait.httpchecker;

import eu.novait.httpchecker.utils.HttpDownloader;

/**
 * Hello world!
 *
 */
public class App {

    public static final int STATUS_OK = 0;
    public static final int STATUS_WARNING = 1;
    public static final int STATUS_CRITICAL = 2;
    public static final int STATUS_UNKNOWN = 3;

    public static void main(String[] args) {
        HttpDownloader hd = new HttpDownloader("http://woiib.org.pl");
        hd.setMethod(HttpDownloader.METHOD_HEAD);
        hd.download();

    }
}
