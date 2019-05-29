package example.ss.com.commit3.presenter;

import java.util.List;

import example.ss.com.commit3.GirlBean;
import example.ss.com.commit3.contract.IContract;
import example.ss.com.commit3.model.IModel;

public class IPresenter implements IContract.Presenter {
    private IContract.View mView;
    private final IModel mIModel;

    public IPresenter(IContract.View view) {
        mIModel = new IModel();
        mView = view;
    }

    @Override
    public void getNetWork() {
        mIModel.requestData(new IContract.Callback() {
            @Override
            public void requestSuccess(List<GirlBean.ResultsBean> resultsBeans) {
                mView.updateUiSuccess(resultsBeans);
            }

            @Override
            public void requestFailed(String msg) {
                mView.updateUiFailed(msg);
            }
        });
    }
}
