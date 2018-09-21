package com.example.emlar.maver.Models;

public class Thumbnails {
    private String path;
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtention() {
        return extension;
    }

    public void setExtention(String extention) {
        this.extension = extention;
    }

    public String getFullPath(){
        return path + "." + extension;
    }


}
