package top.moverco.mokhttputil.httpclient_demo;

import top.moverco.mokhttputil.config.HttpUtil;

/**
 * Created by Moverco.
 */

public class HttpClientDemo {
    public static void executeHttpClient(){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.useHttpGET(HttpUtil.getHttpPage(HttpUtil.GET));
        httpClientUtil.setPostContent("Name","Morton");
        httpClientUtil.useHttpPost(HttpUtil.getHttpPage(HttpUtil.POST));
    }
}
