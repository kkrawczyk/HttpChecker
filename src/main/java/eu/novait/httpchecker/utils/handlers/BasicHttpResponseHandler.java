/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.novait.httpchecker.utils.handlers;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author kkrawczyk
 */
public class BasicHttpResponseHandler implements ResponseHandler {

    private int statusCode;
    private String statusMessage;

    @Override
    public Object handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
        this.statusCode = hr.getStatusLine().getStatusCode();
        this.statusMessage = hr.getStatusLine().getReasonPhrase();
        HttpEntity he = hr.getEntity();
        return he != null ? EntityUtils.toByteArray(he) : null;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
