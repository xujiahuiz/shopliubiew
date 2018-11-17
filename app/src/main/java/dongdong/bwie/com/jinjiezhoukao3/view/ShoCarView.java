package dongdong.bwie.com.jinjiezhoukao3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dongdong.bwie.com.jinjiezhoukao3.R;


public class ShoCarView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private ImageView select;
    private ImageView shopiamge;
    private EditText edNum;
    private TextView txtTitle;
    private TextView txtPrice;
    private TextView txtMinus;
    private TextView txtPush;
    private int num = 1;
    private boolean ischeck;

    public ShoCarView(Context context) {
        super(context);
        init(context);
    }

    public ShoCarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShoCarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context mContext) {
        this.mContext = mContext;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_shopcar_view, null);
        //初始化控件
        initWeght(view);
        addView(view);
    }

    private void initWeght(View view) {
        select = (ImageView) view.findViewById(R.id.iv_select);
        shopiamge = (ImageView) view.findViewById(R.id.iv_shop_iamge);
        txtTitle = (TextView) view.findViewById(R.id.iv_title);
        txtPrice = (TextView) view.findViewById(R.id.iv_price);
        txtMinus = (TextView) view.findViewById(R.id.iv_minus);
        txtPush = (TextView) view.findViewById(R.id.iv_push);
        edNum = (EditText) view.findViewById(R.id.iv_num);
        txtMinus.setOnClickListener(this);
        txtPush.setOnClickListener(this);
        select.setOnClickListener(this);
    }

    /*
     * 设置选中未选中图片
     * */
    public void setSelectImageResouse(int resouse) {
        select.setImageResource(resouse);
    }

    /*
     * 设置商品图片
     * */
    public void setShopImageUrl(String url) {
        Picasso.with(mContext).load(url).fit().into(shopiamge);
    }

    /*
     * 设置商品标题
     * */
    public void setShopTitleUrl(String title) {
        txtTitle.setText(title);
    }

    /*
     * 设置商品标题
     * */
    public void setShopPriceUrl(String price) {
        txtPrice.setText(price);
    }

    /*
     *加减号点击  选中未选中点击
     *  */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_minus:
                num--;
                wrEd(num);
                break;
            case R.id.iv_push:
                num++;
                wrEd(num);
                break;
            case R.id.iv_select:

                if (ischeck==true) {
                    ischeck = false;
                    select.setImageResource(R.drawable.ck_no);
                } else {
                    ischeck = true;
                    select.setImageResource(R.drawable.ck_yes);
                }
                listener.backSelect(ischeck);
                break;
        }
    }

    /*
     *Edtext赋值，往接口传递数据
     *  */
    private void wrEd(int num) {
        edNum.setText(num+"");
        listener.backNum(num);
    }

    /*
     * 初始化的默认值
     * */
    public void setNum(int num) {
        this.num = num;
        edNum.setText(num + "");
    }

    /*
     *选中未选中赋值
     * */
    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
        if (ischeck) {
            select.setImageResource(R.drawable.ck_yes);
        } else {
            select.setImageResource(R.drawable.ck_no);
        }

    }

    //接口回调
    public interface DataBackListener {
        void backNum(int data);

        void backSelect(boolean select);

    }

    private DataBackListener listener;

    public void setListener(DataBackListener listener) {
        this.listener = listener;
    }

}