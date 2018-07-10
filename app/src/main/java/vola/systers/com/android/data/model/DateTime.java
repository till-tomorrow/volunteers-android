package vola.systers.com.android.data.model;

import lombok.Data;

@Data
public class DateTime {
    private Integer year;
    private Integer month;
    private Integer date;
    private Integer hour;
    private Integer minute;
    private Integer second;

    public DateTime(String[] eventDate,String[] eventTime) {
        this.year=Integer.parseInt(eventDate[0]);
        this.month=Integer.parseInt(eventDate[1]);
        this.date=Integer.parseInt(eventDate[2]);
        this.hour=Integer.parseInt(eventTime[0]);
        this.minute=Integer.parseInt(eventTime[1]);
        this.second=Integer.parseInt(eventTime[2]);
    }
}