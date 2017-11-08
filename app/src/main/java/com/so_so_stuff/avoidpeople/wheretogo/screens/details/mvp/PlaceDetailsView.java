package com.so_so_stuff.avoidpeople.wheretogo.screens.details.mvp;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.so_so_stuff.avoidpeople.wheretogo.R;
import com.so_so_stuff.avoidpeople.wheretogo.models.Place;
import com.so_so_stuff.avoidpeople.wheretogo.screens.details.DetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivladik on 30.10.2017.
 */

public class PlaceDetailsView {
    View view;
    @BindView(R.id.place_details_image_view)
    ImageView imageView;
    @BindView(R.id.place_details_title)
    TextView tvTitle;
    @BindView(R.id.place_details_description)
    TextView tvDescription;
    @BindView(R.id.place_details_age_restriction)
    TextView tvAgeRestriction;
    @BindView(R.id.place_details_item_utl)
    TextView tvItemUrl;

    public PlaceDetailsView(DetailsActivity context, Place place) {
        FrameLayout parent = new FrameLayout(context); // change layout
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_details, parent, false);
        // если айтэм списка или фрагмент (они ответственны за добавление элемента), а также если элемент из xml-файла уже прикреплен к разметке в этом же файле (!!!) - ставить фолс
        // если родителя нет - ставить true

        ButterKnife.bind(this, view);

        Glide.with(context).load(place.getFirstImage().getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        tvTitle.setText(TextUtils.isEmpty(place.getTitle()) ? "no title" : place.getTitle());
        String ageRestriction = String.valueOf(place.getAgeRestriction());
        tvAgeRestriction.setText(TextUtils.isEmpty(ageRestriction) ? "no age restriction" : ageRestriction + "+");
        tvItemUrl.setText(TextUtils.isEmpty(place.getItemUrl()) ? "no item url" : place.getItemUrl());
        tvDescription.setText(TextUtils.isEmpty(place.getDescription()) ? "no description" : place.getDescription());
    }

    public View getView() {
        return view;
    }
}
