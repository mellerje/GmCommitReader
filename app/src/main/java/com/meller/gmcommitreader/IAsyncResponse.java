package com.meller.gmcommitreader;

import java.util.List;

public interface IAsyncResponse {
    void processFinish(List<CommitItem> commitItems);
}
