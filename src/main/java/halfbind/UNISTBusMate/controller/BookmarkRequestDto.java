package halfbind.UNISTBusMate.controller;

import java.util.List;

import halfbind.UNISTBusMate.domain.Day;
import lombok.Data;

public class BookmarkRequestDto {
    private Long busId;
    private String userName;
    private List<Day> days;

    public String getUserName() {
        return userName;
    }

    public Long getBusId() {
        return busId;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
