package iuh.fit.event;

public class EventMessage {
    private String eventType;
    private EventData data;

    public EventMessage() {
    }

    public EventMessage(String eventType, EventData data) {
        this.eventType = eventType;
        this.data = data;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public EventData getData() {
        return data;
    }

    public void setData(EventData data) {
        this.data = data;
    }
}
