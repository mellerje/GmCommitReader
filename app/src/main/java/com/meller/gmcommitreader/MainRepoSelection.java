package com.meller.gmcommitreader;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainRepoSelection extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_repo_selection);

        Button getCommitsButton = findViewById(R.id.getCommitsButton);
        getCommitsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        Editable username = ((EditText)findViewById(R.id.inputUsername)).getText();
        Editable repository = ((EditText)findViewById(R.id.inputRepository)).getText();

        if(username.toString().isEmpty() || repository.toString().isEmpty())
        {
            AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
            errorDialog.setMessage("Username or repository missing.");
            errorDialog.setTitle("Missing Information");
            errorDialog.setPositiveButton("OK", null);
            errorDialog.create().show();
            return;
        }

        intent.putExtra("username", username.toString());
        intent.putExtra("repository", repository.toString());
        startActivity(intent);
    }

}