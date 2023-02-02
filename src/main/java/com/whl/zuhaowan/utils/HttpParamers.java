package com.whl.zuhaowan.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求参数
 */
public class HttpParamers {
    private Map<String, String[]> params = new HashMap<String, String[]>();
    private HttpMethod httpMethod;
    private String jsonParamer = "";

    public HttpParamers(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * @ModifyTime 2021/08/05
     * post 请求
     **/
    public static HttpParamers httpPostParamers() {
        return new HttpParamers(HttpMethod.POST);
    }

    /**
     * @ModifyTime 2021/08/05
     * get请求
     **/
    public static HttpParamers httpGetParamers() {
        return new HttpParamers(HttpMethod.GET);
    }

    /**
     * @ModifyTime 2021/08/06
     * delete请求
     **/
    public static HttpParamers httpDeleteParamers() {
        return new HttpParamers(HttpMethod.DELETE);
    }

    /**
     * @ModifyTime 2021/08/06
     * put请求
     **/
    public static HttpParamers httpPutParamers() {
        return new HttpParamers(HttpMethod.PUT);
    }

    /**
     * @ModifyTime 2021/08/05
     * 添加key-value参数
     **/
    public HttpParamers addParam(String name, String value) {
        if(params.containsKey(name)){
            String[] source = params.get(name);
            String[] values = new String[source.length + 1];
            int index =0;
            for(String v:source){
                values[index++] = v;
            }
            values[index] =value;
            this.params.put(name, values);
        }else {
            String[] values = new String[]{value};
            this.params.put(name, values);
        }
        return this;
    }
    /**
     * @ModifyTime 2021/08/05
     * 添加json格式参数
     **/
    public HttpParamers addJsonParam(String json) {
        this.jsonParamer = json;
        return this;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public String getQueryString(String charset) throws IOException {
        if ((this.params == null) || (this.params.isEmpty())) {
            return null;
        }
        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, String[]>> entries = this.params.entrySet();

        for (Map.Entry<String, String[]> entry : entries) {
            String name = entry.getKey();
            String[] value = entry.getValue();
            for (String val:value) {
                query.append("&").append(name).append("=").append(URLEncoder.encode(val, charset));
            }
        }
        return query.substring(1);
    }

    public boolean isJson() {
        return !isEmpty(this.jsonParamer);
    }

    public Map<String, String[]> getParams() {
        return this.params;
    }

    public String toString() {
        return "HttpParamers " + JSON.toJSONString(this);        
    }

    public String getJsonParamer() {
        return this.jsonParamer;
    }
    
    public void setJsonParamer() {
        this.jsonParamer = JSON.toJSONString(this.params);
    }
    
    private static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }
}