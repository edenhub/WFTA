package com.sysu.toolPlantform.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by adam on 2016/1/31.
 */
public class HttpUrlValidator {

    private static final int CONNECT_TIMEOUT = 2000;

    public static boolean validate(String urlStr) {
        try {
            return validate(createURL(urlStr));
        } catch (MalformedURLException mue) {
            return false;
        }
    }


    private static boolean validate(URL url) {
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("HEAD");
            httpConnection.setConnectTimeout(CONNECT_TIMEOUT);
            int response = httpConnection.getResponseCode();
            if ((response < 0) || (response >= 300))             // indicates some error
                return false;
        } catch (SocketTimeoutException ste) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        return true;
    }


    private static URL createURL(String urlStr) throws MalformedURLException {

        // escape early if urlStr is obviously bad
        if (urlStr == null) throw new MalformedURLException("URL is null");
        if (!urlStr.startsWith("http://"))
            throw new MalformedURLException("URL does not begin with 'http://'");

        // this will throw an exception if the URL is invalid
        return new URL(urlStr);
    }
}
