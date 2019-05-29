package example.ss.com.commit3;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewPager mVp;
    private TextView mTv;
    private VpAdapter mAdapter;
    private List<GirlBean.ResultsBean> mResultsBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        EventBus.getDefault().register(this);
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMyEventBus(MyEventBus myEventBus){
        mResultsBeans = myEventBus.mResultsBeans;
        mAdapter.addData(mResultsBeans);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTv = (TextView) findViewById(R.id.tv);
        mAdapter = new VpAdapter(this);
        mVp.setAdapter(mAdapter);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mTv.setText("第"+(i+1)+"页,"+"共"+mResultsBeans.size()+"页");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
