package dongdong.bwie.com.jinjiezhoukao3.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import dongdong.bwie.com.jinjiezhoukao3.R;
import dongdong.bwie.com.jinjiezhoukao3.view.LiuView;

public class LiuActivity extends AppCompatActivity {
    private Context mcontext;
    private LiuView liuview;
    private EditText edtxt;

    private List list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liu);
        liuview = (LiuView) findViewById(R.id.liuview);
        edtxt = (EditText) findViewById(R.id.ed_txt);
        findViewById(R.id.bt_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_txt:
                        String trim = edtxt.getText().toString().trim();
                        list.add(trim);
                        Log.d("TAG", list.toString());
                        liuview.getData(list);

                        break;
                }
            }
        });


    }
}
