package com.example.gala_easy_event;

import java.util.List;

public interface FetchDataListener {
    public void onFetchComplete(List<Etudiant> data);
    public void onFetchFailure(String msg);
}

