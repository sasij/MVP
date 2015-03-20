package com.juanjo.mvp.presenters;

import com.juanjo.mvp.interfaces.IDetailActivityPresenter;
import com.juanjo.mvp.interfaces.IDetailView;
import com.juanjo.mvp.models.ImageDto;

/**
 * Created by juanjo on 14/2/15.
 */
public class DetailViewPresenter implements IDetailActivityPresenter {

	private IDetailView view;
	private ImageDto image;

	@Override
	public void onCreate(IDetailView detailView, ImageDto imageDto) {
		view = detailView;
		image = imageDto;

		view.setImage(image.getUrl());
		view.setTitle(image.getTitle());
	}

}
