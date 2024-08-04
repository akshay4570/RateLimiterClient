package api;

import models.ServiceConfiguration;
import request.ReadConfigRequest;
import request.ReadRequest;
import request.UpdateConfigRequest;
import request.UpdateRequest;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RateLimiterServer {

    private String ipAddress;

    public CompletableFuture<Map<String, Limit>> readKey(String serviceName){
        new ReadRequest(serviceName, UUID.randomUUID().toString());
        //Hit the Server Endpoint and Read Key Metrics from the Rate Limiter Server
        return null;
    }

    public CompletableFuture<Void> updateKey(String serviceName, ServiceConfiguration serviceConfiguration){
        new UpdateRequest(serviceName, UUID.randomUUID().toString(), new Limit(serviceConfiguration.getAmount(), serviceConfiguration.getTimeUnit().toString()));
        //Hit the Server Endpoint and Update Key Metrics to the Rate Limiter Server
        return null;
    }

    public CompletableFuture<ServiceConfiguration> readServiceConfig(String serviceName){
        new ReadConfigRequest(serviceName, UUID.randomUUID().toString());
        //Hit the Server Endpoint and Read Service Configuration Requests from the Server
        return null;
    }

    public CompletableFuture<Void> updateServiceConfig(String serviceName){
        new UpdateConfigRequest(serviceName, UUID.randomUUID().toString());
        //Hit the Server Endpoint and Update Service Configuration Requests from the Server
        return null;
    }
}
