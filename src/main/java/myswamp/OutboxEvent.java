package myswamp;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Entity
@Table(name = "outbox_events")
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String aggregateId;

    @NotNull
    private String aggregateType;

    @NotNull
    private String aggregateSubType;

    @NotNull
    private Long eventTimestamp;

    @NotNull
    private byte[] payload;


    public OutboxEvent() {
    }

    public OutboxEvent(String aggregateId, String aggregateSubType, String aggregateType, byte[] payload, Long eventTimestamp) {
        this.aggregateId = aggregateId;
        this.aggregateSubType = aggregateSubType;
        this.aggregateType = aggregateType;
        this.payload = payload;
        this.eventTimestamp = eventTimestamp;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateSubType() {
        return aggregateSubType;
    }

    public void setAggregateSubType(String aggregateSubType) {
        this.aggregateSubType = aggregateSubType;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public Long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }
}
