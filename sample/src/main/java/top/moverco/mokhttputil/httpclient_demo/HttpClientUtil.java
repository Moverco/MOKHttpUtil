package top.moverco.mokhttputil.httpclient_demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import top.moverco.mokhttputil.config.L;

/**
 * Created by Moverco.
 */

public class HttpClientUtil {
    private static HttpClient mHttpClient = null;
    private static final String CONNECTION = "Connection";
    private static final String KEEP_ALIVE = "Keep-Alive";
    private String post_name = " ";
    private String post_value = " ";

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_value() {
        return post_value;
    }

    public void setPost_value(String post_value) {
        this.post_value = post_value;
    }

    public void setPostContent(String post_name, String post_value) {
        this.post_name = post_name;
        this.post_value = post_value;
    }

    public static HttpClient getHttpClientInstance() {
        if (mHttpClient == null) {
            synchronized (HttpClientUtil.class) {
                if (mHttpClient == null) {
                    mHttpClient = createNewHttpClient();
                }
            }
        }
        return mHttpClient;
    }

    private static HttpClient createNewHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 15000);
        HttpConnectionParams.setConnectionTimeout(params, 15000);
        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(params, true);
        mHttpClient = new DefaultHttpClient(params);
        return mHttpClient;
    }

    /**
     * To execute a Get method
     *
     * @param client
     * @param url
     */
    public void useHttpGET(HttpClient client, String url) {
        if (client == null) {
            client = createNewHttpClient();
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(CONNECTION, KEEP_ALIVE);
        try {
            HttpResponse response = client.execute(httpGet);
            showResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void useHttpGET(String url) {
        useHttpGET(createNewHttpClient(), url);

    }

    /**
     * To execute a Post method
     * @param client
     * @param url
     */
    public void useHttpPost(HttpClient client, String url) {
        if (client == null) {
            client = createNewHttpClient();
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(CONNECTION, KEEP_ALIVE);
        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair(post_name, post_value));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse response = client.execute(httpPost);
            showResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useHttpPost(String url) {
        useHttpPost(createNewHttpClient(), url);
    }

    /**
     * Log the content of response with inputstream
     * @param response
     */
    private void showResponse(HttpResponse response) {
        if (response != null) {
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (entity != null) {
                try {
                    InputStream inputStream = entity.getContent();
                    String content = convertInputStreamToString(inputStream);
                    L.debug("Response code is " + code + "\n" + "Content : " + "\n" + content);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Convert inputstream to string
     * @param stream
     * @return
     */
    private String convertInputStreamToString(InputStream stream) {
        if (stream != null) {
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer sb = new StringBuffer();
            String line = null;
            try {
                while ((line = bufferReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();

        } else return "content is null";
    }

}
