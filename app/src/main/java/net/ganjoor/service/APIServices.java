package net.ganjoor.service;


import net.ganjoor.model.PoetPojo;

import retrofit2.Call;
import retrofit2.http.POST;

public interface APIServices {

    @POST("poets")
    Call<PoetPojo> poets();

}
