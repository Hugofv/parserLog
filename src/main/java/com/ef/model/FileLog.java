package com.ef.model;

/**
 * Class of representation of 'File Log'.
 *
 * @author Hugo Fernandes
 */
public class FileLog {

    private Integer id;

    private String name;

    private String accessFile;

    /**
     *
     * @return current id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id to set (in int)
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return current name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name to set (in string)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return current accessFile
     */
    public String getAccessFile() {
        return accessFile;
    }

    /**
     *
     * @param accessFile to set (in string)
     */
    public void setAccessFile(String accessFile) {
        this.accessFile = accessFile;
    }
}
