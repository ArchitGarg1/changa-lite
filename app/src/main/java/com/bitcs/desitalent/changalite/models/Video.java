package com.bitcs.desitalent.changalite.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.database.PropertyName;

import java.util.ArrayList;

/**
 * Created by shubhamagrawal on 21/12/17.
 */

public class Video implements Parcelable {

    @PropertyName("id")
    @JsonProperty("id")
    public String id;

    @PropertyName("categories")
    @JsonProperty("categories")
    public ArrayList<String> categories;

    @PropertyName("shareCount")
    @JsonProperty("shareCount")
    public Integer shareCount;

    @PropertyName("likesCount")
    @JsonProperty("likesCount")
    public Integer likesCount;

    @PropertyName("commentCount")
    @JsonProperty("commentCount")
    public Integer commentCount;

    @PropertyName("viewCount")
    @JsonProperty("viewCount")
    public Integer viewCount;

    @PropertyName("liked")
    @JsonProperty("liked")
    public Boolean liked;

    @PropertyName("videoUrl")
    @JsonProperty("videoUrl")
    public String videoUrl;

    @PropertyName("thumUrl")
    @JsonProperty("thumUrl")
    public String thumUrl;

    @PropertyName("description")
    @JsonProperty("description")
    public String description;

    @PropertyName("hashtags")
    @JsonProperty("hashtags")
    public ArrayList<String> hashtags;

    @PropertyName("isUploading")
    @JsonProperty("isUploading")
    public boolean isUploading;

    @PropertyName("isDraftTile")
    @JsonProperty("isDraftTile")
    public boolean isDraftTile;

    @PropertyName("isDraftVideo")
    @JsonProperty("isDraftVideo")
    public boolean isDraftVideo;

    @PropertyName("bitmapImageUrl")
    @JsonProperty("bitmapImageUrl")
    public Bitmap bitmapImageUrl;

    protected Video(Parcel in) {
        id = in.readString();
        categories = in.createStringArrayList();
//        sound = in.readParcelable(Sound.class.getClassLoader());
//        user = in.readParcelable(User.class.getClassLoader());
        if (in.readByte() == 0) {
            shareCount = null;
        } else {
            shareCount = in.readInt();
        }
        if (in.readByte() == 0) {
            likesCount = null;
        } else {
            likesCount = in.readInt();
        }
        if (in.readByte() == 0) {
            commentCount = null;
        } else {
            commentCount = in.readInt();
        }
        if (in.readByte() == 0) {
            viewCount = null;
        } else {
            viewCount = in.readInt();
        }
        byte tmpLiked = in.readByte();
        liked = tmpLiked == 0 ? null : tmpLiked == 1;
        videoUrl = in.readString();
        thumUrl = in.readString();
        description = in.readString();
        hashtags = in.createStringArrayList();
        isUploading = in.readByte() != 0;
        isDraftTile = in.readByte() != 0;
        isDraftVideo = in.readByte() != 0;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeStringList(categories);
//        dest.writeParcelable(sound, flags);
//        dest.writeParcelable(user, flags);
        if (shareCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(shareCount);
        }
        if (likesCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likesCount);
        }
        if (commentCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(commentCount);
        }
        if (viewCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(viewCount);
        }
        dest.writeByte((byte) (liked == null ? 0 : liked ? 1 : 2));
        dest.writeString(videoUrl);
        dest.writeString(thumUrl);
        dest.writeString(description);
        dest.writeStringList(hashtags);
        dest.writeByte((byte) (isUploading ? 1 : 0));
        dest.writeByte((byte) (isDraftTile ? 1 : 0));
        dest.writeByte((byte) (isDraftVideo ? 1 : 0));
    }
}
