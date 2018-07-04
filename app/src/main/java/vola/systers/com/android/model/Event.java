package vola.systers.com.android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String locationName;
    private String description;
    private String city;
    private String latitude;
    private String longitude;
    private String status;
    private String country;

    public Event(String id, String name, String startDate, String endDate, String startTime, String endTime, String locationName, String description, String city, String country, String longitude, String latitude, String status) {
        this.id=id;
        this.name=name;
        this.startDate=startDate;
        this.endDate=endDate;
        this.startTime=startTime;
        this.endTime=endTime;
        this.locationName=locationName;
        this.description=description;
        this.city=city;
        this.country=country;
        this.status=status;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        locationName = in.readString();
        description = in.readString();
        city = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        status = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(locationName);
        dest.writeString(description);
        dest.writeString(city);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(status);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocationName() { return locationName; }

    public String getDescription() { return description; }

    public String getCity() { return city; }

    public String getCountry() { return country; }

    public String getStatus() { return status; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

}