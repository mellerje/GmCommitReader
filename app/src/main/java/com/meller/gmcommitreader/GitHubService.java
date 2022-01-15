package com.meller.gmcommitreader;

import android.os.AsyncTask;

import java.net.URL;
import java.util.Scanner;

public class GitHubService extends AsyncTask<URL, Integer, Long> {
    final String gitHubToken = "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";
    final String repoString = "https://api.github.com/repos/mellerje/GmCommitReader/commits";

    private boolean ReadCommits() throws Exception
    {
        URL url = new URL(repoString);
        Scanner scan = new Scanner(url.openStream());

        String repoCommitsJson = "";

        while (scan.hasNext())
        {
            repoCommitsJson += scan.nextLine();
        }

        scan.close();

        return true;
    }

    @Override
    protected Long doInBackground(URL... urls) {
        try {
            ReadCommits();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long amount = 0;
        return amount;
    }
}
