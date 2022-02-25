package com.meller.gmcommitreader;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommitListActivity extends AppCompatActivity implements IAsyncResponse {
    final String repoString = "https://api.github.com/repos/%s/%s/commits";

    private Button refreshButton;
    private Button sortButton;
    private ListView commitListView;

    private List<CommitItem> commitItems;
    private CommitItemsAdapter adapter;

    private String username;
    private String repository;

    private Boolean ascending = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_list);

        username = getIntent().getStringExtra("username");
        repository = getIntent().getStringExtra("repository");

        refreshButton = findViewById(R.id.refreshButton);
        sortButton = findViewById(R.id.sort);

        refreshButton.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View view) {
                                                 refreshRepository();
                                             }
                                         }
        );

        sortButton.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View view) {
                                                 sortRepositories();
                                             }
                                         }
        );

        refreshCommitItemsAdapter();
        refreshRepository();
    }


    @Override
    public void processFinish(List<CommitItem> updatedCommitItems) {
        commitItems.clear();
        commitItems.addAll(updatedCommitItems);

        adapter.notifyDataSetChanged();

        showToast("Repository Refreshed");
    }

    private void refreshCommitItemsAdapter() {
        commitItems = new ArrayList<>();

        adapter = new CommitItemsAdapter(this, R.layout.activity_listview, commitItems);

        commitListView = findViewById(R.id.commitListView);

        commitListView.setAdapter(adapter);
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

    private void sortRepositories()
    {
        commitItems.sort(
                new Comparator<CommitItem>() {
                    @Override
                    public int compare(CommitItem commitItem, CommitItem t1) {
                        if(ascending) {
                            return commitItem.commitDate.compareTo((t1.commitDate));
                        }

                        return t1.commitDate.compareTo((commitItem.commitDate));
                    }
                }
        );

        ascending = !ascending;

        adapter.notifyDataSetChanged();
    }

    private void showToast(String toastMessage) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toastMessage, duration);
        toast.show();
    }
}