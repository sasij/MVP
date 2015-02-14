package com.juanjo.mvp.interfaces;

import android.content.Intent;

/**
 * Created by juanjo on 14/2/15.
 */
public interface IDetailActivityPresenter {

    public void onCreate(IDetailView view, Intent intent);

    public void getImageFromIntent(Intent intent);

}
