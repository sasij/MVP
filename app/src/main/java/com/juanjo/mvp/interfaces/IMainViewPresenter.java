package com.juanjo.mvp.interfaces;

/**
 * Created by juanjo on 14/2/15.
 */
public interface IMainViewPresenter {

    public void onCreate(IMainView view);

    public void getImagesFromService();

    public void showList();

    public void onItemClicked(int position);

}
