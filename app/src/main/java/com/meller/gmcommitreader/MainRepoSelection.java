package com.meller.gmcommitreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainRepoSelection extends AppCompatActivity implements View.OnClickListener {

    private Button getCommitsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_repo_selection);

        getCommitsButton = (Button) findViewById(R.id.getCommitsButton);
        getCommitsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        Editable username = ((EditText)findViewById(R.id.inputUsername)).getText();
        intent.putExtra("username", username.toString());
        Editable repository = ((EditText)findViewById(R.id.inputRepository)).getText();;
        intent.putExtra("repository", repository.toString());
        startActivity(intent);
    }

}