package eu.novait.httpchecker.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUrlProvider extends UrlProvider {

    public FileUrlProvider(File f) throws FileNotFoundException, IOException {
        super();
        if (!f.exists()) {
            throw new FileNotFoundException(f.getAbsolutePath());
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null) {
            String url = line.trim();
            if (url.length() > 0 && url.charAt(0) != '#') {
                this.addUrl(url);
            }
        }
        br.close();
    }
}
