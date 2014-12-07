package eu.novait.httpchecker.utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Base class to provide urls to application.
 *
 * @author Krzysztof Krawczyk
 */
public class UrlProvider {

    private ArrayList<String> urlList;

    /**
     * Default constructor. It sets up inner attributes.
     */
    public UrlProvider() {
        this.urlList = new ArrayList<>();
    }

    /**
     * Returns iterator on inner urls list
     *
     * @return string iterator on inner urls list
     */
    public Iterator<String> getIterator() {
        return this.urlList.iterator();
    }

    /**
     * Adding url to inner list
     *
     * @param url url to add
     */
    public void addUrl(String url) {
        this.urlList.add(url);
    }
}
