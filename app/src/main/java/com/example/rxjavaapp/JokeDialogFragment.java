package com.example.rxjavaapp;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class JokeDialogFragment extends DialogFragment {
	private String jokeText;

	public JokeDialogFragment(String jokeText) {
		this.jokeText = jokeText;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog (@Nullable final Bundle savedInstanceState) {
		return new AlertDialog.Builder(requireContext())
				.setMessage(jokeText)
				.setPositiveButton("Close", (dialog, which) -> {dialog.dismiss();})
				.create();
	}
}
