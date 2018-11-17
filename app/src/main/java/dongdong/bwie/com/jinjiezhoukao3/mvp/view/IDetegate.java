package dongdong.bwie.com.jinjiezhoukao3.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

public interface IDetegate {
    void initData();

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    View rootView();

    void iniContext(Context context);

    void getString(String url, Map<String, String> map, int type);

    void postString(String url, Map<String, String> map, int type);

}
