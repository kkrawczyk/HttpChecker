/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.httpchecker.utils;

import eu.novait.httpchecker.utils.handlers.CheckHandler;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author kkrawczyk
 */
public abstract class OutputFormatter {

    private ArrayList<CheckHandler> checkHandlers;

    public OutputFormatter() {
        this.checkHandlers = new ArrayList<>();
    }

    public void addHandler(CheckHandler checkHandler) {
        this.checkHandlers.add(checkHandler);
    }

    public Iterator<CheckHandler> getCheckHandlersIterator() {
        return this.checkHandlers.iterator();
    }

    public CheckHandler getCheckHandler(int idx) {
        if (this.checkHandlers.size() > idx) {
            return this.checkHandlers.get(idx);
        }
        return null;
    }

    public int getCheckHandlerCount() {
        return this.checkHandlers.size();
    }

    public abstract void printOutput();
}
