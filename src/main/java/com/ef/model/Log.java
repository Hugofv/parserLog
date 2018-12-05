package com.ef.model;

import java.time.LocalDateTime;

/**
 * Class of representation of 'Log'.
 *
 * @author Hugo Fernandes
 */
public class Log {

    private int id;

    private int fileId;

    private LocalDateTime startDate;

    private String ipAddress;

    private String request;

    private int statusCode;

    private String userAgent;

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
     * @return current ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     *
     * @param ipAddress to set (in String)
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     *
     * @return current request
     */
    public String getRequest() {
        return request;
    }

    /**
     *
     * @param request to set (in string)
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     *
     * @return current statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode to set (in int)
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     *
     * @return current userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     *
     * @param userAgent to set (in string)
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     *
     * @return current fileId
     */
    public int getFileId() {
        return fileId;
    }

    /**
     *
     * @param fileId to set (in int)
     */
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
