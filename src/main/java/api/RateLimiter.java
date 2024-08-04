package api;

import algorithm.Algorithm;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

public class RateLimiter<RESOURCE> {
    private RateLimiterServer rateLimiterServer;
    private String serviceName;
    private Function<RESOURCE, CompletableFuture<Void>> onAccept;
    private Function<RESOURCE, CompletableFuture<Void>> onReject;
    private ExecutorService executorService;
    private Algorithm algorithm;

    public RateLimiter(RateLimiterServer rateLimiterServer, String serviceName, Function<RESOURCE, CompletableFuture<Void>> onAccept, Function<RESOURCE, CompletableFuture<Void>> onReject, ExecutorService executorService, Algorithm algorithm) {
        this.rateLimiterServer = rateLimiterServer;
        this.serviceName = serviceName;
        this.onAccept = onAccept;
        this.onReject = onReject;
        this.executorService = executorService;
        this.algorithm = algorithm;
    }

    //Just an example of using the Server Methods Exposed, similarly we can use other methods as well
    public CompletableFuture<Map<String, Limit>> readKey(String json){
        return CompletableFuture.supplyAsync(() -> rateLimiterServer.readKey(json), executorService)
                .thenCompose(Function.identity());
    }

    public CompletableFuture<Void> onAccept(RESOURCE resource) {
        algorithm.forwardPressure(System.currentTimeMillis());
        return onAccept.apply(resource);
    }

    public CompletableFuture<Void> onReject(RESOURCE resource) {
        algorithm.backPressure(System.currentTimeMillis());
        return onReject.apply(resource);
    }

    public boolean shouldAccept(RESOURCE resource) {
        return algorithm.shouldAccept(System.currentTimeMillis());
    }
}
