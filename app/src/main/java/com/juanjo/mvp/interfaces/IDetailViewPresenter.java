package com.juanjo.mvp.interfaces;

import com.juanjo.mvp.models.ImageDto;

/**
 * Created by juanjo on 14/2/15.
 */
public interface IDetailViewPresenter {

	public void onCreate(IDetailView view, ImageDto image);

}
