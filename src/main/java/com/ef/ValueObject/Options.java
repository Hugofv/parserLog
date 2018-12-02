package com.ef.ValueObject;

import java.time.LocalDateTime;

public class Options {

    private String acessLog;

    private LocalDateTime startDate;

    private String duration;

    private int threshold;

    public String getAcessLog() {
        return acessLog;
    }

    public void setAcessLog(String acessLog) {
        this.acessLog = acessLog;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
