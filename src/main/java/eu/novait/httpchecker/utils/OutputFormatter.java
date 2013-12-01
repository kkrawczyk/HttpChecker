package eu.novait.httpchecker.utils;

import eu.novait.httpchecker.utils.handlers.CheckHandler;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract class to interprets chackHandlers and format application response.
 *
 * @author Krzysztof Krawczyk
 */
public abstract class OutputFormatter {

    private ArrayList<CheckHandler> checkHandlers;

    /**
     * Default constructor to initialize inner attributes.
     */
    public OutputFormatter() {
        this.checkHandlers = new ArrayList<>();
    }

    /**
     * Adding checkHandler to process
     *
     * @param checkHandler checkHandler to process
     */
    public void addHandler(CheckHandler checkHandler) {
        this.checkHandlers.add(checkHandler);
    }

    /**
     * Returns checkHandlers list iterator
     *
     * @return Iterator of the inner checkHandlers list
     */
    public Iterator<CheckHandler> getCheckHandlersIterator() {
        return this.checkHandlers.iterator();
    }

    /**
     * Returns check handler at the idx index of the inner list
     *
     * @param idx index of the check handler
     * @return check handler
     */
    public CheckHandler getCheckHandler(int idx) {
        if (this.checkHandlers.size() > idx) {
            return this.checkHandlers.get(idx);
        }
        return null;
    }

    /**
     * Count of the check handlers in inner list
     *
     * @return count of the check handlers
     */
    public int getCheckHandlerCount() {
        return this.checkHandlers.size();
    }

    /**
     * Abstract method to override in subclasses
     */
    public abstract void printOutput();
}
