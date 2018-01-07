package com.barun.example.job;

public class RecordTracker {

	private boolean moreRecords = true;

    void noMoreRecords() {
        moreRecords = false;
    }

    boolean moreRecords() {
        return moreRecords;
    }
}
