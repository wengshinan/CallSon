package com.family.callson.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.cloud.son.data.json.CallsonUser;
import com.cloud.son.data.json.CallsonUser.UserProperty;
import com.cloud.son.data.json.CallsonUser.UserType;
import com.family.callson.R;
import com.family.callson.constant.IntentParam;
import com.family.callson.constant.RequestType;
import com.family.callson.utils.HttpRequest;

import org.json.JSONObject;


public class LoginActivity extends ActionBarActivity {

    private CheckBox cl = null;
    private EditText pt = null;

    public CallsonUser getUser() {
        return user;
    }

    private CallsonUser user = new CallsonUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setPage(true);

        cl = (CheckBox) this.findViewById(R.id.password_visable_check);
        pt = (EditText) this.findViewById(R.id.password_text);
        cl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    pt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        final Spinner ageSpinner = (Spinner) LoginActivity.this.findViewById(R.id.age_spinner);
        String[] ageList = new String[80];
        for (int i = 0; i < 80; i++)
            ageList[i] = (i+20) + "岁";
        final ArrayAdapter ageAdapter = new ArrayAdapter(
                LoginActivity.this, R.layout.spinner, ageList);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button loginButton = (Button) findViewById(R.id.login_register);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ((EditText) LoginActivity.this.findViewById(R.id.phone_text))
                        .getText().toString();
                String password = ((EditText) LoginActivity.this.findViewById(R.id.password_text))
                        .getText().toString();

                //构造请求用户对象，包含手机、密码
                UserProperty uProp = user.new UserProperty();
                uProp.setPhone(phone);
                uProp.setPassword(password);
                LoginActivity.this.getUser().setUProp(uProp);

                //联机请求获取用户类型、用户参数
                //TODO，返回不一定是user的json对象，需要修改，看看是不是包装一个request对象
                String response = (String) HttpRequest.sendRequest4Response(
                        LoginActivity.this.getUser().create().toString(),
                        HttpRequest.REQUEST_TYPE_LOGIN_REGISTER);

                try {
                    LoginActivity.this.getUser().parse(new JSONObject(response));
                } catch (Exception e) {

                }

/*
                Intent intent = new Intent();
                intent.putExtra(IntentParam.PARAM_NAME_USER, response);*/

                UserType userType = LoginActivity.this.getUser().getType();
                if (userType == CallsonUser.UserType.VISITOR) {
                    // 弹出注册框，填写客户信息
                    LoginActivity.this.setPage(false);

                } else if (userType == CallsonUser.UserType.CUSTOMER
                        || userType == CallsonUser.UserType.PROVIDER
                        || userType == CallsonUser.UserType.MANAGER) {
                    LoginActivity.this.finish();
                } else {
                    LoginActivity.this.finish();
                }
            }
        });

        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

    }

    public void setLoginPage(){

    }

    public void setRegisterPage(){

    }

    private void setPage(boolean isLogin){
        if (isLogin) {
            ((EditText) findViewById(R.id.phone_text)).setEnabled(true);
            ((EditText) findViewById(R.id.password_text)).setEnabled(true);

            findViewById(R.id.login_register).setVisibility(View.VISIBLE);
            findViewById(R.id.validate_laber).setVisibility(View.INVISIBLE);
            findViewById(R.id.validate_text).setVisibility(View.INVISIBLE);
            findViewById(R.id.cn_name_label).setVisibility(View.INVISIBLE);
            findViewById(R.id.cn_name_text).setVisibility(View.INVISIBLE);
            findViewById(R.id.age_label).setVisibility(View.INVISIBLE);
            findViewById(R.id.age_spinner).setVisibility(View.INVISIBLE);
            findViewById(R.id.description_label).setVisibility(View.INVISIBLE);
            findViewById(R.id.description_text).setVisibility(View.INVISIBLE);
            findViewById(R.id.register_button).setVisibility(View.INVISIBLE);
        } else {
            ((EditText) findViewById(R.id.phone_text)).setEnabled(false);
            ((EditText) findViewById(R.id.password_text)).setEnabled(false);

            findViewById(R.id.login_register).setVisibility(View.INVISIBLE);
            findViewById(R.id.validate_laber).setVisibility(View.VISIBLE);
            findViewById(R.id.validate_text).setVisibility(View.VISIBLE);
            findViewById(R.id.cn_name_label).setVisibility(View.VISIBLE);
            findViewById(R.id.cn_name_text).setVisibility(View.VISIBLE);
            findViewById(R.id.age_label).setVisibility(View.VISIBLE);
            findViewById(R.id.age_spinner).setVisibility(View.VISIBLE);
            findViewById(R.id.description_label).setVisibility(View.VISIBLE);
            findViewById(R.id.description_text).setVisibility(View.VISIBLE);
            findViewById(R.id.register_button).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(IntentParam.PARAM_NAME_USER, user.create().toString());
        setResult(RESULT_OK, intent);
        super.finish();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */
}
