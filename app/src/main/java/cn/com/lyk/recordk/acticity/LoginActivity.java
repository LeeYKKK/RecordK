package cn.com.lyk.recordk.acticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.com.lyk.recordk.R;
import cn.com.lyk.recordk.been.User;
import cn.com.lyk.recordk.service.ApiService;
import cn.com.lyk.recordk.utils.NetUtil;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    @ViewInject(R.id.edPhone)
    private EditText edPhone;
    @ViewInject(R.id.edPass)
    private EditText edPass;
    @ViewInject(R.id.imBack)
    private ImageButton imBack;
     Call<User> loginCall;

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
loginCall= NetUtil.configRetodt(ApiService.class)
        .user()
                break;
        }
    }


}
