package com.ef.model;

public class IpBlock {

    private String IpAddress;

    private String Reason;

    private long Quantity;

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public long getQuantity() {
        return Quantity;
    }

    public void setQuantity(long quantity) {
        Quantity = quantity;
    }
}
