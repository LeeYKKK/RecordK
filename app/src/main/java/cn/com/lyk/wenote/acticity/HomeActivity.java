package cn.com.lyk.wenote.acticity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.com.lyk.wenote.R;

public class HomeActivity extends AppCompatActivity {
    @ViewInject(R.id.btAdd)
    private Button btAdd;
    @ViewInject(R.id.btLogin)
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        x.view().inject(this);
    }
    @Event(value = {R.id.btAdd,R.id.btLogin})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.btAdd:
                Intent intent=new Intent(this,AddActivity.class);
                startActivity(intent);
                break;
            case R.id.btLogin:
                Intent intent1=new Intent(this,LoginActivity.class);
                startActivity(intent1);
                break;

        }
    }
}
