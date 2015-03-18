package com.juanjo.mvp;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import roboguice.RoboGuice;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.juanjo.mvp.helpers.ImageBuilder;
import com.juanjo.mvp.interfaces.IMainView;
import com.juanjo.mvp.models.ImageDto;
import com.juanjo.mvp.presenters.MainViewPresenter;
import com.juanjo.mvp.views.adapters.ImageListAdapter;

/**
 * Created by juanjo on 18/03/15.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityPresenterTest {

	@Inject
	MainViewPresenter presenter;

	@Mock
	IMainView viewMock;
	@Mock
	ImageBuilder imageBuilderMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		RoboGuice.overrideApplicationInjector(Robolectric.application,
				new MyTestModule());
		RoboGuice.getInjector(Robolectric.application).injectMembers(this);
	}

	@Test
	public void testGetImagesFromServer() {
		List<ImageDto> imagesList = images();

		when(imageBuilderMock.getImages()).thenReturn(imagesList);

		presenter.onCreate(viewMock);
		presenter.getImagesFromService();

		verify(viewMock, times(1)).showProgressBar();
		verify(viewMock, times(1)).hideList();

		verify(imageBuilderMock, times(1)).getImages();

		verify(viewMock, times(1)).hideProgressBar();
		verify(viewMock, times(1)).createImageAdapter(imagesList);
		verify(viewMock, times(1)).createList(any(ImageListAdapter.class));
		verify(viewMock, times(1)).showList();
	}

	@Test
	public void testOnItemClick() {
		List<ImageDto> imagesList = images();

		when(imageBuilderMock.getImages()).thenReturn(imagesList);

		presenter.onCreate(viewMock);
		presenter.getImagesFromService();
		presenter.onItemClicked(1);

		verify(viewMock).goToDetailActivity(any(ImageDto.class));
	}

	private List<ImageDto> images() {
		List<ImageDto> listImages = new ArrayList<ImageDto>();

		listImages.add(new ImageDto());
		listImages.add(new ImageDto());

		return listImages;
	}

	public class MyTestModule extends AbstractModule {
		@Override
		protected void configure() {
			bind(ImageBuilder.class).toInstance(imageBuilderMock);
		}
	}
}
