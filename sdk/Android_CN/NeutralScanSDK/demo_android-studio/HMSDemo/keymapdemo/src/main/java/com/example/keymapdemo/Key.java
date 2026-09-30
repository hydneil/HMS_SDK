package com.example.keymapdemo;

import java.io.Serializable;

public class Key implements Serializable {
    private int type = 0;
    private int keycode = 0;
    private int mapkeycode = 0;
    private String activity = "";
    private String activityExtra = "";
    private String broadcastDown= "";
    private String broadcastDownExtra= "";
    private String broadcastUp= "";
    private String broadcastUpExtra= "";
    private int wakeup = 0;
    public Key(){
        super();
    }
    @Override
    public String toString() {
        return "Key{" +
                "keycode=" + keycode +
                ", mapkeycode=" + mapkeycode +
                ", activity='" + activity + '\'' +
                ", activityExtra='" + activityExtra + '\'' +
                ", broadcastDown='" + broadcastDown + '\'' +
                ", broadcastDownExtra='" + broadcastDownExtra + '\'' +
                ", broadcastUp='" + broadcastUp + '\'' +
                ", broadcastUpExtra='" + broadcastUpExtra + '\'' +
                ", type=" + type +
                '}';
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getKeycode() {
        return keycode;
    }

    public void setKeycode(int keycode) {
        this.keycode = keycode;
    }

    public int getMapkeycode() {
        return mapkeycode;
    }

    public void setMapkeycode(int mapkeycode) {
        this.mapkeycode = mapkeycode;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityExtra() {
        return activityExtra;
    }

    public void setActivityExtra(String activityExtra) {
        this.activityExtra = activityExtra;
    }

    public String getBroadcastDown() {
        return broadcastDown;
    }

    public void setBroadcastDown(String broadcastDown) {
        this.broadcastDown = broadcastDown;
    }

    public String getBroadcastDownExtra() {
        return broadcastDownExtra;
    }

    public void setBroadcastDownExtra(String broadcastDownExtra) {
        this.broadcastDownExtra = broadcastDownExtra;
    }

    public String getBroadcastUp() {
        return broadcastUp;
    }

    public void setBroadcastUp(String broadcastUp) {
        this.broadcastUp = broadcastUp;
    }

    public String getBroadcastUpExtra() {
        return broadcastUpExtra;
    }

    public void setBroadcastUpExtra(String broadcastUpExtra) {
        this.broadcastUpExtra = broadcastUpExtra;
    }

    public int getWakeup() {
        return wakeup;
    }

    public void setWakeup(int wakeup) {
        this.wakeup = wakeup;
    }
}
