package com.uni.julio.supertv.viewmodel;

import com.uni.julio.supertv.databinding.ActivityMultiSeasonDetailBinding;
import com.uni.julio.supertv.model.Movie;
import com.uni.julio.supertv.model.Serie;

public interface EpisodeDetailsViewModelContract {
    interface View extends Lifecycle.View {

        void onPlaySelected(Movie movie, int fromStart);
        void finishActivity();
        void showMovieDetails(Serie movie ,int maincategory, int moviecategory);

    }

    //this will have methods that the activity/fragment will call from the ViewModel
    interface ViewModel extends Lifecycle.ViewModel {
        void showMovieDetails(Serie movie, ActivityMultiSeasonDetailBinding movieDetailsBinding, int mainCategoryId, int movieCategoryId);
    }
}