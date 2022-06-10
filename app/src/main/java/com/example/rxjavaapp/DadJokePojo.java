package com.example.rxjavaapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DadJokePojo {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("joke")
	@Expose
	private String joke;
	@SerializedName("status")
	@Expose
	private Integer status;

	public String getId() {
		return id;
	}

	public String getJoke() {
		return joke;
	}

	public Integer getStatus() {
		return status;
	}
}
