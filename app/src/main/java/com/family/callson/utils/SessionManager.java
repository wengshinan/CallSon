package com.family.callson.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.cloud.son.entity.constant.UserConstant;

/**
 * Created by wengshinan on 2015/6/20.
 */
public class SessionManager {

    private SharedPreferences pref;

    private Editor editor;

    private Context _context;

    private static final int PRIVATE_MODE = 0;

    private static final String KEY_PREF_NAME = "CallsonUserInfo";

    private static final String KEY_IS_LOGIN = "IsLoggedIn";

    private static final String KEY_TOKEN = "Token";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(KEY_PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String phone, String cnname, String token){
        editor.putBoolean(KEY_IS_LOGIN, true);
        editor.putString(UserConstant.USER_PARAM_PHONE, phone);
        editor.putString(UserConstant.USER_PARAM_CNNAME, cnname);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public boolean isLogged(){
        if (pref.getBoolean(KEY_IS_LOGIN, false) == true)
            return true;
        return false;
    }

    public String getCnname(){
        if (isLogged())
            return pref.getString(UserConstant.USER_PARAM_CNNAME, null);
        return null;
    }

    public String getPhone(){
        if (isLogged())
            return pref.getString(UserConstant.USER_PARAM_PHONE, null);
        return null;
    }

    public String getToken(){
        if (isLogged())
            return pref.getString(KEY_TOKEN, null);
        return null;
    }
}
