package android.com.moneytap.Activities;

import android.com.moneytap.API.APIInterface;
import android.com.moneytap.API.RetrofitClientInstance;
import android.com.moneytap.Models.SearchResponseModel;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.com.moneytap.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WikiSearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private String TAG = "WikiSearchActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_search);
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG,"submit"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG,newText);
                getSearchResponse(newText);
                return false;
            }
        });
    }

    private void getSearchResponse(String newText) {
        APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call<SearchResponseModel> call = apiInterface.getDataList(newText,"10");
        call.enqueue(new Callback<SearchResponseModel>() {
            @Override
            public void onResponse(Call<SearchResponseModel> call, Response<SearchResponseModel> response) {
                Log.d(TAG,response.body().toString());
            }

            @Override
            public void onFailure(Call<SearchResponseModel> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }
}
