package dongdong.bwie.com.jinjiezhoukao3.activity;

import dongdong.bwie.com.jinjiezhoukao3.activityper.MainPersenter;
import dongdong.bwie.com.jinjiezhoukao3.mvp.persenter.BaseActivity;

public class MainActivity extends BaseActivity<MainPersenter> {

    @Override
    protected Class<MainPersenter> getLayout() {
        return MainPersenter.class;
    }

}
