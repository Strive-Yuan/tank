package com.api.utils;

import com.api.exception.HubServerException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);


    public static String doPost(String url, HttpEntity entity, List<Header> headers) throws Exception {
        String result;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
            httpPost.setEntity(entity);
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                int statusCode = response.getStatusLine().getStatusCode();
                logger.info("响应状态->{}", statusCode);
                logger.info("响应消息->{}", result);
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new HubServerException("post请求错误!状态码:" + statusCode + "响应消息:" + result);
                }
            }

        }
        return result;
    }

    public static String doGET(String url, List<Header> headers, Map<String, String> paramMap) throws Exception {
        String result;
        URIBuilder uriBuilder = new URIBuilder(url);
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                uriBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(httpGet::addHeader);
        }

        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpGet)) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            int statusCode = response.getStatusLine().getStatusCode();
//            logger.info("响应状态->{}", statusCode);
//            logger.info("响应消息->{}", result);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new HubServerException("get请求错误!响应消息状态:" + statusCode + " 响应消息:" + result);
            }
        }
        return result;
    }
}
