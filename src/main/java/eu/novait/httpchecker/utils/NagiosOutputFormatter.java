package eu.novait.httpchecker.utils;

import eu.novait.httpchecker.utils.handlers.CheckHandler;
import java.util.Iterator;

/**
 * Output formater to returns response in nagios format. It can be used as a
 * nagios plugin.
 *
 * @author Krzysztof Krawczyk
 */
public class NagiosOutputFormatter extends OutputFormatter {

    /**
     * Implemented method of abstract class. It format checkHandler in nagios
     * plugin format.
     */
    @Override
    public void printOutput() {
        Iterator<CheckHandler> it = this.getCheckHandlersIterator();
        String longservieoutput = "";
        String mainStatus = "ALL SITES OK";
        int faultSites = 0;
        while (it.hasNext()) {
            CheckHandler ch = it.next();
            if (ch != null) {
                String perfdata = "";
                longservieoutput += Integer.toString(ch.getStatusCode()) + " " + ch.getStatusPhrase();
                longservieoutput += "\t" + Long.toString(ch.getTime()) + "ms";
                longservieoutput += "\t" + ch.getUrl();
                if (ch.getStatusCode() >= 200 && ch.getStatusCode() < 400) {
                    longservieoutput += "| " + ch.getUrl() + "=" + Long.toString(ch.getTime());
                } else {
                    longservieoutput += "| " + ch.getUrl() + "=-1";
                    faultSites++;
                }
                longservieoutput += System.lineSeparator();
            }
        }
        if (faultSites > 0) {
            mainStatus = "WARNING! " + Integer.toString(faultSites) + " sites are unaccessible!";
        }
        System.out.println(mainStatus);
        System.out.print(longservieoutput);
    }

}
