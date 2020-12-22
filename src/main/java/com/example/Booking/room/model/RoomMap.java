package com.example.Booking.room.model;

public class RoomMap {
    private String d;
    private String transform;
    private String x;
    private String y;

    public RoomMap(String d, String transform, String x, String y) {
        this.d = d;
        this.transform = transform;
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getD() {
        return d;
    }

    public String getTransform() {
        return transform;
    }
}
