package com.bitcs.desitalent.changalite.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;

public class PlayerUtil {

    public static SimpleExoPlayer createPlayer(String url, Context context) {

        /* Instantiate a DefaultLoadControl.Builder. */
        DefaultLoadControl.Builder builder = new
                DefaultLoadControl.Builder();

        /* Maximum amount of media data to buffer (in milliseconds). */
        final int loadControlMaxBufferMs = 7000;
        final int loadControlMinBufferMs = 4000;

        /*Configure the DefaultLoadControl to use our setting for how many
        Milliseconds of media data to buffer. */
        builder.setBufferDurationsMs(
                loadControlMinBufferMs,
                loadControlMaxBufferMs,
                /* To reduce the startup time, also change the line below */
                DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS,

                loadControlMinBufferMs);

        /* Build the actual DefaultLoadControl instance */
        DefaultLoadControl loadControl = builder.createDefaultLoadControl();

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context, new DefaultRenderersFactory(context), new DefaultTrackSelector(), loadControl);

        Log.d("VideoPlayerInitWithURL", url);

        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(url), new DataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return null;
            }
        }, new DefaultExtractorsFactory(), null, null);

        player.prepare(videoSource);

        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        return player;
    }

}
