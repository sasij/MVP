package com.juanjo.mvp;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.shadowOf;

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
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

import roboguice.RoboGuice;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.juanjo.mvp.interfaces.IMainActivityPresenter;
import com.juanjo.mvp.models.ImageDto;
import com.juanjo.mvp.presenters.MainViewPresenter;
import com.juanjo.mvp.views.activities.DetailActivity;
import com.juanjo.mvp.views.activities.MainActivity;
import com.juanjo.mvp.views.adapters.ImageListAdapter;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

	@Inject
	Context context;
	@Mock
	MainViewPresenter presenterMock;

	private MainActivity activity;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		RoboGuice.overrideApplicationInjector(Robolectric.application,
				new MyTestModule());
		RoboGuice.getInjector(Robolectric.application).injectMembers(this);

		activity = Robolectric.buildActivity(MainActivity.class).create().get();
	}

	@Test
	public void testOnCreate() {
		assertNotNull("activity is null", activity);
		verify(presenterMock, times(1)).onCreate(activity);
	}

	@Test
	public void testViewsShouldNotBeNull() {
		ListView list = (ListView) activity.findViewById(R.id.list);
		ProgressBar progressBar = (ProgressBar) activity
				.findViewById(R.id.progress);
		Button button = (Button) activity.findViewById(R.id.retry);

		assertNotNull("list is null", list);
		assertNotNull("progressBar is null", progressBar);
		assertNotNull("button is null", button);
	}

	@Test
	public void testCreateList() {
		ImageListAdapter adapter = createAdapter();

		ListView list = (ListView) activity.findViewById(R.id.list);
		activity.createList(adapter);

		assertEquals(list.getAdapter(), adapter);
		assertEquals(list.getCount(), adapter.getCount());
		assertNotNull(list.getOnItemClickListener());
	}

	@Test
	public void testShowMessage() {
		activity.showMessage("Error");
		assertEquals("Error", ShadowToast.getTextOfLatestToast());
	}

	@Test
	public void testClickOnItemList() {
		ListView list = (ListView) activity.findViewById(R.id.list);
		activity.createList(createAdapter());

		shadowOf(list).performItemClick(0);

		verify(presenterMock, times(1)).onItemClicked(0);
	}

	@Test
	public void testGoToDetailActivity() {
		activity.goToDetailActivity(new ImageDto());

		ShadowActivity shadowActivity = shadowOf(activity);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

		assertEquals(DetailActivity.class.getName(), shadowIntent
				.getComponent().getClassName());
		assertNotNull(shadowIntent.getParcelableExtra("IMAGE"));
	}

	@Test
	public void testShowProgressBar() {
		ProgressBar progressBar = (ProgressBar) activity
				.findViewById((R.id.progress));
		activity.showProgressBar();

		assertThat(progressBar.getVisibility(), equalTo(View.VISIBLE));
	}

	@Test
	public void testHideProgressBar() {
		ProgressBar progressBar = (ProgressBar) activity
				.findViewById((R.id.progress));
		activity.hideProgressBar();

		assertThat(progressBar.getVisibility(), equalTo(View.GONE));
	}

	private ImageListAdapter createAdapter() {
		// TODO Create builder pattern
		ImageDto image1 = new ImageDto();
		ImageDto image2 = new ImageDto();

		List<ImageDto> images = new ArrayList<>();
		images.add(image1);
		images.add(image2);

		return new ImageListAdapter(context, images);
	}

	public class MyTestModule extends AbstractModule {
		@Override
		protected void configure() {
			bind(IMainActivityPresenter.class).toInstance(presenterMock);
		}
	}
}