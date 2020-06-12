package com.uni.julio.supertv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.tabs.TabLayout;
import com.uni.julio.supertv.helper.TVRecyclerView;
import com.uni.julio.supertv.model.Movie;
import com.uni.julio.supertv.viewmodel.EpisodeDetailsViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityMultiSeasonDetailBinding extends ViewDataBinding {
  @NonNull
  public final TextView actors;

  @NonNull
  public final TextView actorsDetail;

  @NonNull
  public final TextView description;

  @NonNull
  public final TextView descriptionDetail;

  @NonNull
  public final TabLayout detailTab;

  @NonNull
  public final TextView director;

  @NonNull
  public final TextView directorDetail;

  @NonNull
  public final TextView dislike;

  @NonNull
  public final ImageView fondoUrl;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final TextView like;

  @NonNull
  public final LinearLayout play;

  @NonNull
  public final RatingBar ratingBar;

  @NonNull
  public final TVRecyclerView recyclerView;

  @NonNull
  public final ScrollView scrollview;

  @NonNull
  public final TextView textView5;

  @Bindable
  protected EpisodeDetailsViewModel mMovieDetailsVM;

  @Bindable
  protected Movie mMovieDetailItem;

  protected ActivityMultiSeasonDetailBinding(Object _bindingComponent, View _root,
      int _localFieldCount, TextView actors, TextView actorsDetail, TextView description,
      TextView descriptionDetail, TabLayout detailTab, TextView director, TextView directorDetail,
      TextView dislike, ImageView fondoUrl, ImageView imageView7, ImageView imageView8,
      TextView like, LinearLayout play, RatingBar ratingBar, TVRecyclerView recyclerView,
      ScrollView scrollview, TextView textView5) {
    super(_bindingComponent, _root, _localFieldCount);
    this.actors = actors;
    this.actorsDetail = actorsDetail;
    this.description = description;
    this.descriptionDetail = descriptionDetail;
    this.detailTab = detailTab;
    this.director = director;
    this.directorDetail = directorDetail;
    this.dislike = dislike;
    this.fondoUrl = fondoUrl;
    this.imageView7 = imageView7;
    this.imageView8 = imageView8;
    this.like = like;
    this.play = play;
    this.ratingBar = ratingBar;
    this.recyclerView = recyclerView;
    this.scrollview = scrollview;
    this.textView5 = textView5;
  }

  public abstract void setMovieDetailsVM(@Nullable EpisodeDetailsViewModel movieDetailsVM);

  @Nullable
  public EpisodeDetailsViewModel getMovieDetailsVM() {
    return mMovieDetailsVM;
  }

  public abstract void setMovieDetailItem(@Nullable Movie movieDetailItem);

  @Nullable
  public Movie getMovieDetailItem() {
    return mMovieDetailItem;
  }

  @NonNull
  public static ActivityMultiSeasonDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_multi_season_detail, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMultiSeasonDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityMultiSeasonDetailBinding>inflateInternal(inflater, com.uni.julio.supertv.R.layout.activity_multi_season_detail, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMultiSeasonDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_multi_season_detail, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMultiSeasonDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityMultiSeasonDetailBinding>inflateInternal(inflater, com.uni.julio.supertv.R.layout.activity_multi_season_detail, null, false, component);
  }

  public static ActivityMultiSeasonDetailBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityMultiSeasonDetailBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityMultiSeasonDetailBinding)bind(component, view, com.uni.julio.supertv.R.layout.activity_multi_season_detail);
  }
}
