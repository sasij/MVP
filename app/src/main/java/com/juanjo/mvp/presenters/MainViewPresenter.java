package com.juanjo.mvp.presenters;

import com.google.inject.Inject;
import com.juanjo.mvp.helpers.ImageBuilder;
import com.juanjo.mvp.interfaces.IMainView;
import com.juanjo.mvp.interfaces.IMainViewPresenter;
import com.juanjo.mvp.models.ImageDto;

import java.util.List;

/**
 * Created by juanjo on 14/2/15.
 */
public class MainViewPresenter implements IMainViewPresenter {

	@Inject
	private ImageBuilder imageBuilder;

	private IMainView view;
	private List<ImageDto> images;

	@Override
	public void onCreate(IMainView view) {
		this.view = view;
	}

	@Override
	public void getImagesFromService() {
		view.showProgressBar();
		view.hideList();

		// Here we can doing the request to get the data
		// In the example, I have the data hardcoded in the code
		images = imageBuilder.getImages();

		showList();
	}

	@Override
	public void showList() {
		view.hideProgressBar();
		view.createList(view.createImageAdapter(images));// Inversion Dependency
		view.showList();
	}

	@Override
	public void onItemClicked(int position) {
		view.goToDetailActivity(images.get(position));
	}

}
