package com.ef.ValueObject;

import java.time.LocalDateTime;

/**
 * Class of representation of 'Options'.
 *
 * @author Hugo Fernandes
 */
public class Options {

    private String acessLog;

    private LocalDateTime startDate;

    private String duration;

    private int threshold;

    /**
     *
     * @return current acessLog
     */
    public String getAcessLog() {
        return acessLog;
    }

    /**
     *
     * @param acessLog to set (in string)
     */
    public void setAcessLog(String acessLog) {
        this.acessLog = acessLog;
    }

    /**
     *
     * @return current startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate to set (in LocalDateTime)
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return current duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration to set (in string)
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     *
     * @return current threshold
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     *
     * @param threshold to set (in int)
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
