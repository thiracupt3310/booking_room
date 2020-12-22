package com.example.Booking.room.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.TemporalType;
import java.rmi.server.ExportException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private int room_id;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int status;
    private String username;

    public void setRoom_id(int room_id) {
        this.room_id = room_id;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Transaction(int room_id, String date, Time startTime, Time endTime, int status) {
        this.room_id = room_id;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = new Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public void setDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = new Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoom_id() {
        return room_id;
    }

    public Date getDate() {
        return date;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;

    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "room_id=" + room_id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }
}
