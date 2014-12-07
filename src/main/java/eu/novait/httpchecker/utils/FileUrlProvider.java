package eu.novait.httpchecker.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to load url list from file
 *
 * @author Krzysztof Krawczyk
 */
public class FileUrlProvider extends UrlProvider {

    /**
     * Default constructor.
     *
     * Loads urls from file defined in f param. Each URL has to be in separate
     * line
     *
     * @param f Source file
     * @throws FileNotFoundException throwed if f file doesn't exists
     * @throws IOException throwed on any exception during file read
     */
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
