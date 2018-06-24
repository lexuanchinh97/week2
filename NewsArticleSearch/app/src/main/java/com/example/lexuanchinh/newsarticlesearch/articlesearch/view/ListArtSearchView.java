package com.example.lexuanchinh.newsarticlesearch.articlesearch.view;

import com.example.lexuanchinh.newsarticlesearch.model.Doc;

import java.util.List;

public interface ListArtSearchView {
    void showLoading();
    void showListArtSearch(List<Doc> docs);
    void hideLoading();
}
