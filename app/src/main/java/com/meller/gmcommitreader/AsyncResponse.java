package com.meller.gmcommitreader;

import java.util.List;

public interface AsyncResponse {
    void processFinish(List<CommitItem> commitItems);
}
