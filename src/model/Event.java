package model;
import java.time.LocalDateTime;


public class Event {
    private LocalDateTime eventDate;
    private double duration;

    public Event(LocalDateTime eventDate, double duration) {
        this.EventDate = eventDate;
        this.duration = duration;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}