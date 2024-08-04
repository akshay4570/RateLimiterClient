package algorithm;

import models.RLTimeUnit;
import models.ServiceConfiguration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SlidingWindow extends Algorithm{

    private double threshold;
    private RLTimeUnit timeUnit;
    private List<Long> listTimeStamp;

    public SlidingWindow(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
        this.threshold = serviceConfiguration.getAmount();
        this.timeUnit = serviceConfiguration.getTimeUnit();
        this.listTimeStamp = new ArrayList<>();
    }

    @Override
    public void backPressure(long timeStamp) {
        threshold -= timeStamp > System.currentTimeMillis() + 100 ? 1 : threshold*0.1;
    }

    @Override
    public void forwardPressure(long timeStamp) {
        threshold += 0.1;
    }

    @Override
    public boolean shouldAccept(long timeStamp) {
        listTimeStamp = listTimeStamp.stream()
                .filter(this::hasExpired)
                .collect(Collectors.toList());
        if(threshold > listTimeStamp.size()){
            listTimeStamp.add(timeStamp);
            return true;
        }else{
            return false;
        }
    }

    private boolean hasExpired(Long ts) {
        long timeElapsed = System.currentTimeMillis() - ts;
        long maxTimeAllowed = Duration.of(1, ChronoUnit.valueOf(timeUnit.toString())).toMillis();
        return timeElapsed > maxTimeAllowed;
    }
}
