package api;

public class Limit {
    private int amount;
    private String timeUnit;

    public Limit(int amount, String timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    public int getAmount() {
        return amount;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}
