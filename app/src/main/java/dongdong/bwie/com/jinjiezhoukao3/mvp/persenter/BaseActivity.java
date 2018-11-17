package dongdong.bwie.com.jinjiezhoukao3.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dongdong.bwie.com.jinjiezhoukao3.mvp.view.AppIDetagate;

public abstract class BaseActivity<T extends AppIDetagate> extends AppCompatActivity {

    public T appIDetagate;

    public BaseActivity(){
        try {
            appIDetagate=getLayout().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected abstract Class<T> getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appIDetagate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(appIDetagate.rootView());
        appIDetagate.iniContext(this);
        appIDetagate.initData();
    }
}
