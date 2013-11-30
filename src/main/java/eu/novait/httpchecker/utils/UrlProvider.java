package eu.novait.httpchecker.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class UrlProvider {
    private ArrayList<String> urlList;
    
    public UrlProvider(){
        this.urlList = new ArrayList<>();
    }
    
    public Iterator<String> getIterator(){
        return this.urlList.iterator();
    }
    
    public void addUrl(String url){
        this.urlList.add(url);
    }
}
