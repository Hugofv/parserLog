package com.ef.model;

/**
 * Class of representation of 'Ip blocked'.
 *
 * @author Hugo Fernandes
 */
public class IpBlocked {

    private String ipAddress;

    private String reason;

    private long quantity;

    private int fileId;

    /**
     *
     * @return current ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     *
     * @param ipAddress to set (in string)
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     *
     * @return current reason
     */
    public String getReason() {
        return reason;
    }

    /**
     *
     * @param reason to set (in string)
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     *
     * @return current quantity
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity to set (in long)
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
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
