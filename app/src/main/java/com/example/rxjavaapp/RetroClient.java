package com.example.rxjavaapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
	private static Retrofit instance = null;
	private static Retrofit instanceRxJava;
	private OkHttpClient client;

	public RetroClient() {
		client = new OkHttpClient.Builder().build();
	}

	public static Retrofit getInstance() {
		if (instance == null) {
			String myURL = "https://icanhazdadjoke.com";
			instance = new Retrofit.Builder().baseUrl(myURL)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
					.build();
		}
		return instance;
	}
	public static Retrofit getRxInstance() {
		if (instanceRxJava == null) {
			Gson gson = new GsonBuilder()
					.setLenient()
					.create();
			String myURL = "https://icanhazdadjoke.com";
			instanceRxJava = new Retrofit.Builder().baseUrl(myURL)
					.addConverterFactory(GsonConverterFactory.create(gson))
					.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
					.build();
		}
		return instanceRxJava;
	}
}
