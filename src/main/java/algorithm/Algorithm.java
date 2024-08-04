package algorithm;

import models.ServiceConfiguration;

public abstract class Algorithm {

    private ServiceConfiguration serviceConfiguration;

    public Algorithm(ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
    }

    public abstract void backPressure(long timeStamp);
    public abstract void forwardPressure(long timeStamp);
    public abstract boolean shouldAccept(long timeStamp);
}
