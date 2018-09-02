package android.com.moneytap.Activities;

import android.app.ProgressDialog;
import android.com.moneytap.API.APIInterface;
import android.com.moneytap.API.RetrofitClientInstance;
import android.com.moneytap.API.Volley.AppController;
import android.com.moneytap.Adapters.RVListAdapter;
import android.com.moneytap.Models.SearchResponseModel;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.com.moneytap.R;
import android.widget.ListAdapter;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WikiSearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private String TAG = "WikiSearchActivity";
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_search);
        searchView = (SearchView) findViewById(R.id.search_view);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
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
        String url = "https://en.wikipedia.org//w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description"+
                "&gpssearch="+newText+"&gpslimit=10";
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG,response.toString());
                        bindData(response);
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.getMessage());
                    }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void bindData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            SearchResponseModel responseModel = new Gson().fromJson(response,SearchResponseModel.class);
            Log.d(TAG,responseModel.toString());
            RVListAdapter rvListAdapter = null;
            if(responseModel.getQuery() != null) {
                rvListAdapter = new RVListAdapter(this, responseModel.getQuery().getPages());
            }
            rvList.setLayoutManager(new LinearLayoutManager(this));
            rvList.setAdapter(rvListAdapter);
            //rvListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}