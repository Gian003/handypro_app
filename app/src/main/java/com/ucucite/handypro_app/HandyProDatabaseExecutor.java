package com.ucucite.handypro_app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandyProDatabaseExecutor {
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);
}
