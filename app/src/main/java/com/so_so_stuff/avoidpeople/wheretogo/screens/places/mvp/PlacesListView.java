package com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.so_so_stuff.avoidpeople.wheretogo.R;
import com.so_so_stuff.avoidpeople.wheretogo.models.Place;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.PlacesListActivity;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.adapter.PlacesListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by admin on 30.10.2017.
 */

public class PlacesListView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    View view;
    PlacesListAdapter adapter;

    public PlacesListView(PlacesListActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_places_list, parent, true);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new PlacesListAdapter();
        recyclerView.setAdapter(adapter);
    }

    Observable<Integer> observeClickedItem() {
        return adapter.observeSubject();
    }

    public View getView() {
        return view;
    }

    public void swapAdapter(ArrayList<Place> newList) {
        adapter.swapAdapter(newList);
    }
}
