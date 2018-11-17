package dongdong.bwie.com.jinjiezhoukao3.activityper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dongdong.bwie.com.jinjiezhoukao3.R;
import dongdong.bwie.com.jinjiezhoukao3.activity.LiuActivity;
import dongdong.bwie.com.jinjiezhoukao3.activity.MainActivity;
import dongdong.bwie.com.jinjiezhoukao3.adpter.ShopListAdapter;
import dongdong.bwie.com.jinjiezhoukao3.adpter.ShopListItemAdapter;
import dongdong.bwie.com.jinjiezhoukao3.bean.ShopBean;
import dongdong.bwie.com.jinjiezhoukao3.mvp.view.AppIDetagate;

public class MainPersenter extends AppIDetagate implements ShopListItemAdapter.comeDataListener {
    private Context mcontext;
    private RecyclerView recycle;
    private static final int SHOP_URI=1;
    private EditText select;
    private ImageView ck;
    private ShopListAdapter shopListAdapter;
    private TextView priceAll;
    private TextView numAll;
    private ShopBean shopBean;
    private List<ShopBean.DataBean> data;
    private int num1 = 0;
    private boolean flag = true;
    private double price = 0.0;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        Map<String, String> map = new HashMap<>();
        map.put("uid", 71 + "");
        getString("/product/getCarts", map, SHOP_URI);
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.main_ck:
                        isCkAll();
                        break;
                    case R.id.main_select:
                       ((MainActivity)mcontext).startActivity(new Intent(mcontext, LiuActivity.class));
                        break;
                }
            }
        },R.id.main_ck,R.id.main_select);
    }

    @Override
    public void Success(int type, String data) {
        super.Success(type, data);
        switch (type){
            case SHOP_URI:
                Log.d("MainPersenter",data);
                Gson gson = new Gson();
               shopBean = gson.fromJson(data, ShopBean.class);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mcontext);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycle.setLayoutManager(linearLayoutManager);
                shopListAdapter.setList(shopBean.getData());
                recycle.setAdapter(shopListAdapter);
                break;
        }
    }

    @Override
    public void fila(String msg) {
        super.fila(msg);
    }

    private void initView() {
        recycle = (RecyclerView) get(R.id.main_Recycle);
        select = (EditText) get(R.id.main_select);
        ck = (ImageView) get(R.id.main_ck);
        shopListAdapter = new ShopListAdapter(mcontext);
        shopListAdapter.setListener(this);
        priceAll = (TextView) get(R.id.main_priceall);
        numAll = (TextView) get(R.id.main_numall);

    }


    @Override
    public void iniContext(Context context) {
        super.iniContext(context);
        this.mcontext = context;
    }

    @Override
    public void comeData(int num) {
        num1 = 0;
        price = 0.0;
         data = shopBean.getData();
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).getList().size(); j++) {
                if (data.get(i).getList().get(j).isChecked()) {
                    num1 += 1;
                    price += data.get(i).getList().get(j).getPrice() * num;
                }
            }
        }
        setNumAndPrice();
    }
    /*
     *所有价格与数量赋值
     * */
    private void setNumAndPrice() {
        numAll.setText("商品数量:" + "(" + num1 + ")");
        priceAll.setText("合计:￥" + price + "");
    }
    //设置全选全不选
    private void isCkAll() {
        if (flag) {
            ck.setImageResource(R.drawable.ck_yes);
            for (int i = 0; i < shopBean.getData().size(); i++) {
                for (int j = 0; j < shopBean.getData().get(i).getList().size(); j++) {
                    shopBean.getData().get(i).getList().get(j).setChecked(true);
                    num1 += 1;
                    price += shopBean.getData().get(i).getList().get(j).getPrice() * num1;
                }
            }
            shopListAdapter.notifyDataSetChanged();
        } else {
           ck.setImageResource(R.drawable.ck_no);
            for (int i = 0; i < shopBean.getData().size(); i++) {
                for (int j = 0; j < shopBean.getData().get(i).getList().size(); j++) {
                    shopBean.getData().get(i).getList().get(j).setChecked(false);
                }
            }
            num1 = 0;
            price = 0;
            shopListAdapter.notifyDataSetChanged();
        }
        flag = !flag;
        setNumAndPrice();
    }

}
