package com.juanjo.mvp;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.juanjo.mvp.interfaces.IDetailView;
import com.juanjo.mvp.models.ImageDto;
import com.juanjo.mvp.presenters.DetailViewPresenter;

/**
 * Created by juanjo on 20/03/15.
 */
public class UnitTest extends TestCase {

	DetailViewPresenter presenter;

	@Mock
	IDetailView mockView;

	@Mock
	ImageDto imageMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		presenter = new DetailViewPresenter();
	}

	public void testPresenteShouldNotBeNull() {

		Assert.assertNotNull(presenter);
	}

	public void testOnCreateDetailPresenter() {

		when(imageMock.getTitle()).thenReturn("Title test");
		when(imageMock.getUrl()).thenReturn("http://test");

		presenter.onCreate(mockView, imageMock);

		verify(mockView, times(1)).setTitle(anyString());
		verify(mockView, times(1)).setTitle(anyString());

	}
}