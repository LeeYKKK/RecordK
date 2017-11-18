package cn.com.lyk.wenote.acticity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.com.lyk.wenote.R;
import cn.com.lyk.wenote.service.ApiService;
import cn.com.lyk.wenote.utils.NetUtil;
import cn.com.lyk.wenote.utils.RegexUtil;
import cn.com.lyk.wenote.utils.ResultMsg;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @ViewInject(R.id.edLoginEmail)
    private EditText edLoginEmail;
    @ViewInject(R.id.edLoginPassword)
    private EditText edLoginPassword;
    @ViewInject(R.id.btLoginUser)
    private Button btLoginUser;
    @ViewInject(R.id.tlLoginEmail)
    private TextInputLayout tlLoginEmail;
    private Call<ResultMsg> loginCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);
        initEvent();

    }

    @Event(value = R.id.btLoginUser)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLoginUser:

                if (edLoginEmail.getText().toString() == null ||
                        edLoginPassword.getText().toString() == null) {
                    Toast.makeText(this, "邮箱和密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!RegexUtil.EMAIL_PATTERN.matcher(
                        edLoginEmail.getText().toString()).matches()) {
                    tlLoginEmail.setErrorEnabled(true);
                    tlLoginEmail.setError("");

                } else {
                    loginCall();
                }
                break;
        }

    }

    private void loginCall() {
        loginCall = NetUtil.configRetodt(ApiService.class)
                .userLogin(edLoginEmail.getText().toString(), edLoginPassword.getText().toString());
        loginCall.enqueue(new Callback<ResultMsg>() {
            @Override
            public void onResponse(Call<ResultMsg> call, Response<ResultMsg> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplication(),MainActivity.class);
                        startActivity(intent);
                    } else if (response.body().getCode().equals("403")) {
                        Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResultMsg> call, Throwable t) {
                Logger.e("Error>>" + t.toString());

            }
        });

    }


    private void initEvent() {
        edLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tlLoginEmail.setErrorEnabled(false);

            }
        });
    }


}
