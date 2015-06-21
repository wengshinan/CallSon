package com.family.callson.utils.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cloud.son.data.json.CallsonUser;
import com.cloud.son.data.json.History;
import com.cloud.son.data.json.Order;

/**
 * Created by wengshinan on 2015/6/20.
 */
public class DBManager {

    private String phone;
    private SQLiteDatabase db;
    private DBHelper helper;

    private static final String TABLE_NAME_USER = "users";
    private static final String TABLE_NAME_ORDER = "orders";

    public DBManager(String phone, Context context){
        this.phone = phone;
        this.helper = new DBHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public String getToken(String phone){
        Cursor cr = db.query(TABLE_NAME_USER, new String[]{"token"},
                "phone=?", new String[]{phone}, null, null, null);
        String token = null;
        if (cr.moveToFirst()){
            token = cr.getString(0);
        }
        cr.close();
        return token;
    }

    public CallsonUser getUser(String phone){
        Cursor cr = db.query(TABLE_NAME_USER,
                new String[]{"uid","type","cnname","enname","age","description"},
                "phone=?", new String[]{phone}, null, null, null);
        if (!cr.moveToFirst())
            return null;
        CallsonUser user = new CallsonUser();
        int index = 0;
        user.setUId(cr.getString(index++));
        user.setType(CallsonUser.UserType.values()[cr.getInt(index++)]);
        CallsonUser.UserProperty userProp = user.new UserProperty();
        userProp.setCnName(cr.getString(index++));
        userProp.setEnName(cr.getString(index++));
        userProp.setAge(cr.getInt(index++));
        userProp.setDescription(cr.getString(index++));
        userProp.setPhone(phone);
        user.setUProp(userProp);
        cr.close();
        return user;
    }

    public void addOrUpdateUser(CallsonUser user){

    }

    public History getUserHistory(String uId){
        Cursor cr = db.query(TABLE_NAME_ORDER,
                new String[]{}, "uid=?", new String[]{uId}, null, null, null);
        if (!cr.moveToFirst())
            return null;
        History history = new History();
        while (!cr.isAfterLast()) {
            //add history
            //TODO
            cr.moveToNext();
        }
        cr.close();
        return history;
    }

    public void addOrUpdateOrder(Order order){

    }

}
