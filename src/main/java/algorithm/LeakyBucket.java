package algorithm;

import models.ServiceConfiguration;

public class LeakyBucket extends Algorithm{

    public LeakyBucket(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    @Override
    public void backPressure(long timeStamp) {

    }

    @Override
    public void forwardPressure(long timeStamp) {

    }

    @Override
    public boolean shouldAccept(long timeStamp) {
        return false;
    }
}
