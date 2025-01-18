package com.adobe.aem.social.fiwt.core.services;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.net.MalformedURLException;

@Component(service = JsonFromThirdPartyService.class,immediate=true)
public class JsonFromThirdPartyService {

    private String json;

    public String getJsonString(String url) {
            try {
                CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                HttpGet request = new HttpGet(url);
                request.addHeader("Accept", "application/json");
                CloseableHttpResponse response = closeableHttpClient.execute(request);
                if (response.getStatusLine().getStatusCode() == 200) {
                    json = EntityUtils.toString(response.getEntity());
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ClientProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return json;
    }

    public String getJson() {
        return json;
    }
}
