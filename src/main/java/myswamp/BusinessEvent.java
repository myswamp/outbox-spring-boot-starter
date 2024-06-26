package myswamp;

public abstract class BusinessEvent {

    private String aggregateId;
    private String aggregateType;
    private String aggregateSubType;
    private Long timestamp;
    private byte[] payload;

    BusinessEvent(String aggregateId, String aggregateType, String aggregateSubType, Long timestamp, byte[] payload) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.aggregateSubType = aggregateSubType;
        this.timestamp = timestamp;
        this.payload = payload;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getAggregateSubType() {
        return aggregateSubType;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
