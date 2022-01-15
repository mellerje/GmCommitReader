package com.meller.gmcommitreader;

public class CommitItem {
    public String commitHash;
    public String author;
    public String commitMessage;

    public CommitItem(String hash, String author, String message) {
        this.commitHash = hash;
        this.author = author;
        this.commitMessage = message;
    }
}
