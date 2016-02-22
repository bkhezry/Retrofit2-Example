package ir.bkhezry.retrofit2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import ir.bkhezry.retrofit2.Model.CustomListAdapter;
import ir.bkhezry.retrofit2.Model.Movie;

import ir.bkhezry.retrofit2.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class HiveActivity extends AppCompatActivity {
    private static final String url = "http://api.androidhive.info/json/movies.json";
    @Bind(R.id.list)
    ListView listView;
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<>();
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive);
        ButterKnife.bind(this);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.androidhive.info/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Movie>> call = apiService.getMoviesService();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                hidePDialog();
                try {
                    if (response.isSuccess()) {
                        Toast.makeText(HiveActivity.this, "Success", Toast.LENGTH_LONG).show();
                        movieList.clear();
                        movieList.addAll(response.body());
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(HiveActivity.this, "Failed! : " + response.errorBody().string(), Toast.LENGTH_LONG).show();


                    }
                } catch (IOException e) {
                    Log.e("bkhezry", "IOException:"+e.getMessage().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                hidePDialog();
                if (t != null) {
                    Toast.makeText(HiveActivity.this, "Failed! OnFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
