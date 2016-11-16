package Eliza.Message;

public class RatedMessage extends Message implements Rateable {

    private final double quality;

    public RatedMessage(String message, double quality) {
        super(message);
        this.quality = quality;
    }

    public RatedMessage(IMessage message, double quality) {
        this(message.getMessage(), quality);
    }

    @Override
    public String toString() {
        return String.format("[%f]: %s", getQuality(), getMessage());
    }

    @Override
    public double getQuality() {
        return quality;
    }

}
