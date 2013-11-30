package eu.novait.httpchecker;

import eu.novait.httpchecker.utils.FileUrlProvider;
import eu.novait.httpchecker.utils.HttpDownloader;
import eu.novait.httpchecker.utils.NagiosOutputFormatter;
import eu.novait.httpchecker.utils.OutputFormatter;
import eu.novait.httpchecker.utils.handlers.CheckHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App {

    public static final int STATUS_OK = 0;
    public static final int STATUS_WARNING = 1;
    public static final int STATUS_CRITICAL = 2;
    public static final int STATUS_UNKNOWN = 3;

    private ArrayList<OutputFormatter> outputFormatters;
    private ArrayList<CheckHandler> checkHandlers;
    private ArrayList<String> urlsToProcess;

    public App() {
        this.outputFormatters = new ArrayList<>();
        this.checkHandlers = new ArrayList<>();
        this.urlsToProcess = new ArrayList<>();
    }

    public void addOutputFormatter(OutputFormatter outputFormatter) {
        this.outputFormatters.add(outputFormatter);
    }
    
    public void addUrlToProcess(String url){
        this.urlsToProcess.add(url);
    }

    private void processUrl(String url) {
        HttpDownloader hd = new HttpDownloader(url);
        hd.setMethod(HttpDownloader.METHOD_GET);
        hd.download();
        CheckHandler ch = new CheckHandler(
                hd.getResponseTime(),
                hd.getResponse().getStatusCode(),
                hd.getResponse().getStatusMessage(),
                hd.getResponse().getContent(),
                hd.getHost()
        );
        this.checkHandlers.add(ch);
    }
    
    public void processUrls(){
        Iterator<String> it = this.urlsToProcess.iterator();
        while(it.hasNext()){
            String url = it.next();
            this.processUrl(url);
        }
    }

    public void processOutput() {
        this.prepareOutputFormatters();
        Iterator<OutputFormatter> it = this.outputFormatters.iterator();
        while (it.hasNext()) {
            OutputFormatter of = it.next();
            of.printOutput();
        }
    }

    private void prepareOutputFormatters() {
        Iterator<OutputFormatter> it = this.outputFormatters.iterator();
        while (it.hasNext()) {
            OutputFormatter of = it.next();
            Iterator<CheckHandler> it2 = this.checkHandlers.iterator();
            while (it2.hasNext()) {
                CheckHandler ch = it2.next();
                of.addHandler(ch);
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
        NagiosOutputFormatter nof = new NagiosOutputFormatter();
        app.addOutputFormatter(nof);
        try {
            Options options = new Options();
            options.addOption(
                    OptionBuilder.withArgName("url")
                    .hasArg()
                    .withDescription("URL to site")
                    .create("u"));
            options.addOption(
                    OptionBuilder.withArgName("filename")
                    .hasArg()
                    .withDescription("URL filelist")
                    .create("f"));
            CommandLineParser parser = new BasicParser();
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("u")) {
                app.addUrlToProcess(cmd.getOptionValue("u"));
            }
            if (cmd.hasOption("f")) {
                FileUrlProvider fup = new FileUrlProvider(
                        new File(cmd.getOptionValue("f"))
                );
                Iterator<String> it = fup.getIterator();
                while(it.hasNext()){
                    String url = it.next();
                    app.addUrlToProcess(url);
                }
            }
            
            app.processUrls();
            app.processOutput();

            //HelpFormatter formatter = new HelpFormatter();
            //formatter.printHelp("HTTPChecker", options);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
