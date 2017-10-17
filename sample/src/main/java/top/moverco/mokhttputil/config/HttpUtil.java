package top.moverco.mokhttputil.config;


/**
 * Created by Moverco.
 */

public class HttpUtil {
    private static final String HOME_PAGE = "http://httpbin.org";
    public static final String GET = "/get";
    public static final String POST = "/post";


    public static String getHttpPage(String method){
        return HOME_PAGE+method;
    }


}
