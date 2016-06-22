package com.xxg.commonapi.amap;

import com.xxg.commonapi.util.HttpUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by wucao on 16/6/22.
 */
public class AmapService {

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 根据地址获取经纬度
     * @param text 地址字符串
     * @return 经纬度
     */
    public LatitudeAndLongitude getLatitudeAndLongitude(String keyword) throws IOException {
        String url = "http://restapi.amap.com/v3/place/text?key=" + key + "&keywords=" + URLEncoder.encode(keyword, "UTF-8");
        String result = HttpUtil.get(url);
        JSONObject jsonObject = new JSONObject(result);
        if(jsonObject.has("pois") && jsonObject.getJSONArray("pois").length() > 0) {
            String location = jsonObject.getJSONArray("pois").getJSONObject(0).getString("location");
            String[] locationSplit = location.split(",");
            LatitudeAndLongitude latitudeAndLongitude = new LatitudeAndLongitude();
            latitudeAndLongitude.setLongitude(Double.parseDouble(locationSplit[0]));
            latitudeAndLongitude.setLatitude(Double.parseDouble(locationSplit[1]));
            return latitudeAndLongitude;
        } else {
            return null;
        }
    }

}
