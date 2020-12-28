package com.bitcs.desitalent.changalite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bitcs.desitalent.changalite.models.IChangaApiResponse;
import com.bitcs.desitalent.changalite.models.Video;
import com.bitcs.desitalent.changalite.models.VideoResponse;
import com.bitcs.desitalent.changalite.utils.ApiHelper;

import java.util.ArrayList;

public class VideoScrollManagerFragment extends Fragment {

    private ArrayList<Video> videoList = new ArrayList<>();
    private View view;
    private FragmentActivity activity;
    public ViewPager2 viewPager2;
    private String TAG = VideoScrollManagerFragment.class.getSimpleName();
    public VideoScrollAdapter videoScrollAdapter;
    private int position;
    private String videoId;
    int page = 1;
    private FrameLayout commentHolder;

    public VideoScrollManagerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();

        Bundle args = getArguments();
        if (args != null) {
            videoId = args.getString("videoId");
            position = args.getInt("position");
            videoList = args.getParcelableArrayList("userVideos");
            videoList = videoList == null ? new ArrayList<>() : videoList;
        }
        // this will be called when there is no video.
        if (videoList.size() == 0) {
            makeServerCall();
        }

    }

    VideoScrollManagerFragment mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_scroll, container, false);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // do nothing on back pressed
            }
        };

        viewPager2 = view.findViewById(R.id.video_viewpager);

        videoScrollAdapter = new VideoScrollAdapter(activity, getChildFragmentManager(), getLifecycle(), videoList);

        mContext = this;

        commentHolder = view.findViewById(R.id.comment_holder);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.setAdapter(videoScrollAdapter);
        viewPager2.setCurrentItem(position, false);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == videoList.size() - 5) {
                    loadMore();
                }
            }
        });

        return view;
    }

    public void makeServerCall() {
        page = 1;
        videoList.clear();
        loadMore();
    }

    public void loadMore() {
        Log.i("loading more: page", "" + page);
        ApiHelper.makeGetRequest(ApiHelper.getNextPopularVideoUrl(page), VideoResponse.class, new IChangaApiResponse() {
            @Override
            public <T> void onResponse(String error, T classObject) {
                if (error == null) {
                    page++;
                    VideoResponse popularVideos = (VideoResponse) classObject;
                    if (popularVideos.data != null && popularVideos.data.size() > 0) {
                        videoList.addAll(popularVideos.data);
                        videoScrollAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}

