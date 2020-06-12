package com.uni.julio.supertv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.uni.julio.supertv.adapter.LivetvAdapterNew;
import com.uni.julio.supertv.model.LiveProgram;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class LivetvnewListBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout channelTitle;

  @NonNull
  public final TextView channelTitleText;

  @NonNull
  public final LinearLayout flMainLayout;

  @NonNull
  public final LinearLayout nextProgram;

  @NonNull
  public final TextView nextProgramText;

  @NonNull
  public final LinearLayout nowPlaying;

  @NonNull
  public final TextView nowPlayingText;

  @Bindable
  protected LiveProgram mLiveProgramItem;

  @Bindable
  protected LivetvAdapterNew mLivetvAdapter;

  protected LivetvnewListBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout channelTitle, TextView channelTitleText, LinearLayout flMainLayout,
      LinearLayout nextProgram, TextView nextProgramText, LinearLayout nowPlaying,
      TextView nowPlayingText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.channelTitle = channelTitle;
    this.channelTitleText = channelTitleText;
    this.flMainLayout = flMainLayout;
    this.nextProgram = nextProgram;
    this.nextProgramText = nextProgramText;
    this.nowPlaying = nowPlaying;
    this.nowPlayingText = nowPlayingText;
  }

  public abstract void setLiveProgramItem(@Nullable LiveProgram liveProgramItem);

  @Nullable
  public LiveProgram getLiveProgramItem() {
    return mLiveProgramItem;
  }

  public abstract void setLivetvAdapter(@Nullable LivetvAdapterNew livetvAdapter);

  @Nullable
  public LivetvAdapterNew getLivetvAdapter() {
    return mLivetvAdapter;
  }

  @NonNull
  public static LivetvnewListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.livetvnew_list, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static LivetvnewListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<LivetvnewListBinding>inflateInternal(inflater, com.uni.julio.supertv.R.layout.livetvnew_list, root, attachToRoot, component);
  }

  @NonNull
  public static LivetvnewListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.livetvnew_list, null, false, component)
   */
  @NonNull
  @Deprecated
  public static LivetvnewListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<LivetvnewListBinding>inflateInternal(inflater, com.uni.julio.supertv.R.layout.livetvnew_list, null, false, component);
  }

  public static LivetvnewListBinding bind(@NonNull View view) {
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
  public static LivetvnewListBinding bind(@NonNull View view, @Nullable Object component) {
    return (LivetvnewListBinding)bind(component, view, com.uni.julio.supertv.R.layout.livetvnew_list);
  }
}
