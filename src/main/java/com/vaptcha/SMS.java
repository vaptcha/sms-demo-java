package com.vaptcha;

import com.google.gson.Gson;

import java.util.HashMap;

public class SMS {

    public static void Send() {
        String url = "http://sms.vaptcha.com/send";
        HashMap<String, Object> form = new HashMap<>();
        //  https://user.vaptcha.com/sms 用户后台-->短信接口-->短信充值 一栏获取 id 和 key
        form.put("smsid", "*******");
        form.put("smskey", "******");
        String[] strArray = {"_vcode"};
        form.put("data", strArray);
        form.put("countrycode", "86");
        form.put("phone", "****");
        //  https://user.vaptcha.com/sms 用户后台-->短信接口-->短信模板 一栏获取模板 ID
        form.put("templateid", "1");
        Gson gson = new Gson();
        String s = gson.toJson(form);
        try {
            String str = HttpUtil.postJSON(url, s);
            // 返回码列表: https://www.vaptcha.com/document/install.html#%E8%BF%94%E5%9B%9E%E7%A0%81%E5%88%97%E8%A1%A8
            System.out.println("返回码:" + str + " 具体含义见官方文档");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
