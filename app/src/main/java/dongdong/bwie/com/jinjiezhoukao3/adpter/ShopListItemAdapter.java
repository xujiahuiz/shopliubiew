package dongdong.bwie.com.jinjiezhoukao3.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import dongdong.bwie.com.jinjiezhoukao3.R;
import dongdong.bwie.com.jinjiezhoukao3.bean.ShopBean;
import dongdong.bwie.com.jinjiezhoukao3.entity.RecycleAdapter;
import dongdong.bwie.com.jinjiezhoukao3.entity.ViewHolder;
import dongdong.bwie.com.jinjiezhoukao3.view.ShoCarView;

public class ShopListItemAdapter extends RecycleAdapter<ShopBean.DataBean.ListBean> {

    public ShopListItemAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_listadapter_child;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final ShopBean.DataBean.ListBean dataBean, final int postion) {

        ShoCarView childview = (ShoCarView) viewHolder.getView(R.id.child_view);
        childview.setListener(new ShoCarView.DataBackListener() {
            @Override
            public void backNum(int data) {
                dataBean.setNum(data);
                notifyItemChanged(postion);
                comedataListener.comeData(dataBean.getNum());
            }

            @Override
            public void backSelect(boolean select) {
                Log.d("wwwww", select + "");
                dataBean.setChecked(select);
                notifyItemChanged(postion);
                comedataListener.comeData(dataBean.getNum());
            }
        });
        String[] split = dataBean.getImages().split("\\|");
        childview.setNum(dataBean.getNum());
        childview.setShopImageUrl(split[0]);
        childview.setShopPriceUrl(dataBean.getPrice() + "");
        childview.setShopTitleUrl(dataBean.getTitle());
        childview.setIscheck(dataBean.isChecked());
    }

    //自定义接口，回调数据
    public interface comeDataListener {
        void comeData(int data);
    }

    private comeDataListener comedataListener;

    public void setComedataListener(comeDataListener comedataListener) {
        this.comedataListener = comedataListener;
    }

}
