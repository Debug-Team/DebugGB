package banksystem;

public class Record {
    private String source;
    private String target;
    private double amount;

    public Record(String source, String target, double amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    @Override
    public String toString() {
//        return "Source: " + source + ", Target: " + target + ", Amount: " + amount;
        return source + "," + target + "," + amount;
    }
}
