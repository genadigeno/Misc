package geno.micros.configclient;

import java.io.Serializable;

public class ResponseObject implements Serializable {

    public ResponseObject() {}

    public ResponseObject(String rate, String laneCount, String tallStart, String connString) {
        this.rate = rate;
        this.laneCount = laneCount;
        this.tallStart = tallStart;
        this.connString = connString;
    }

    private String rate;

    private String laneCount;

    private String tallStart;

    private String connString;

    public String getConnString() {
        return connString;
    }

    public void setConnString(String connString) {
        this.connString = connString;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getLaneCount() {
        return laneCount;
    }

    public void setLaneCount(String laneCount) {
        this.laneCount = laneCount;
    }

    public String getTallStart() {
        return tallStart;
    }

    public void setTallStart(String tallStart) {
        this.tallStart = tallStart;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "rate='" + rate + '\'' +
                ", laneCount='" + laneCount + '\'' +
                ", tallStart='" + tallStart + '\'' +
                ", connString='" + connString + '\'' +
                '}';
    }
}
