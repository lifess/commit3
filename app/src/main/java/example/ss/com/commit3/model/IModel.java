package example.ss.com.commit3.model;

import java.util.List;

import example.ss.com.commit3.ApiService;
import example.ss.com.commit3.GirlBean;
import example.ss.com.commit3.contract.IContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class IModel implements IContract.Model {
    @Override
    public void requestData(final IContract.Callback callback) {
        Retrofit build = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.mUrl)
                .build();
        build.create(ApiService.class).getGirl()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GirlBean girlBean) {
                        List<GirlBean.ResultsBean> results = girlBean.getResults();
                        callback.requestSuccess(results);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
