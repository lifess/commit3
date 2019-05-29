package example.ss.com.commit3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import example.ss.com.commit3.contract.IContract;
import example.ss.com.commit3.presenter.IPresenter;

//郭帅帅 1810A
public class MainActivity extends AppCompatActivity implements IContract.View {

    private RecyclerView mRlv;
    private RlvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMvp();
    }

    private void initMvp() {
        IPresenter iPresenter = new IPresenter(this);
        iPresenter.getNetWork();
    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter = new RlvAdapter(this);
        mRlv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RlvAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(GirlBean.ResultsBean resultsBean) {
                List<GirlBean.ResultsBean> list = mAdapter.getList();
//                list.add(resultsBean);
                MyEventBus myEventBus = new MyEventBus();
                myEventBus.mResultsBeans=list;
                EventBus.getDefault().postSticky(myEventBus);
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void updateUiSuccess(List<GirlBean.ResultsBean> resultsBeans) {
        mAdapter.updateData(resultsBeans);
    }

    private static final String TAG = "MainActivity";
    @Override
    public void updateUiFailed(String msg) {
        Log.d(TAG, "updateUiFailed: "+msg);
    }
}
