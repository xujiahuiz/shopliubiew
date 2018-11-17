package dongdong.bwie.com.jinjiezhoukao3.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import dongdong.bwie.com.jinjiezhoukao3.mvp.view.AppIDetagate;

public abstract class BaseFragment<T extends AppIDetagate> extends Fragment {

    public T appIDetagate;

    public BaseFragment() {

        try {
            appIDetagate = getLayout().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

    }

    protected abstract Class<T> getLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appIDetagate.create(getLayoutInflater(), null, savedInstanceState);
        return appIDetagate.rootView();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appIDetagate.iniContext(getContext());
        appIDetagate.initData();
    }


}
