package com.meller.gmcommitreader;

public class CommitItem {
    public int commitNumber;
    public String commitHash;
    public String author;
    public String commitMessage;

    public CommitItem(int commitNumber, String hash, String author, String message) {
        this.commitNumber = commitNumber;
        this.commitHash = hash;
        this.author = author;
        this.commitMessage = message;
    }
}
