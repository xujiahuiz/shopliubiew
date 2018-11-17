package dongdong.bwie.com.jinjiezhoukao3.adpter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dongdong.bwie.com.jinjiezhoukao3.R;
import dongdong.bwie.com.jinjiezhoukao3.bean.ShopBean;
import dongdong.bwie.com.jinjiezhoukao3.entity.RecycleAdapter;
import dongdong.bwie.com.jinjiezhoukao3.entity.ViewHolder;

public class ShopListAdapter extends RecycleAdapter<ShopBean.DataBean>{

    private Context context;
    private ShopListItemAdapter.comeDataListener listener;

    public void setListener(ShopListItemAdapter.comeDataListener listener) {
        this.listener = listener;
    }

    public ShopListAdapter(Context mcontext) {
        super(mcontext);
        this.context=mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_listadapter_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, ShopBean.DataBean dataBean, int postion) {
        viewHolder.setText(R.id.recycle_name,dataBean.getSellerName());
       RecyclerView recyclerView = (RecyclerView) viewHolder.getView(R.id.recycle_child);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ShopListItemAdapter shopListItemAdapter = new ShopListItemAdapter(context);
        shopListItemAdapter.setComedataListener(listener);
        shopListItemAdapter.setList(dataBean.getList());
        recyclerView.setAdapter(shopListItemAdapter);

    }
}
