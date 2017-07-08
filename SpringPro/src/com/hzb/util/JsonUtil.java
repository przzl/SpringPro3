package com.hzb.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2017-3-13
 * Time: 22:01:40
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {
    /**
     * json字符串转java List
     *
     * @param rsContent
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> jsonStringToList(String rsContent) throws Exception {
        JSONArray arry = JSONArray.fromObject(rsContent);

        System.out.println("json字符串内容如下");
        System.out.println(arry);
        List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < arry.size(); i++) {
            JSONObject jsonObject = arry.getJSONObject(i);
            Map<String, String> map = new HashMap<String, String>();
            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
                String key = (String) iter.next();
                String value = jsonObject.get(key).toString();
                map.put(key, value);
            }
            rsList.add(map);
        }
        return rsList;
    }

    public static String parseJson(String data) {
        String cityName = "";
        try {
            //JSONArray arry = JSONArray.fromObject(data);
            JSONObject jsonObject = JSONObject.fromObject(data);
            JSONObject jsonObjectRes = jsonObject.getJSONObject("result");
            JSONObject jsonObjectResAdd = jsonObjectRes.getJSONObject("addressComponent");
            cityName = jsonObjectResAdd.getString("city");
        } catch (Exception e) {
             e.printStackTrace();
        }
        return cityName;
    }
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        List<Map<String, String>> list1 = jsonStringToList(diskListContent);
//        System.out.println("json字符串成map");
//        for (Map<String, String> m : list1) {
//            System.out.println(m);
//        }
//        System.out.println("map转换成json字符串");
//        for (Map<String, String> m : list1) {
//            JSONArray jsonArray = JSONArray.fromObject(m);
//            System.out.println(jsonArray.toString());
//
//        }
//        System.out.println("list转换成json字符串");
//        JSONArray jsonArray2 = JSONArray.fromObject(list1);
//        System.out.println(jsonArray2.toString());

        JSONObject jsonObject = JSONObject.fromObject(ddddd);
            JSONObject jsonObjectRes =jsonObject.getJSONObject("result");
            JSONObject jsonObjectResAdd =jsonObjectRes.getJSONObject("addressComponent");
            String cityName =  jsonObjectResAdd.getString("city");
    }

    // json字符串转
    private static String diskListContent =
            "[{\"n1\":\"asd\",\"n2\":22,\"n3\":\"45.40GB\"," + "\"n4\":\"qwerty\",\"n5\":\"asd\",}," + "{\"n1\":\"local\","
                    + "\"n2\":1,\"n3\":\"279.40GB\",\"n4\":\"ST3300656SS\",\"n5\":\"\\/devm\\/d0\"}]";
    private static String ddddd = "{\"status\":0,\"result\":{\"location\":{\"lng\":118.16819859999996,\"lat\":24.474051619681114},\"formatted_address\":\"福建省厦门市思明区文兴西路\",\"business\":\"莲前\",\"addressComponent\":{\"country\":\"中国\",\"country_code\":0,\"province\":\"福建省\",\"city\":\"厦门市\",\"district\":\"思明区\",\"adcode\":\"350203\",\"street\":\"文兴西路\",\"street_number\":\"\",\"direction\":\"\",\"distance\":\"\"},\"pois\":[{\"addr\":\"厦门市思明区文兴东路、洪文小区后面\",\"cp\":\" \",\"direction\":\"南\",\"distance\":\"280\",\"name\":\"云顶至尊\",\"poiType\":\"房地产\",\"point\":{\"x\":118.16742759606056,\"y\":24.47624656348955},\"tag\":\"房地产;住宅区\",\"tel\":\"\",\"uid\":\"c7eca12b4671db37e015f9b3\",\"zip\":\"\"},{\"addr\":\"思明区洪文七里94号(近文兴西路)\",\"cp\":\" \",\"direction\":\"南\",\"distance\":\"333\",\"name\":\"厦门市华侨幼儿园(文兴西路)\",\"poiType\":\"教育培训\",\"point\":{\"x\":118.16820912185399,\"y\":24.476797431443406},\"tag\":\"教育培训;幼儿园\",\"tel\":\"\",\"uid\":\"dd5a7a4ffa0ea6e4b04c4944\",\"zip\":\"\"},{\"addr\":\"前埔六里21\",\"cp\":\" \",\"direction\":\"西\",\"distance\":\"378\",\"name\":\"源泉海景公寓\",\"poiType\":\"房地产\",\"point\":{\"x\":118.17157776751529,\"y\":24.473714183918888},\"tag\":\"房地产;住宅区\",\"tel\":\"\",\"uid\":\"1cbfcf199014af98b5f42f54\",\"zip\":\"\"},{\"addr\":\"厦门市思明区\",\"cp\":\" \",\"direction\":\"南\",\"distance\":\"366\",\"name\":\"厦门市公安局莲前派出所警务一队\",\"poiType\":\"政府机构\",\"point\":{\"x\":118.16857742711295,\"y\":24.47704408794592},\"tag\":\"政府机构;公检法机构\",\"tel\":\"\",\"uid\":\"edb4b2818f2dd1d5c61b7517\",\"zip\":\"\"},{\"addr\":\"厦门市思明区\",\"cp\":\" \",\"direction\":\"南\",\"distance\":\"369\",\"name\":\"莲前派出所第一警务队\",\"poiType\":\"政府机构\",\"point\":{\"x\":118.16862234238845,\"y\":24.47706875356929},\"tag\":\"政府机构;公检法机构\",\"tel\":\"\",\"uid\":\"7b68bde3e00a57d5a11de9b3\",\"zip\":\"\"},{\"addr\":\"洪文七里2-41号\",\"cp\":\" \",\"direction\":\"东南\",\"distance\":\"602\",\"name\":\"瑞景公园\",\"poiType\":\"房地产\",\"point\":{\"x\":118.16576573086765,\"y\":24.47847468602078},\"tag\":\"房地产;住宅区\",\"tel\":\"\",\"uid\":\"fd007c5eabe8a011a8f729f8\",\"zip\":\"\"},{\"addr\":\"福建省厦门市思明区柯厝路354号源泉山庄B地块\",\"cp\":\" \",\"direction\":\"西北\",\"distance\":\"411\",\"name\":\"厦门城市职业学院附属幼儿园\",\"poiType\":\"教育培训\",\"point\":{\"x\":118.16979013955103,\"y\":24.471000862922826},\"tag\":\"教育培训;幼儿园\",\"tel\":\"\",\"uid\":\"a67ad66e8fbd5e56adcb665d\",\"zip\":\"\"},{\"addr\":\"前埔六里28号之111\",\"cp\":\" \",\"direction\":\"西\",\"distance\":\"502\",\"name\":\"闽丰生鲜超市\",\"poiType\":\"购物\",\"point\":{\"x\":118.17264675107181,\"y\":24.473352411203057},\"tag\":\"购物;超市\",\"tel\":\"\",\"uid\":\"7c675c62e5015a2243e1920a\",\"zip\":\"\"},{\"addr\":\"前埔文兴西路1495号云顶至尊往会展方向往前100米（厦航宿舍对面）。\",\"cp\":\" \",\"direction\":\"西南\",\"distance\":\"651\",\"name\":\"厦门君泰酒店\",\"poiType\":\"酒店\",\"point\":{\"x\":118.17281742911865,\"y\":24.477340075103688},\"tag\":\"酒店\",\"tel\":\"\",\"uid\":\"b0c7c0c22d315153e9f853a0\",\"zip\":\"\"},{\"addr\":\"厦门市思明区文兴西路1479号\",\"cp\":\" \",\"direction\":\"西\",\"distance\":\"743\",\"name\":\"日日建安车业连锁机构\",\"poiType\":\"汽车服务\",\"point\":{\"x\":118.17455115875234,\"y\":24.475934129835165},\"tag\":\"汽车服务;汽车维修\",\"tel\":\"\",\"uid\":\"46e86fdde867746cd13ce304\",\"zip\":\"\"}],\"poiRegions\":[],\"sematic_description\":\"云顶至尊南280米\",\"cityCode\":194}}";
}
