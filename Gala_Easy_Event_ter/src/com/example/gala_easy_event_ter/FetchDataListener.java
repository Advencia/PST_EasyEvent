package com.example.gala_easy_event_ter;

import java.util.List;

public interface FetchDataListener {
    public void onFetchComplete(List<Etudiant> data);
    public void onFetchFailure(String msg);
}

