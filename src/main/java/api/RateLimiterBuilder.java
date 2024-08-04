package api;

import algorithm.Algorithm;
import algorithm.SlidingWindow;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class RateLimiterBuilder<RESOURCE> {
    private RateLimiterServer rateLimiterServer;
    private String serviceName;
    private Function<RESOURCE, CompletableFuture<Void>> onAccept;
    private Function<RESOURCE, CompletableFuture<Void>> onReject;
    private ExecutorService executorService;
    private Algorithm algorithm;
    public RateLimiterBuilder<RESOURCE> serviceName(String serviceName){
        this.serviceName = serviceName;
        return this;
    }

    public RateLimiterBuilder<RESOURCE> onAccept(Function<RESOURCE, CompletableFuture<Void>> onAccept){
        this.onAccept = onAccept;
        return this;
    }

    public RateLimiterBuilder<RESOURCE> onReject(Function<RESOURCE, CompletableFuture<Void>> onReject){
        this.onReject = onReject;
        return this;
    }

    public RateLimiterBuilder<RESOURCE> rateLimiterServer(RateLimiterServer rateLimiterServer){
        this.rateLimiterServer = rateLimiterServer;
        return this;
    }

    public RateLimiterBuilder<RESOURCE> algorithm(Algorithm algorithm) throws ExecutionException, InterruptedException {
        this.algorithm = algorithm == null ? new SlidingWindow(rateLimiterServer.readServiceConfig(serviceName).get()) : algorithm;
        return this;
    }

    public RateLimiterBuilder<RESOURCE> thread(int threadCount){
        executorService = Executors.newFixedThreadPool(threadCount);
        return this;
    }

    public RateLimiter<RESOURCE> build(){
        return new RateLimiter<>(rateLimiterServer, serviceName, onAccept, onReject, executorService, algorithm);
    }
}
