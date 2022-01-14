package com.meller.gmcommitreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(this);
    }

    private boolean connectToRepository()
    {
        return true;
    }


    @Override
    public void onClick(View view) {
        refreshRepository();
    }

    private void refreshRepository() {
        showToast("Repository Refreshed");
    }

    private void showToast(String toastMessage) {
        Context context = getApplicationContext();
        CharSequence text = toastMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}