package com.ttl.databinding.Interfaces;


import com.ttl.databinding.Model.EidGreetingsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mirza Adil on 3/12/2017.
 */

public interface APIInterface {

    @GET(".")
    Call<List<EidGreetingsModel>> getEidText();

}
