package com.example.agendamu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class EventModel implements Parcelable {
    private int id, duration, picId;
    private String title, location, guest, notes;
    private Date dateTime;

    public EventModel() {
    }

    public EventModel(int id, int duration, int picId, String title, String location, String guest, String notes, Date dateTime) {
        this.id = id;
        this.duration = duration;
        this.picId = picId;
        this.title = title;
        this.location = location;
        this.guest = guest;
        this.notes = notes;
        this.dateTime = dateTime;
    }

    protected EventModel(Parcel in) {
        id = in.readInt();
        duration = in.readInt();
        picId = in.readInt();
        title = in.readString();
        location = in.readString();
        guest = in.readString();
        notes = in.readString();
    }

    public static final Creator<EventModel> CREATOR = new Creator<EventModel>() {
        @Override
        public EventModel createFromParcel(Parcel in) {
            return new EventModel(in);
        }

        @Override
        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPicUid() {
        return picId;
    }

    public void setPicUid(int picUid) {
        this.picId = picUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "id=" + id +
                ", duration=" + duration +
                ", picUid='" + picId + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", guest='" + guest + '\'' +
                ", notes='" + notes + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public String getDayAsString(){
        return new SimpleDateFormat("EEE").format(this.dateTime);
    }

    public String getDateAsString(){
        return DateFormat.getDateInstance().format(this.dateTime);
    }

    public String getStartTimeAsString(){
        return DateFormat.getTimeInstance().format(this.dateTime);
    }

    public Date getEndTime(){
        return Date.from(this.dateTime.toInstant().plus(Duration.ofMinutes(this.duration)));
    }

    public String getEndTimeAsString(){
        return DateFormat.getTimeInstance().format(this.getEndTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(duration);
        parcel.writeInt(picId);
        parcel.writeString(title);
        parcel.writeString(location);
        parcel.writeString(guest);
        parcel.writeString(notes);
        parcel.writeSerializable(dateTime);
    }
}