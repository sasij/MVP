package com.juanjo.mvp.helpers;

import com.google.inject.Singleton;
import com.juanjo.mvp.models.ImageDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanjo on 14/2/15.
 */
@Singleton
public class ImageBuilder {

    int count = 0;

    public ImageBuilder() {
    }

    public ImageDto getImage() {

        ImageDto image = new ImageDto();
        image.setTitle("Image" + System.currentTimeMillis());
        image.setUrl("http://lorempixel.com/300/200/sports/" + count);
        count++;
        return image;
    }

    public List<ImageDto> getImages() {

        List<ImageDto> images = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            images.add(getImage());
        }
        return images;
    }


}
