package com.juanjo.mvp.interfaces;

import com.juanjo.mvp.models.ImageDto;

/**
 * Created by juanjo on 14/2/15.
 */
public interface IMainActivityPresenter {

    public void onCreate(IMainView view);

    public void getImages();

    public void showList();

    public ImageDto getImage(int position);
}
