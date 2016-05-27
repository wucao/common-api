package com.xxg.commonapi.kdniao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.xxg.commonapi.util.HttpUtil;

/**
 * 快递鸟快递跟踪查询
 * @author wucao
 *
 */
public class KdniaoService {
	
	private String apiId;
    private String apiKey;

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * 快递查询跟踪
     * @param shipperCode 快递公司编号
     * @param expressNumber 快递单号
     * @return 快递跟踪信息
     */
    public List<KdniaoTrace> getTrace(String shipperCode, String expressNumber) throws IOException {

        JSONObject requestJson = new JSONObject();
        requestJson.put("ShipperCode", shipperCode);
        requestJson.put("LogisticCode", expressNumber);
        String requestData = requestJson.toString();

        String url = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";
        String requestBody = "RequestData=" + URLEncoder.encode(requestData, "UTF-8");
        requestBody += "&EBusinessID=" + apiId;
        requestBody += "&RequestType=1002";
        requestBody += "&DataSign=" + generateSign(requestData);
        requestBody += "&DataType=2";

        String response = HttpUtil.post(url, requestBody);

        JSONObject responseJson = new JSONObject(response);

        List<KdniaoTrace> list = null;
        if(responseJson.has("Traces")) {
            list = new ArrayList<>();
            JSONArray jsonArray = responseJson.getJSONArray("Traces");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject traceJson = jsonArray.getJSONObject(i);
                KdniaoTrace kdniaoTrace = new KdniaoTrace();
                kdniaoTrace.setTime(traceJson.getString("AcceptTime"));
                kdniaoTrace.setMessage(traceJson.getString("AcceptStation"));
                list.add(kdniaoTrace);
            }
        }

        return list;
    }

    private String generateSign(String data) throws UnsupportedEncodingException {
        String md5 = DigestUtils.md5Hex(data + apiKey);
        String base64 = Base64.encodeBase64String(md5.getBytes("UTF-8"));
        String result = URLEncoder.encode(base64, "UTF-8");
        return result;
    }

}
