package uk.co.pauldavies83.compoundinterestcalculator.concurrency;

public interface ThreadScheduler {
    void executeOnBackgroundThread(Runnable runnable);
    void executeOnUiThread(Runnable runnable);
}
