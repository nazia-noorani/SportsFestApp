package nazianoorani.sportsfestapp.dto;

/**
 * Created by nazianoorani on 19/05/16.
 */
public class EventDto {
    String event;
    String eventImageURL;
    int eventNo;

    public int getEventNo() {
        return eventNo;
    }

    public void setEventNo(int eventNo) {
        this.eventNo = eventNo;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventImageURL() {
        return eventImageURL;
    }

    public void setEventImageURL(String eventImageURL) {
        this.eventImageURL = eventImageURL;
    }
}
