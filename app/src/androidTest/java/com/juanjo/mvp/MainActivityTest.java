package com.juanjo.mvp;

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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.shadowOf;


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

        activity = Robolectric.buildActivity(MainActivity.class).create()
                .get();
    }

    @Test
    public void testOnCreate() {
        assertNotNull("activity is null", activity);
        verify(presenterMock, times(1)).onCreate(activity);
    }

    @Test
    public void testViewsShouldNotBeNull() {
        ListView list = (ListView) activity.findViewById(R.id.list);
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progress);
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
        ShadowActivity shadowActivity = shadowOf(activity);

        ListView list = (ListView) activity.findViewById(R.id.list);
        activity.createList(createAdapter());

        Robolectric.shadowOf(list).performItemClick(0);

        Intent startedIntent = shadowActivity.getNextStartedActivity();

        assertEquals(startedIntent.getComponent().getClassName(),
                DetailActivity.class.getName());

        assertNotNull(startedIntent.getParcelableExtra("IMAGE"));
    }

    @Test
    public void testShowProgressBar() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById((R.id.progress));
        activity.showProgressBar();

        assertThat(progressBar.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void testHideProgressBar() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById((R.id.progress));
        activity.hideProgressBar();

        assertThat(progressBar.getVisibility(), equalTo(View.GONE));
    }

    private ImageListAdapter createAdapter() {
        //TODO Create builder pattern
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

//    @Test
//    public void testOnResume() {
//        spyPresenter = spy(presenter);
//
//        spyPresenter.onCreate(viewMock);
//        spyPresenter.onResume();
//
//        verify(databaseHelperMock, times(1)).open();
//        verify(viewMock, times(1)).loadMap();
//        verify(spyPresenter, times(1)).initProcess();
//    }

//    @Test
//    public void testInitWithConnectionEnabled() {
//
//        spyPresenter = spy(presenter);
//
//        doReturn(true).when(spyPresenter).canInitTheTask();
//
//        spyPresenter.onCreate(viewMock);
//        spyPresenter.initProcess();
//
//        verify(viewMock, times(1)).showMessage(anyString());
//        verify(viewMock, times(1)).cleanMap();
//        verify(databaseHelperMock, times(1)).removeAllTweetsFromDatabase();
//        verify(spyPresenter, times(1)).startStreamTask(any(StreamTweetTask.class));
//
//    }
//
//
//    @Test
//    public void testInitWithConnectionDisabled() {
//        List<Tweet> tweets = new ArrayList<Tweet>();
//        Tweet tweet1 = new Tweet();
//        Tweet tweet2 = new Tweet();
//        tweets.add(tweet1);
//        tweets.add(tweet2);
//
//        spyPresenter = spy(presenter);
//
//        doReturn(false).when(spyPresenter).canInitTheTask();
//        when(databaseHelperMock.getAllTweetsFromDatabase()).thenReturn(tweets);
//
//        spyPresenter.onCreate(viewMock);
//        spyPresenter.initProcess();
//
//        verify(viewMock, times(1)).showMessage("Without connection");
//        verify(viewMock, times(2)).addPinToMap(any(Tweet.class));
//    }
//
//    @Test
//    public void testStartStreamTask() {
//        presenter.startStreamTask(streamTweetTaskMock);
//
//        verify(streamTweetTaskMock, times(1)).setListener(presenter);
//        verify(streamTweetTaskMock, times(1)).execute();
//    }
//
//    @Test
//    public void testShowPinOnMap() {
//        presenter.onCreate(viewMock);
//        presenter.showPinOnMap(new Tweet());
//
//        verify(viewMock, times(1)).addPinToMap(any(Tweet.class));
//    }
//
//    @Test
//    public void testOnPause() {
//        spyPresenter = spy(presenter);
//        spyPresenter.onPause();
//        verify(spyPresenter, times(1)).stopStreamTask(any(StreamTweetTask.class));
//    }
//
//    @Test
//    public void testStopStreamTask() {
//        presenter.stopStreamTask(streamTweetTaskMock);
//        verify(streamTweetTaskMock, times(1)).stopStream();
//        verify(streamTweetTaskMock, times(1)).cancel(true);
//    }