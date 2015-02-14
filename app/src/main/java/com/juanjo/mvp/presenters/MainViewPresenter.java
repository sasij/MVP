package com.juanjo.mvp.presenters;

import com.google.inject.Inject;
import com.juanjo.mvp.helpers.ImageBuilder;
import com.juanjo.mvp.interfaces.IMainActivityPresenter;
import com.juanjo.mvp.interfaces.IMainView;
import com.juanjo.mvp.models.ImageDto;

import java.util.List;

/**
 * Created by juanjo on 14/2/15.
 */
public class MainViewPresenter implements IMainActivityPresenter {

    IMainView view;

    @Inject
    ImageBuilder imageBuilder;
    List<ImageDto> images;

    @Override
    public void onCreate(IMainView view) {
        this.view = view;

        view.showProgressBar();
        view.hideList();
        getImages();
        showList();
    }

    @Override
    public void getImages() {

        //Here we can doing the request to get the data
        //In the example, I have the data hardcoded in the code
        images = imageBuilder.getImages();

    }

    @Override
    public void showList() {
        view.hideProgressBar();
        view.createList(images);
        view.showList();
    }

    @Override
    public ImageDto getImage(int position) {
        return images.get(position);
    }

}
