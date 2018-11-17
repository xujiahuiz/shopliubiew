package dongdong.bwie.com.jinjiezhoukao3.activityper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import dongdong.bwie.com.jinjiezhoukao3.R;
import dongdong.bwie.com.jinjiezhoukao3.mvp.view.AppIDetagate;
import dongdong.bwie.com.jinjiezhoukao3.view.LiuView;

public class Liupersenter extends AppIDetagate {
    private Context mcontext;
    private LiuView liuview;
    private EditText edtxt;
    private Button bttxt;
    private List list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_liu;
    }

    @Override
    public void initData() {
        super.initData();

        liuview = (LiuView) get(R.id.liu_v);
        edtxt = (EditText) get(R.id.ed_txt);
        bttxt = (Button) get(R.id.bt_txt);
        setClick(new View.OnClickListener() {
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
        }, R.id.bt_txt);
    }

    @Override
    public void iniContext(Context context) {
        super.iniContext(context);
        this.mcontext = context;
    }
}
