package com.meller.gmcommitreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    final String repoString = "https://api.github.com/repos/%s/%s/commits";

    Button refreshButton;
    ListView commitListView;

    List<CommitItem> commitItems;
    CommitItemsAdapter adapter;
    private String username;
    private String repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("username");
        repository = getIntent().getStringExtra("repository");

        refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(this);

        AddCommitAdater();

        refreshRepository();
    }

    private void AddCommitAdater()
    {
        commitItems = new ArrayList<CommitItem>();

        adapter = new CommitItemsAdapter(this,
                R.layout.activity_listview, commitItems);

        commitListView = (ListView) findViewById(R.id.commitListView);

        commitListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        refreshRepository();
    }

    private void refreshRepository() {
        try {
            String repoUrlString = String.format(repoString, username, repository);
            GitHubService gitHubService = new GitHubService();
            gitHubService.delegate = this;
            gitHubService.execute(new URL(repoUrlString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showToast(String toastMessage) {
        Context context = getApplicationContext();
        CharSequence text = toastMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void processFinish(List<CommitItem> updatedCommitItems) {
        commitItems.clear();
        commitItems.addAll(updatedCommitItems);

        adapter.notifyDataSetChanged();

        showToast("Repository Refreshed");
    }
}