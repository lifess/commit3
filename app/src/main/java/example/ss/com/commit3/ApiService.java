package example.ss.com.commit3;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String mUrl="https://gank.io/";
    @GET("api/data/%E7%A6%8F%E5%88%A9/10/1")
    Observable<GirlBean> getGirl();
}
