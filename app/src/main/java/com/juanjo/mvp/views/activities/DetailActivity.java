package com.juanjo.mvp.views.activities;

import org.parceler.Parcels;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.juanjo.mvp.R;
import com.juanjo.mvp.interfaces.IDetailActivityPresenter;
import com.juanjo.mvp.interfaces.IDetailView;
import com.juanjo.mvp.models.ImageDto;
import com.squareup.picasso.Picasso;

/**
 * Created by juanjo on 14/2/15.
 */
public class DetailActivity extends RoboActivity implements IDetailView {

	@InjectView(R.id.image)
	ImageView image;
	@InjectView(R.id.title)
	TextView title;

	@Inject
	IDetailActivityPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		presenter.onCreate(this, getImageFromIntent(getIntent()));
	}

	@Override
	public void setImage(String url) {
		Picasso.with(this).load(url).fit().into(image);
	}

	@Override
	public void setTitle(String text) {
		title.setText(text);
	}

	private ImageDto getImageFromIntent(Intent intent) {
		return (ImageDto) Parcels.unwrap(intent.getParcelableExtra("IMAGE"));
	}

}
