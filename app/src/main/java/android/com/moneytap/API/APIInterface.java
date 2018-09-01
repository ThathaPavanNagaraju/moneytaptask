package android.com.moneytap.API;

import android.com.moneytap.Models.SearchResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pavan Nagaraju on 01-Sep-18.
 */

public interface APIInterface {
    @GET("api.php/action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description")
    Call<SearchResponseModel> getDataList(@Query("gpssearch") String name , @Query("gpslimit") String gpsLimit);
}
