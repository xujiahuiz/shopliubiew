package dongdong.bwie.com.jinjiezhoukao3.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dongdong.bwie.com.jinjiezhoukao3.R;

public class LiuView extends RelativeLayout {
    private View view;
    private Context mcontext;
    private LinearLayout layoutv;
    private LinearLayout layouth;

    public LiuView(Context context) {
        super(context);
        initView(context);
    }

    public LiuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public LiuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    private void initView(Context context) {
        //初始化上下文
        this.mcontext = context;
        //获取垂直布局
        view = View.inflate(context, R.layout.layout_item_v, null);
        layoutv = (LinearLayout) view.findViewById(R.id.liu_v);
        //添加到this.addView
        this.addView(view);
    }

    public void getData(List<String> data) {
        /*
         * 写一个方法，调用setList(String集合)
         * */
        setList(data);
    }

    private void setList(List<String> list) {
        //初始化横向布局，初始化横向布局之前remove垂直布局，找控件，添加到横向布局，之后remove横向布局
        layoutv.removeAllViews();
        layouth = (LinearLayout) View.inflate(mcontext, R.layout.layout_item_h, null);
        layoutv.addView(layouth);
        layouth.removeAllViews();
        int len = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            len += s.length();
            if (len > 20) {
                layouth = (LinearLayout) View.inflate(mcontext, R.layout.layout_item_h, null);
                layoutv.addView(layouth);
                len = 0;
            }
            //找文本控件 赋值
            View layouttxt = View.inflate(mcontext, R.layout.layout_item_txt, null);
            TextView txt = (TextView) layouttxt.findViewById(R.id.liu_txt);

            txt.setText(s);
            layouth.addView(layouttxt);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layouttxt.getLayoutParams();
            params.weight = 1;
            params.rightMargin = 10;
            params.leftMargin = 10;
            layouttxt.setLayoutParams(params);
        }

    }

}