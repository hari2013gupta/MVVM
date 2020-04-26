package com.hari.mvvm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hari.mvvm.adapters.PlaceAdapter;
import com.hari.mvvm.common.MyDividerItemDecoration;
import com.hari.mvvm.models.NicePlace;
import com.hari.mvvm.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private Activity activity;
    private List<NicePlace> placeList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PlaceAdapter placeAdapter;
    private ProgressBar mProgressBar;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();

        mainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                Log.d(TAG, "=========OBSERVE CHANGES==========");
                placeAdapter.notifyDataSetChanged();
            }
        });
        mainActivityViewModel.getIsUpdating().observe(this, aBoolean -> {
            showHideProgressBar(aBoolean);
            if(!aBoolean){
                mRecyclerView.smoothScrollToPosition(mainActivityViewModel.getNicePlaces().getValue().size()-1);
            }
        });

        initLayout();

        addItemInList();
    }

    private void initLayout() {
        mProgressBar = findViewById(R.id.progress_bar);

        mRecyclerView = findViewById(R.id.placeRV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(activity, DividerItemDecoration.VERTICAL, 36));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        placeAdapter = new PlaceAdapter(activity, mainActivityViewModel.getNicePlaces().getValue());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(placeAdapter);

        placeAdapter.notifyDataSetChanged();
        placeAdapter.setOnItemClickListener((id, v) -> {
            Log.v(TAG, "ID=" + id);
        });
        FloatingActionButton mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(v -> {

            NicePlace aPlace = new NicePlace("id x", "Place x", "https://homepages.cae.wisc.edu/~ece533/images/fruits.png");
            mainActivityViewModel.addNewValue(aPlace);
        });
    }

    private void showHideProgressBar(boolean showProgress) {
        if (showProgress) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void addItemInList() {
        for (int i = 0; i < 100; i++) {
            placeList.add(new NicePlace("id " + i, "Place " + i, "https://homepages.cae.wisc.edu/~ece533/images/fruits.png"));
        }
    }
}
