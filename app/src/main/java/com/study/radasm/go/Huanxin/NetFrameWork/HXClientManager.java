package com.study.radasm.go.Huanxin.NetFrameWork;

import android.text.TextUtils;

import com.study.radasm.go.AppClient;
import com.study.radasm.go.Huanxin.HXException.HXException;
import com.study.radasm.go.Utils.LogUtils;
import com.study.radasm.go.Utils.NetUtils;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/******     **********            需要重写       ***********         *******/

/**
 *
 *
 * Created by RadAsm on 15/5/15.
 */
public class HXClientManager {
    private static final String TAG = "HttpClientManager";
    public static String Method_GET = "GET";
    public static String Method_POST = "POST";
    public static String Method_PUT = "PUT";
    public static String Method_DELETE = "DELETE";
    public static final int max_retries_times_on_connection_refused = 20;

    private static final String httpFormatter = "&s is sending httpRequest by method &s";

    private static final String ERROR_RESPONSE="error_response";

    public HXClientManager() {

    }

    /**
     * send a HttpRequest by requestMethod，异常链的处理
     *
     * @param url
     * @param map
     * @param StringJson
     * @param requestMethod
     * @return
     */
    public static String sendHttpRequest(String url, Map map, String stringJson, String requestMethod) throws IOException,HXException{
        LogUtils.i(TAG, httpFormatter, url, requestMethod);

        /**
         * 设置最后处理使用
         */
        IOException ie = null;
        HXException he = null;
        Object exception = null;
        String httpResponse=null;

        try {
            httpResponse = getHttpResponse(url, map, stringJson, requestMethod);
        } catch (IOException e) {
            ie = e;
            exception = e;

        } catch (HXException e) {
            he = e;
            exception = e;
        }

        String detailMessage="";
        /**
         * 如果exception有返回的异常信息的时候,保存异常信息
         */
        if(exception!=null&&!TextUtils.isEmpty(((Exception) exception).getMessage())){
            detailMessage=((Exception)exception).getMessage();
        }

        /**
         * 分析具体的出错的信息
         *      如果在异常的具体的信息中出现“refuse”或者本机的网络不可用的时候，就直接break;
         */
        if((detailMessage.toLowerCase().contains("refuse"))|| !NetUtils.hasNetwork(AppClient.getInstance().getContext())){
            httpResponse=ERROR_RESPONSE;
        }

        /**
         * 异常链的处理
         */
        if(ie!=null){
            throw ie;
        }else if(he!=null){
            throw he;
        }else{
            return httpResponse;
        }

    }

    /**
     * execute a request and return a HttpRequest result
     *
     * @param url
     * @param map
     * @param stringJson
     * @param requestMethod
     * @return
     */
    private static String getHttpResponse(String url, Map map, String stringJson, String requestMethod) throws IOException, HXException {
        /**
         * 现将头部信息添加上去，再执行
         */
        addDomainToHeader(map);
        return null;
    }

    /**
     * 添加头部信息
     * @param map
     */
    private static void addDomainToHeader(Map map) {


    }


}
