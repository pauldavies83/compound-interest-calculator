package uk.co.pauldavies83.compoundinterestcalculator.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.co.pauldavies83.compoundinterestcalculator.concurrency.ThreadScheduler;
import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.data.LocalCompoundCalculatorService;

public final class CompoundInterestCalculatorApplication extends Application implements AppContainer {

    private CompoundCalculatorService service;

    @Override
    public void onCreate() {
        super.onCreate();
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        service = new LocalCompoundCalculatorService(new ApplicationThreadScheduler(executorService, mainThreadHandler));
    }

    @Override
    public CompoundCalculatorService getCompoundCalculatorService() {
        return service;
    }


    private class ApplicationThreadScheduler implements ThreadScheduler {
        private final ExecutorService executorService;
        private final Handler mainThreadHandler;

        ApplicationThreadScheduler(ExecutorService executorService, Handler mainThreadHandler) {
            this.executorService = executorService;
            this.mainThreadHandler = mainThreadHandler;
        }

        @Override
        public void executeOnBackgroundThread(Runnable runnable) {
            executorService.execute(runnable);
        }

        @Override
        public void executeOnUiThread(Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }
}
