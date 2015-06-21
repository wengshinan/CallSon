package com.family.callson.utils;

import com.cloud.son.data.json.CallsonUser;

import org.json.JSONObject;

/**
 * Created by wengshinan on 2015/6/14.
 */
public class HttpRequest {

    public static final int REQUEST_TYPE_LOGIN_REGISTER = 1;


    public static Object sendRequest4Response(String request, int requestType){
        if (requestType == REQUEST_TYPE_LOGIN_REGISTER) {
            return getTestUser(request);
        }

        return null;
    }

    public static String getTestUser(String userStr){
        CallsonUser user = new CallsonUser();
        try {
            JSONObject userJson = new JSONObject(userStr);
            user.parse(userJson);

            user.getUProp().setCnName("测试用户");
            user.getUProp().setAge(68);
            user.setType(CallsonUser.UserType.CUSTOMER);
            user.setUId("0000001");
        } catch (Exception e) {
            return userStr;
        }

        return user.create().toString();
    }

}
