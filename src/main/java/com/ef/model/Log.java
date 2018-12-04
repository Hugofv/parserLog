package com.ef.model;

import java.time.LocalDateTime;


public class Log {

    private int id;

    private int fileId;

    private LocalDateTime startDate;

    private String ipAddress;

    private String request;

    private int statusCode;

    private String userAgent;

    /**
     * the startDate
     *
     * @return
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
