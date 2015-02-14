package com.juanjo.mvp.views.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.juanjo.mvp.R;
import com.juanjo.mvp.interfaces.IDetailView;
import com.juanjo.mvp.presenters.DetailViewPresenter;
import com.squareup.picasso.Picasso;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created by juanjo on 14/2/15.
 */
public class DetailActivity extends RoboActivity implements IDetailView {

    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.title)
    TextView title;

    @Inject
    DetailViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter.onCreate(this, getIntent());
    }

    @Override
    public void setImage(String url) {
        Picasso.with(this).load(url).fit().into(image);
    }

    @Override
    public void setTitle(String text) {
        title.setText(text);
    }

}
