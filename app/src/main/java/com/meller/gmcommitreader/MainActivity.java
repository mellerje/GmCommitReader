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
    final String repoString = "https://api.github.com/repos/mellerje/GmCommitReader/commits";

    Button refreshButton;
    ListView commitListView;
    String [] commitItemsArray = { "Test1", "Test2" };
    String [] commitSubItemsArray = { "SubTest1", "SubTest2" };
    List<CommitItem> commitItems;
    CommitItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(this);

        AddCommitAdater();

        refreshRepository();
    }

    private void AddCommitAdater()
    {
        commitItems = new ArrayList<CommitItem>();

        commitItems.add(new CommitItem("Hash", "Author", "Message"));
        commitItems.add(new CommitItem("Hash2", "Author2", "Message2"));

        adapter = new CommitItemsAdapter(this,
                R.layout.activity_listview, commitItems);

        commitListView = (ListView) findViewById(R.id.commitListView);

        commitListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        try {
            GitHubService gitHubService = new GitHubService();
            gitHubService.delegate = this;
            gitHubService.execute(new URL(repoString));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void processFinish(List<CommitItem> updatedCommitItems) {
        commitItems.clear();
        commitItems.addAll(updatedCommitItems);

        adapter.notifyDataSetChanged();

        refreshRepository();
    }
}