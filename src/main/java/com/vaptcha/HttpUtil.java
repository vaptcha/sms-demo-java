package com.vaptcha;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
    // 发送POST请求（JSON形式）
    public static String postJSON(String url, String json) throws Exception {
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        return postRequest(url, entity);
    }

    // 发送POST请求
    private static String postRequest(String url, HttpEntity entity) throws Exception {
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        post.setEntity(entity);
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new Exception(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new Exception("postRequest -- Client protocol exception!", e);
        } catch (IOException e) {
            throw new Exception("postRequest -- IO error!", e);
        } finally {
            post.releaseConnection();
        }
    }
}
