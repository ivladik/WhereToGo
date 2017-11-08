package com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp;

import com.so_so_stuff.avoidpeople.wheretogo.models.Place;
import com.so_so_stuff.avoidpeople.wheretogo.models.Places;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ivladik on 02.11.2017.
 */
public class PlacesListPresenterTest {
    private PlacesListPresenter presenter;
    @Mock
    PlacesListModel mockModel;
    @Mock
    PlacesListView mockView;
    @Mock
    Places mockPlaces;
    @Mock
    Place mockPlace;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        RxJavaHooks.setOnIOScheduler(scheduler -> Schedulers.immediate());
        RxJavaHooks.setOnComputationScheduler(scheduler -> Schedulers.immediate());
        RxJavaHooks.setOnNewThreadScheduler(scheduler -> Schedulers.immediate());

        final RxAndroidPlugins rxAndroidPlugins = RxAndroidPlugins.getInstance();
        rxAndroidPlugins.registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        presenter = new PlacesListPresenter(mockModel, mockView, new CompositeSubscription());
    }

    @Test
    public void getDataFromNetwork() {
        when(mockModel.isNetworkAvailable()).thenReturn(true);
        when(mockModel.providePlacesList()).thenReturn(Observable.just(mockPlaces));
        when(mockPlaces.getResults()).thenReturn(new ArrayList<>(Collections.singletonList(mockPlace)));

        when(mockView.observeClickedItem()).thenReturn(Observable.just(0));

        presenter.onCreate();

        verify(mockModel).isNetworkAvailable();
        verify(mockModel, atLeastOnce()).providePlacesList();
        verify(mockModel, never()).getPlacesListFromDb();
        verify(mockModel).addPlacesToDb(mockPlaces.getResults());
        verify(mockView).swapAdapter(mockPlaces.getResults());

        verify(mockView).observeClickedItem();
        verify(mockModel).goToPlaceDetailsActivity(any(Place.class));
    }

    @Test
    public void getDataFromDatabase() {
        when(mockModel.isNetworkAvailable()).thenReturn(false);
        when(mockModel.getPlacesListFromDb()).thenReturn(Observable.just(mockPlaces));
        when(mockPlaces.getResults()).thenReturn(new ArrayList<>(Collections.singletonList(mockPlace)));

        when(mockView.observeClickedItem()).thenReturn(Observable.just(0));

        presenter.onCreate();

        verify(mockModel).isNetworkAvailable();
        verify(mockModel, atLeastOnce()).getPlacesListFromDb();
        verify(mockModel, never()).providePlacesList();
        verify(mockView).swapAdapter(mockPlaces.getResults());

        verify(mockView).observeClickedItem();
        verify(mockModel).goToPlaceDetailsActivity(any(Place.class));
    }

    @After
    public void tearDown() throws Exception {
        RxJavaHooks.reset();
        RxAndroidPlugins.getInstance().reset();
    }
}