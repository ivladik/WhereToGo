package com.so_so_stuff.avoidpeople.wheretogo.screens.places.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.so_so_stuff.avoidpeople.wheretogo.R;
import com.so_so_stuff.avoidpeople.wheretogo.models.Place;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by ivladik on 30.10.2017.
 */

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.ViewHolder> {
    private final PublishSubject<Integer> clickedItemIndex = PublishSubject.create();
    private ArrayList<Place> placesList = new ArrayList<>();

    public void swapAdapter(ArrayList<Place> newList) {
        placesList.clear();
        placesList.addAll(newList);
        notifyDataSetChanged();
    }

    public Observable<Integer> observeSubject() {
        return clickedItemIndex;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view, clickedItemIndex);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = placesList.get(position);
        holder.bind(place);
    }

    @Override
    public int getItemCount() {
        return placesList != null && placesList.size() > 0 ? placesList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.places_list_image)
        ImageView imageView;
        @BindView(R.id.places_list_title)
        TextView titleTextView;
        @BindView(R.id.places_list_age_restriction)
        TextView ageRestrictionTextView;
        @BindView(R.id.places_list_item_url)
        TextView itemUrlTextView;
//        @BindView(R.id.places_list_description)
//        TextView descriptionTextView;

        public ViewHolder(View itemView, PublishSubject<Integer> clickedItemIndex) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
            view.setOnClickListener(view -> clickedItemIndex.onNext(getAdapterPosition()));
        }

        public void bind(Place place) {
            Glide.with(view.getContext()).load(place.getFirstImage().getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);

            titleTextView.setText(TextUtils.isEmpty(place.getTitle()) ? "no title" : place.getTitle());
            String ageRestriction = String.valueOf(place.getAgeRestriction());
            ageRestrictionTextView.setText(TextUtils.isEmpty(ageRestriction) ? "no age restriction" : ageRestriction + "+");
            itemUrlTextView.setText(TextUtils.isEmpty(place.getItemUrl()) ? "no item url" : place.getItemUrl());
//            descriptionTextView.setText(TextUtils.isEmpty(place.getDescription()) ? "no description" : place.getDescription());
        }
    }
}
