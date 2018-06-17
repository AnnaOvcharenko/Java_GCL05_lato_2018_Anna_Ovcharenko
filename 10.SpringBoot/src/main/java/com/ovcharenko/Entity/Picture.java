package com.ovcharenko.Entity;

public class Picture {
    private static String galleryPath;

    private int id;
    private String name;
    private int width;
    private int height;
    private long size;
    private long created;

    public Picture(int id, String name, int width, int height, long size, long created) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.size = size;
        this.created = created;
    }

    public Picture() {
    }



////////////////////////////
    public static String getGalleryPath() {
        return galleryPath;
    }

    public static void setGalleryPath(String galleryPath) {
        Picture.galleryPath = galleryPath;
    }

    ///////////////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
