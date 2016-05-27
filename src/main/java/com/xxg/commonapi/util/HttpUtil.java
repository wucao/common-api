package com.xxg.commonapi.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * HTTP请求工具类
 */
public class HttpUtil {

    /**
     * GET请求
     * @param url URL
     * @param charset 响应Body编码
     * @return 响应内容
     * @throws IOException io异常
     * @author XXG
     */
    public static String get(String url, String charset) throws IOException {
        OutputStream output = null;
        InputStream input = null;
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);
        try {
            input = connection.getInputStream();
            return IOUtils.toString(input, charset);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    /**
     * GET请求
     * @param url URL
     * @return 响应内容
     * @throws IOException io异常
     * @author XXG
     */
    public static String get(String url) throws IOException {
        return get(url, "UTF-8");
    }

    /**
     *  post方式提交字节数组
     *  @param url URL
     *  @param byteArray 请求Body字节数组
     *  @param charset 响应Body编码
     *  @return web返回内容
     *  @throws IOException io异常
     *  @author XXG
     */
    public static String post(String url, byte[] byteArray, String charset) throws IOException {
        OutputStream output = null;
        InputStream input = null;
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);
        try {
            connection.setDoOutput(true);
            output = connection.getOutputStream();
            output.write(byteArray);
            output.flush();

            input = connection.getInputStream();
            return IOUtils.toString(input, charset);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    /**
     *  post方式提交字符串
     *  @param url URL
     *  @param postString 提交的字符串
     *  @param requestCharset 请求Body编码方式
     *  @param responseCharset 响应Body编码方式
     *  @return web返回内容
     *  @throws IOException io异常
     *  @author XXG
     */
    public static String post(String url, String postString, String requestCharset, String responseCharset) throws IOException {
        return post(url, postString.getBytes(requestCharset), responseCharset);
    }

    /**
     *  post方式提交字符串
     *  @param url URL
     *  @param postString 提交的字符串
     *  @return web返回内容
     *  @throws IOException io异常
     *  @author XXG
     */
    public static String post(String url, String postString) throws IOException {
        return post(url, postString, "UTF-8", "UTF-8");
    }
}
