package com.meller.gmcommitreader;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        String username = ((EditText)findViewById(R.id.inputUsername)).getText().toString();
        String repository = ((EditText)findViewById(R.id.inputRepository)).getText().toString();

        if(username.isEmpty() || repository.isEmpty())
        {
            showErrorDialog();
            return;
        }

        Intent intent = new Intent(this, CommitListActivity.class);

        // Pass needed variables to the commit list activity
        intent.putExtra("username", username);
        intent.putExtra("repository", repository);

        startActivity(intent);
    }

    private void showErrorDialog() {
        AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);
        errorDialog.setMessage("Username or repository missing.");
        errorDialog.setTitle("Missing Information");
        errorDialog.setPositiveButton("OK", null);
        errorDialog.create().show();
    }

}