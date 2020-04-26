package com.hari.mvvm.repositories;

import com.hari.mvvm.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class NicePlaceRepository {

    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    public static NicePlaceRepository getInstance() {
        if (instance == null) {
            return new NicePlaceRepository();
        }
        return instance;
    }

    //pretend to get data from a webservice or online source
    public MutableLiveData<List<NicePlace>> getNicePlaces() {
        setNicePlaces();

        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces() {
        for (int i = 0; i < 100; i++) {
            dataSet.add(new NicePlace("id " + i, "Place " + (i + 1), "https://homepages.cae.wisc.edu/~ece533/images/fruits.png"));
        }
    }
}
