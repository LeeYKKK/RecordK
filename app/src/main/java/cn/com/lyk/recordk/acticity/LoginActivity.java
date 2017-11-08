package cn.com.lyk.recordk.acticity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.com.lyk.recordk.R;
import cn.com.lyk.recordk.service.ApiService;
import cn.com.lyk.recordk.utils.DateUtil;
import cn.com.lyk.recordk.utils.NetUtil;
import cn.com.lyk.recordk.utils.ResultMsg;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @ViewInject(R.id.edPhone)
    private EditText edPhone;
    @ViewInject(R.id.edPass)
    private EditText edPass;
    @ViewInject(R.id.imBack)
    private ImageButton imBack;
    @ViewInject(R.id.btRegister)
    private Button btRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);//注入view和事件
    }

    @Event(type = View.OnClickListener.class, value = {R.id.btRegister})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.btRegister:
                Toast.makeText(this, "点击注册", Toast.LENGTH_SHORT).show();
                register();
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        String nowDate = DateUtil.getFullDate();
        Call<ResultMsg> loginCall = NetUtil.configRetodt(ApiService.class)
                .user(edPhone.getText().toString(), edPass.getText().toString(), nowDate);
        Logger.i("edPhone>>" + edPhone.getText().toString() + "<<<>>>...nowDate>>>" + nowDate);
        loginCall.enqueue(new Callback<ResultMsg>() {
            @Override
            public void onResponse(Call<ResultMsg> call, Response<ResultMsg> response) {
                Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        Toast.makeText(LoginActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultMsg> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "注册失败！123", Toast.LENGTH_SHORT).show();
                Logger.e("Error>>" + t.toString());

            }
        });

    }


}
