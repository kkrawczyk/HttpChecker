/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.novait.httpchecker;

import eu.novait.httpchecker.utils.HttpDownloader;
import junit.framework.TestCase;
import org.powermock.api.mockito.PowerMockito;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author kkrawczyk
 */
public class DownloaderUnitTest extends TestCase {

    private URL url;
    private HttpURLConnection connection;

    public DownloaderUnitTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.url = PowerMockito.mock(URL.class);
        this.connection = PowerMockito.mock(HttpURLConnection.class);
        PowerMockito.whenNew(URL.class).
                withArguments("http", "localhost", 80, "GET").thenReturn(this.url);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testHttpDownloaderExists() {
        eu.novait.httpchecker.utils.HttpDownloader downloader = new eu.novait.httpchecker.utils.HttpDownloader("dummy_host");
        if (!(downloader instanceof eu.novait.httpchecker.utils.HttpDownloader)) {
            fail("Classtype missmatchs");
        }
    }

    public void testHttpDownloaderCounstructor() {
        eu.novait.httpchecker.utils.HttpDownloader downloader = new HttpDownloader("dummyhost");
        if (!(downloader instanceof eu.novait.httpchecker.utils.HttpDownloader)) {
            fail("Classtype missmatchs");
        }
        eu.novait.httpchecker.utils.HttpDownloader downloader2 = new HttpDownloader("dummyhost", 80);
        if (!(downloader2 instanceof eu.novait.httpchecker.utils.HttpDownloader)) {
            fail("Classtype missmatchs");
        }
    }

    public void testHttpDownloaderGettersAndSetters() {
        String host = "http://www.wp.pl/";
        String host2 = "http://onet.pl";
        int port = 8080;
        int port2 = 8181;
        eu.novait.httpchecker.utils.HttpDownloader downloader = new HttpDownloader(host);
        assertEquals(downloader.getHost(), host);
        assertEquals(downloader.getPort(), 80);
        downloader.setHost(host2);
        assertEquals(downloader.getHost(), host2);

        eu.novait.httpchecker.utils.HttpDownloader downloader2 = new HttpDownloader(host, port);
        assertEquals(downloader2.getHost(), host);
        assertEquals(downloader2.getPort(), port);
        downloader2.setHost(host2);
        downloader2.setPort(port2);
        assertEquals(downloader2.getHost(), host2);
        assertEquals(downloader2.getPort(), port2);
    }

    public void testIfDownloadMethodExists() {
        Method download = null;
        Method download2 = null;
        try {
            download = HttpDownloader.class.getMethod("download", (Class<?>[]) null);
            download2 = HttpDownloader.class.getMethod("download", new Class[]{String.class});
        } catch (NoSuchMethodException e) {
        }
        assertNotNull(download);
        assertNotNull(download2);
    }
    
}
