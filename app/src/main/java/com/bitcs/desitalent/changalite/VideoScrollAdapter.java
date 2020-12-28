package com.bitcs.desitalent.changalite;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bitcs.desitalent.changalite.models.Video;

import java.util.ArrayList;

public class VideoScrollAdapter extends FragmentStateAdapter {

    private ArrayList<Video> videoList;
    private Activity activity;

    public VideoScrollAdapter(Activity activity, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Video> videoList) {
        super(fragmentManager, lifecycle);
        this.videoList = videoList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return VideoFragment.newInstance(videoList.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (videoList == null)
            return 0;
        return videoList.size();
    }
}
