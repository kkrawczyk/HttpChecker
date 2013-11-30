/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.httpchecker.utils;

import eu.novait.httpchecker.utils.handlers.CheckHandler;
import java.util.Iterator;

/**
 *
 * @author kkrawczyk
 */
public class NagiosOutputFormatter extends OutputFormatter {

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
        if(faultSites>0){
            mainStatus = "WARNING! "+Integer.toString(faultSites)+" sites are unaccessible!";
        }
        System.out.println(mainStatus);
        System.out.print(longservieoutput);
    }

}
