package example.ss.com.commit3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.MyViewHolder> {
    private Context mContext;
    private List<GirlBean.ResultsBean> mList=new ArrayList<>();
    private OnItemClickListener mListener;

    public RlvAdapter(Context context) {

        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.girl_item, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final GirlBean.ResultsBean bean = mList.get(i);
        Glide.with(mContext).load(bean.getUrl()).into(myViewHolder.mIv);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnItemClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(List<GirlBean.ResultsBean> resultsBeans) {
        if (resultsBeans != null) {
            mList.addAll(resultsBeans);
            notifyDataSetChanged();
        }
    }

    public List<GirlBean.ResultsBean> getList() {
        return mList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mIv=itemView.findViewById(R.id.iv);
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(GirlBean.ResultsBean resultsBean);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }
}
