package example.ss.com.commit3.contract;

import java.util.List;

import example.ss.com.commit3.GirlBean;

public interface IContract {
    interface Model {
        void requestData(Callback callback);
    }

    interface View {
        void updateUiSuccess(List<GirlBean.ResultsBean> resultsBeans);
        void updateUiFailed(String msg);
    }

    interface Presenter {
        void getNetWork();
    }
    interface Callback{
        void requestSuccess(List<GirlBean.ResultsBean> resultsBeans);
        void requestFailed(String msg);
    }
}
