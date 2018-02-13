package com.andreipiletsky.retrofit.web;

import com.andreipiletsky.retrofit.pojo.Contacts;
import com.andreipiletsky.retrofit.pojo.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("?action=categories")
    Call<List<Contacts>> getContacts();

    @GET("weather?id=616051&APPID=06c0cd0ecefd895a9600f56949423d8e")
    Call<Example> getExample();
}