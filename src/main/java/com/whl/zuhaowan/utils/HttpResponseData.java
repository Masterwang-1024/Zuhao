package com.whl.zuhaowan.utils;

import lombok.Data;

import java.util.Map;

@Data
public class HttpResponseData {
    boolean isSuccess;
    String responseBody;
    Map<String, String> responseHeaders;
}
