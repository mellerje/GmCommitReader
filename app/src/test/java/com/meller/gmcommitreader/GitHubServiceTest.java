package com.meller.gmcommitreader;

import org.json.JSONException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.*;

import androidx.annotation.NonNull;

public class GitHubServiceTest {

    @Test
    public void gitHubServices_ReadCommits_returns_zeroItems_nullUrl() {

        List<CommitItem> items = new GitHubService().ReadCommits(null);

        assertEquals(items.size(), 0);
    }

    @Test
    public void gitHubServices_ReadCommits_returns_zeroItems_invalidUrl() {

        URL invalidUrl = null;
        try {
            invalidUrl = new URL("http:\\invalidUrl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        List<CommitItem> items = new GitHubService().ReadCommits(invalidUrl);

        assertEquals(items.size(), 0);
    }

    @Test (expected = Exception.class)
    public void gitHubServices_ParseJsonToCommitItems_zeroItems_nullString() throws JSONException {
        List<CommitItem> items = new GitHubService().ParseJsonToCommitItems(null);
    }

    @Test
    public void gitHubServices_ParseJsonToCommitItems_zeroItems_emptyString()
    {
        List<CommitItem> items = null;
        try {
            items = new GitHubService().ParseJsonToCommitItems("");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertEquals(items.size(), 0);
    }


    @Test(expected = Exception.class)
    public void gitHubServices_ParseJsonToCommitItems_zeroItems_improperString() throws JSONException, IOException {
        List<CommitItem> items = null;

        String improperJsonString = getStringFromFile("GitHubServicesData/improperJsonString.json");

        items = new GitHubService().ParseJsonToCommitItems(improperJsonString);
    }

    @NonNull
    private String getStringFromFile(String filePath) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        String improperJsonString = "";

        for (String line; (line = reader.readLine()) != null;) {
            improperJsonString += line.trim();
        }
        return improperJsonString;
    }

    @Test
    public void gitHubServices_ParseJsonTOCommitItems_expect2Items() throws JSONException, IOException {
        String jsonString = getStringFromFile("GitHubServicesData/proper2ItemJsonString.json");

        List<CommitItem> items = new GitHubService().ParseJsonToCommitItems(jsonString);

        assertEquals(items.size(), 2);
    }

    @Test
    public void gitHubServices_ParseJsonTOCommitItems_parse2ItemsCorrect() throws JSONException, IOException {
        String jsonString = getStringFromFile("GitHubServicesData/proper2ItemJsonString.json");

        List<CommitItem> items = new GitHubService().ParseJsonToCommitItems(jsonString);

        assertEquals(items.get(0).author, "testUser");
        assertEquals(items.get(0).commitHash, "0987654321");
        assertEquals(items.get(0).commitMessage, "Test Message 2.");
        assertEquals(items.get(1).author, "testUser");
        assertEquals(items.get(1).commitHash, "1234567890");
        assertEquals(items.get(1).commitMessage, "Test Message 1.");
    }
}