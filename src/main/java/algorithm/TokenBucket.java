package algorithm;

import models.ServiceConfiguration;

public class TokenBucket extends Algorithm{

    public TokenBucket(ServiceConfiguration serviceConfiguration) {
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
