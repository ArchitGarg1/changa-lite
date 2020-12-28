package com.bitcs.desitalent.changalite;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bitcs.desitalent.changalite.models.Video;
import com.bitcs.desitalent.changalite.utils.PlayerUtil;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import static com.google.android.exoplayer2.Player.STATE_READY;

public class VideoFragment extends Fragment {

    public SimpleExoPlayer player;
    private PlayerView playerView;
    private View view;
    private Activity activity;
    private Video video;
    private int index;

    private ImageView thumbnailView;

    public static VideoFragment newInstance(Video video, Integer index) {
        VideoFragment instance = new VideoFragment();

        Bundle args = new Bundle();
        args.putParcelable("video", video);
        args.putInt("index", index);
        instance.setArguments(args);

        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();

        player.setPlayWhenReady(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.video_fragment, container, false);
        this.activity = getActivity();

        Bundle args = getArguments();
        if (args != null) {
            video = args.getParcelable("video");
            index = args.getInt("index");
        }

        playerView = view.findViewById(R.id.exo_player_view);
        thumbnailView = view.findViewById(R.id.exo_player_thumbnail);

        player = PlayerUtil.createPlayer(video.videoUrl, activity);
        playerView.setPlayer(player);

        player.addListener(new Player.DefaultEventListener() {

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                super.onPlayerStateChanged(playWhenReady, playbackState);
                if (playbackState == STATE_READY) {
                    thumbnailView.setVisibility(View.INVISIBLE);
                }
            }

        });

        playerView.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    super.onFling(e1, e2, velocityX, velocityY);

                    return true;
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    super.onSingleTapUp(e);
                    if (!player.getPlayWhenReady()) {
                        player.setPlayWhenReady(true);
                    } else {
                        player.setPlayWhenReady(false);
                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (!player.getPlayWhenReady()) {
                        player.setPlayWhenReady(true);
                    }
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        return view;
    }

}
