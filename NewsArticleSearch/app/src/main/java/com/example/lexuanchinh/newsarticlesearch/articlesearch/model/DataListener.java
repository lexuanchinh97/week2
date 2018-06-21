package com.example.lexuanchinh.newsarticlesearch.articlesearch.model;

import com.example.lexuanchinh.newsarticlesearch.model.Doc;

import java.util.List;

public interface DataListener {
    void onResponse(List<Doc> docs);

}
