package com.juanjo.mvp.interfaces;

import com.juanjo.mvp.models.ImageDto;
import com.juanjo.mvp.views.adapters.ImageListAdapter;

import java.util.List;

/**
 * Created by juanjo on 14/2/15.
 */
public interface IMainView {

    public void createList(ImageListAdapter adapter);

    public ImageListAdapter createImageAdapter(List<ImageDto> images);

    public void goToDetailActivity(ImageDto image);

    public void showProgressBar();

    public void hideProgressBar();

    public void showRetryButton();

    public void hideRetryButton();

    public void showList();

    public void hideList();

    public void showMessage(String message);

}
