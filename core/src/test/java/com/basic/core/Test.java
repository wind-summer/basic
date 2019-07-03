package com.basic.core;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2019/6/6
 */
public class Test {


    public static void main(String[] args) {
//        String url = "String url = http://kq.litsoft.com.cn/WebApi/Home/GetListData?_dc=1559787807977";
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTQ4MjEyMDkyfQ.TlDGtt6y57-YnSw9-1scC3TiIW75SXEbNEaU8Y-pYEs");
//
//
//        String result2 = HttpRequest.post(url)
//                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTQ4MjEyMDkyfQ.TlDGtt6y57-YnSw9-1scC3TiIW75SXEbNEaU8Y-pYEs")//头信息，多个头信息多次调用此方法即可
//                //.form(paramMap)//表单内容
//                .timeout(20000)//超时，毫秒
//                .execute().body();
//        //String result= HttpUtil.post(url, paramMap);
        String url1 = "http://kq.litsoft.com.cn/WebApi/Home/GetListData?_dc=1559801983129";
        String url2 = "http://localhost:8090/admin/demo/test1";
        //String result = doGetNpParams(url2);
        //doGetForParams(url2, new HashMap<String, String>());


        Map<String, String> header = new HashMap<>();
//        header.put("Accept","*/*");
//        header.put("Accept-Encoding","gzip, deflate");
//        header.put("Accept-Language","zh-CN,zh;q=0.9");

        header.put("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTQ4MjEyMDkyfQ.TlDGtt6y57-YnSw9-1scC3TiIW75SXEbNEaU8Y-pYEs");
        header.put("Cookie", "ASP.NET_SessionId=1kpdl30j3onasl0f5wa0cnp1; VirtualDir=; LitsoftAttFFLanguage=zh-cn; DONOTUSESID=wenlf");
        //header.put("Connection","keep-alive");
        //header.put("Content-Length","321");
//        header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        header.put("Cookie","LitsoftAttFFLanguage=zh-cn; qqmail_alias=wenlf@litsoft.com.cn; ASP.NET_SessionId=z4zbukvd0yzxxeqc3wdy4vky; VirtualDir=; DONOTUSESID=wenlf");
//        header.put("Host","kq.litsoft.com.cn");
//        header.put("Origin","http://kq.litsoft.com.cn");
//        header.put("Referer","http://kq.litsoft.com.cn/Home/PopUpIndex/");
//        header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3676.400 QQBrowser/10.4.3505.400");
//        header.put("X-Requested-With","XMLHttpRequest");
        Map<String, Object> commonParams = new HashMap<>();
        commonParams.put("FFVmName", "Litsoft.Attendance.ViewModel.Attendance.AttendanceDataListVM2, Litsoft.Attendance.ViewModel");
        commonParams.put("ViewDivID", "ViewDiva0996c3456c544e1ca5edc4e87f105261");
        commonParams.put("SearcherMode", "Search");
        commonParams.put("DONOTUSE_WindowID", null);
        commonParams.put("PageComboSearcha0996c3456c544e1ca5edc4e87f105261-inputEl", 30);
        commonParams.put("Searcher.YearNumber", "2019");
        commonParams.put("Searcher.MonthNumber", "06");
        commonParams.put("Searcher.DepartmentID", null);
        commonParams.put("Searcher.ITCode", "wenlf");
        commonParams.put("Searcher.AttendanceStatusID", null);
        commonParams.put("Searcher.OfficeID", null);
        commonParams.put("RecordsPerPage", 30);
        commonParams.put("SaveInCookie", null);
        commonParams.put("Searcher.CurrentPage", 1);
        commonParams.put("start", 0);


        String result = doPostNoParams(url1, null, commonParams, header);
        System.out.println("打印："+result);






        //        Map<String, String> header = new HashMap<>();
//        header.put("Accept","*/*");
//        header.put("Accept-Encoding","gzip, deflate");
//        header.put("Accept-Language","zh-CN,zh;q=0.9");
//        header.put("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTQ4MjEyMDkyfQ.TlDGtt6y57-YnSw9-1scC3TiIW75SXEbNEaU8Y-pYEs");
//        header.put("Connection","keep-alive");
//        header.put("Content-Length","321");
//        header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        header.put("Cookie","LitsoftAttFFLanguage=zh-cn; qqmail_alias=wenlf@litsoft.com.cn; ASP.NET_SessionId=z4zbukvd0yzxxeqc3wdy4vky; VirtualDir=; DONOTUSESID=wenlf");
//        header.put("Host","kq.litsoft.com.cn");
//        header.put("Origin","http://kq.litsoft.com.cn");
//        header.put("Referer","http://kq.litsoft.com.cn/Home/PopUpIndex/");
//        header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3676.400 QQBrowser/10.4.3505.400");
//        header.put("X-Requested-With","XMLHttpRequest");

    }


    /**
     * POST---无参测试
     */
    /**
     * POST请求
     * @param url
     * @param commonParams url上面的普通参数，为空则视为没有
     * @param formParam 对象参数，为空则视为没有
     * @param header 头参数，为空则视为没有
     * @return
     */
    public static String doPostNoParams(String url,Map<String, String> commonParams, Map<String, Object> formParam, Map<String, String> header) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        if(commonParams != null ){
            StringBuffer params = new StringBuffer();
            params.append("?");
            for(String key : commonParams.keySet()) {
                params.append(key+"=" + commonParams.get(key));
                params.append("&");
            }
            url+=params;
        }

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        //设置header
        if(header != null) {
            for(String key : header.keySet()) {
                httpPost.setHeader(key, header.get(key));
            }
        }
        //组织请求参数(formdata表单形式）
        List<NameValuePair> paramList = new ArrayList<>();
        if(formParam != null && formParam.size() > 0){
            Set<String> keySet = formParam.keySet();
            for(String key : keySet) {
                if(formParam.get(key) != null){
                    paramList.add(new BasicNameValuePair(key, formParam.get(key).toString()));
                }
            }
        }
        if(paramList.size()>0){
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
//        // 将user对象转换为json字符串，并放入entity中
//        if(objectParam != null) {
//            StringEntity entity = new StringEntity(JSON.toJSONString(objectParam), "UTF-8");
//            // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
//            httpPost.setEntity(entity);
//        }

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if(200 == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * GET---有参测试 (方式一:手动在url后面加上参数)
     */
    public static String doGetForParams(String url, Map<String, String> paramsMap) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuffer params = new StringBuffer();
        for(String key : paramsMap.keySet()) {
            params.append(key+"=" + paramsMap.get(key));
            params.append("&");
        }
        // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
        //params.append("name=" + URLEncoder.encode("&", "utf-8"));

        // 创建Get请求
        HttpGet httpGet = new HttpGet(url + "?" + params);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if(200 == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * GET---无参测试
     */
    public static String doGetNpParams(String url) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if(200 == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(responseEntity);
            }
            //System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                result = EntityUtils.toString(responseEntity);
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
