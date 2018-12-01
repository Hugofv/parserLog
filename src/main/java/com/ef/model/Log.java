package com.ef.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LogRegistry")
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @Column(name = "IpAddress")
    private String ipAddress;

    @Column(name = "Request")
    private String request;

    @Column(name = "StatusCode")
    private int statusCode;

    @Column(name = "UserAgent")
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

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
