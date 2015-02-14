package com.juanjo.mvp.models;

import org.parceler.Parcel;

/**
 * Created by juanjo on 14/2/15.
 */
@Parcel
public class ImageDto {

    String url;
    String title;

    public ImageDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
