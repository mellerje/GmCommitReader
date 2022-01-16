package com.meller.gmcommitreader;

import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GitHubService extends AsyncTask<URL, Integer, List<CommitItem>> {
    final String repoString = "https://api.github.com/repos/mellerje/GmCommitReader/commits";

    public AsyncResponse delegate = null;

    private List<CommitItem> ReadCommits() throws Exception
    {
        URL url = new URL(repoString);
        Scanner scan = new Scanner(url.openStream());

        String repoCommitsJson = "";

        while (scan.hasNext())
        {
            repoCommitsJson += scan.nextLine();
        }

        scan.close();

        return ParseJsonToCommitItems(repoCommitsJson);
    }

    private List<CommitItem> ParseJsonToCommitItems(String repoCommitsJson) throws JSONException {
        List<CommitItem> commitItems = new ArrayList<CommitItem>();
        JSONArray commits = new JSONArray(repoCommitsJson);

        for (int i = 0; i < commits.length(); i++)
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
    protected List<CommitItem> doInBackground(URL... urls) {
        try {
            return ReadCommits();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<CommitItem> result)
    {
        delegate.processFinish(result);
    }
}
