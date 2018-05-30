package com.ttl.databinding.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ttl.databinding.Interfaces.APIInterface;
import com.ttl.databinding.Model.EidGreetingsModel;
import com.ttl.databinding.R;
import com.ttl.databinding.Adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private APIInterface apiInterface;
    private List<EidGreetingsModel> data = new ArrayList<>();

    private ProgressDialog mProgressDialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL));
        callApi();

    }

    private void callApi() {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Eid Greetings");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Please wait for loading...");
        mProgressDialog.show();

            apiInterface = getClient().create(APIInterface.class);
            Call<List<EidGreetingsModel>> call = apiInterface.getEidText();
            call.enqueue(new Callback<List<EidGreetingsModel>>() {
                @Override
                public void onResponse(Call<List<EidGreetingsModel>> call, Response<List<EidGreetingsModel>> response) {
                    mProgressDialog.dismiss();
                    if (response.body() != null) {
                        data = response.body();
                        recyclerView.setAdapter(new RecyclerAdapter(MainActivity.this , data));

                    }
                }

                @Override
                public void onFailure(Call<List<EidGreetingsModel>> call, Throwable t) {
                    mProgressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

    private Retrofit getClient() {
        Retrofit retrofit;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://text-46944.firebaseio.com/.json/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

}
