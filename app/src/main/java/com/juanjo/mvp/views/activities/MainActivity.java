package com.juanjo.mvp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.inject.Inject;
import com.juanjo.mvp.R;
import com.juanjo.mvp.interfaces.IMainView;
import com.juanjo.mvp.interfaces.IMainViewPresenter;
import com.juanjo.mvp.models.ImageDto;
import com.juanjo.mvp.views.adapters.ImageListAdapter;

import org.parceler.Parcels;

import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;


public class MainActivity extends RoboActionBarActivity implements IMainView, AdapterView.OnItemClickListener {


    @InjectView(R.id.list)
    ListView list;
    @InjectView(R.id.progress)
    ProgressBar progressBar;
    @InjectView(R.id.retry)
    Button retryButton;

    @Inject
    IMainViewPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.onCreate(this);
        presenter.getImagesFromService();
    }

    @Override
    public ImageListAdapter createImageAdapter(List<ImageDto> images) {
        return new ImageListAdapter(this, images);
    }

    @Override
    public void createList(ImageListAdapter adapter) {
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetryButton() {
        retryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetryButton() {
        retryButton.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        list.setVisibility(View.GONE);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }

    @Override
    public void goToDetailActivity(ImageDto image) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("IMAGE", Parcels.wrap(image));
        startActivity(intent);
    }
}
