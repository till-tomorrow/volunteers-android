package vola.systers.com.android.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Event implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
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

    public Event(int id, String name, String startDate, String endDate, String startTime, String endTime, String locationName, String description, String city, String country, String longitude, String latitude, String status) {
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
        id = in.readInt();
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

    public int getId() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(startDate);
        parcel.writeString(endDate);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeString(locationName);
        parcel.writeString(description);
        parcel.writeString(city);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(status);
        parcel.writeString(country);
    }
}