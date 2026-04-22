package iuh.fit.event;

public class EventData {
    private Long bookingId;

    public EventData() {
    }

    public EventData(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
