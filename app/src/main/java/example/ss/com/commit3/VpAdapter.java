package example.ss.com.commit3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends PagerAdapter {
    private Context mContext;
    private List<GirlBean.ResultsBean> mList=new ArrayList<>();
    public VpAdapter(Context context) {

        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(View) o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        GirlBean.ResultsBean bean = mList.get(position);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.image, null);
        ImageView iv = inflate.findViewById(R.id.iv);
        Glide.with(mContext).load(bean.getUrl()).into(iv);
        container.addView(inflate);
        return inflate;
    }

    public void addData(List<GirlBean.ResultsBean> resultsBeans) {
        if (resultsBeans != null) {
            mList.addAll(resultsBeans);
            notifyDataSetChanged();
        }
    }
}
