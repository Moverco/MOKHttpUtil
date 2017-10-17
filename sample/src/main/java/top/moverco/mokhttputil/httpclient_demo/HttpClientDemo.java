package top.moverco.mokhttputil.httpclient_demo;

import top.moverco.mokhttputil.config.HttpUtil;

/**
 * Created by Moverco.
 */

public class HttpClientDemo {
    public static void executeHttpClient(){
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.useHttpGET(HttpUtil.getHttpPage(HttpUtil.GET));
        httpClientUtil.setPostContent("ip","59,108,54,37");
        httpClientUtil.useHttpPost("http://ip.taobao.com/service/getIpInfo.php");
    }
}
