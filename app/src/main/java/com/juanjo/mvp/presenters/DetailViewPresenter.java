package com.juanjo.mvp.presenters;

import android.content.Intent;

import com.juanjo.mvp.interfaces.IDetailActivityPresenter;
import com.juanjo.mvp.interfaces.IDetailView;
import com.juanjo.mvp.models.ImageDto;

import org.parceler.Parcels;

/**
 * Created by juanjo on 14/2/15.
 */
public class DetailViewPresenter implements IDetailActivityPresenter {

    IDetailView view;
    ImageDto image;

    @Override
    public void onCreate(IDetailView view, Intent intent) {
        this.view = view;
        getImageFromIntent(intent);

        view.setImage(image.getUrl());
        view.setTitle(image.getTitle());
    }

    @Override
    public void getImageFromIntent(Intent intent) {
        image = Parcels.unwrap(intent.getParcelableExtra("IMAGE"));
    }

}
