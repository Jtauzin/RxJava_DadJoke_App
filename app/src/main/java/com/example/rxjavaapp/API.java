package com.example.rxjavaapp;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface API {
	@Headers("Accept: application/json")
	@GET("/")
	Call<DadJokePojo> getJoke();

	@Headers("Accept: application/json")
	@GET("/")
	Observable<DadJokePojo> getJokeObservable();
}
