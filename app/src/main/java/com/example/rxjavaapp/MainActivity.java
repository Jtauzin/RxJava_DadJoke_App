package com.example.rxjavaapp;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private String encryptedjoke = "";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button jokeButton = findViewById(R.id.get_joke_btn);
		Button rxJokeButton = findViewById(R.id.get_rxjoke_btn);
		Button decryptButton = findViewById(R.id.decrypt_btn);
		jokeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (final View v) {
				getJoke();
			}
		});

		rxJokeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (final View v) {
				getJokeObservable();
			}
		});
		decryptButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (final View v) {
				new JokeDialogFragment(decrypt(encryptedjoke)).show(getSupportFragmentManager(), "Joke Dialog");
			}
		});
	}

	void getJoke() {
		API api = RetroClient.getInstance().create(API.class);
		Call<DadJokePojo> jokeCall = api.getJoke();
		jokeCall.enqueue(new Callback<DadJokePojo>() {
			@Override
			public void onResponse (final Call<DadJokePojo> call, final Response<DadJokePojo> response) {
				new JokeDialogFragment(response.body().getJoke()).show(getSupportFragmentManager(), "Joke Dialog");
			}



			@Override
			public void onFailure (final Call<DadJokePojo> call, final Throwable t) {
				Toast.makeText(MainActivity.this, "fail: " + t, Toast.LENGTH_SHORT).show();
				Log.e("Test", "onFailure: " + t);
			}
		});
	}

	void getJokeObservable() {
		API api = RetroClient.getRxInstance().create(API.class);
		Observable<DadJokePojo> jokeObservable = api.getJokeObservable();
		jokeObservable.subscribeOn(Schedulers.io()).map(x -> {encryptedjoke = encrypt(x.getJoke()); return encryptedjoke;})
				.subscribe(result -> {new JokeDialogFragment(result).show(getSupportFragmentManager(), "Joke Dialog");},
						   Throwable::printStackTrace);
	}

	private String encrypt(String someString) {
		int[] key = new int[] {3, 5, 1, 7, 2};
		StringBuilder encryptedString = new StringBuilder(someString);
		int j = 0;
		for (int i = 0; i < someString.length(); i++) {
			j = i % key.length;
			encryptedString.setCharAt(i, (char) (someString.charAt(i) + key[j]));
		}
		return encryptedString.toString();
	}

	private String decrypt(String someString) {
		int[] key = new int[] {3, 5, 1, 7, 2};
		StringBuilder encryptedString = new StringBuilder(someString);
		int j = 0;
		for (int i = 0; i < someString.length(); i++) {
			j = i % key.length;
			encryptedString.setCharAt(i, (char) (someString.charAt(i) - key[j]));
		}
		return encryptedString.toString();
	}
}