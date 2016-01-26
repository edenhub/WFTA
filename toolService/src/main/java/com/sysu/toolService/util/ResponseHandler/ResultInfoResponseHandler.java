package com.sysu.toolService.util.ResponseHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sysu.toolCommons.result.ResultInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class ResultInfoResponseHandler implements ResponseHandler<ResultInfo> {
    private static Gson gson = new GsonBuilder().create();

    @Override
    public ResultInfo handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        HttpEntity entity = httpResponse.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase()
            );
        }

        if (entity == null) {
            throw new ClientProtocolException("response not content");
        }

        ContentType contentType = ContentType.getOrDefault(entity);
        Charset charset = contentType.getCharset();
        Reader reader = new InputStreamReader(entity.getContent(), charset);
        ResultInfo ri = gson.fromJson(reader, ResultInfo.class);
        return ri;
    }
}