package net.ganjoor.service;


import net.ganjoor.model.PoemPojo;
import net.ganjoor.model.PoetPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {

    @GET("poets")
    Call<PoetPojo> poets(@Query("offset") int offset);

    @GET("poems/byCategory/")
    Call<PoemPojo> poems(@Query("id") String categoryId);

}
