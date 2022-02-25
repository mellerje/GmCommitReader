package com.meller.gmcommitreader;

public class CommitItem {
    public int commitNumber;
    public String commitHash;
    public String author;
    public String commitDate;
    public String commitMessage;

    public CommitItem(int commitNumber, String hash, String date, String author, String message) {
        this.commitNumber = commitNumber;
        this.commitHash = hash;
        this.commitDate = date;
        this.author = author;
        this.commitMessage = message;
    }
}
