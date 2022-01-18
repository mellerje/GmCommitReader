package com.meller.gmcommitreader;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GitHubService extends AsyncTask<URL, Integer, List<CommitItem>> {
    public IAsyncResponse delegate = null;

    @Override
    protected List<CommitItem> doInBackground(URL... urls) {
        try {
            return ReadCommits(urls[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected List<CommitItem> ReadCommits(URL url) {
        if(url == null) {
            return new ArrayList<>();
        }

        try {
            Scanner scan = new Scanner(url.openStream());

            StringBuilder repoCommitsJson = new StringBuilder();

            while (scan.hasNext()) {
                repoCommitsJson.append(scan.nextLine());
            }

            scan.close();

            return ParseJsonToCommitItems(repoCommitsJson.toString());
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }

    protected List<CommitItem> ParseJsonToCommitItems(String repoCommitsJson) throws JSONException {
        List<CommitItem> commitItems = new ArrayList<>();

        if(repoCommitsJson.isEmpty()) {
            return commitItems;
        }

        JSONArray commits = new JSONArray(repoCommitsJson);

        for (int i = commits.length() - 1; i >= 0; i--)
        {
            String hash = commits.getJSONObject(i).getString("sha");
            JSONObject commit = commits.getJSONObject(i).getJSONObject("commit");
            String commitAuthor = commit.getJSONObject("author").getString("name");
            String commitMessage = commit.getString("message");

            commitItems.add(new CommitItem(hash, commitAuthor, commitMessage));
        }

        return commitItems;
    }

    @Override
    protected void onPostExecute(List<CommitItem> result) {
        delegate.processFinish(result);
    }
}
