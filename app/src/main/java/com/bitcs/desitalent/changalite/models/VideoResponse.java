package com.bitcs.desitalent.changalite.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IgnoreExtraProperties
public class VideoResponse {

    @PropertyName("success")
    @JsonProperty("success")
    public boolean success;

    @PropertyName("data")
    @JsonProperty("data")
    public ArrayList<Video> data;
}
