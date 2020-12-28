package com.bitcs.desitalent.changalite.utils;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bitcs.desitalent.changalite.models.IChangaApiResponse;
import com.android.volley.Request;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

public class ApiHelper {

    public static String getNextPopularVideoUrl(int page) { // videoId is optional. Pass it for this video to come on top.
        return "https://apiv2.changa.in/api/v1/video/popular-videos" + "?page=" + page;
    }

    public static <T> T getObject(JSONObject data, Class<T> modelClass) throws IOException {
        return getObjectMapper().readValue(data.toString(), modelClass);
    }

    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    private static <T> void makeAPIRequest(int method, final String url, JSONObject postData, final Class<T> modelClass, final IChangaApiResponse apiResponse) {
        postData = postData == null ? new JSONObject() : postData;

        RequestQueue requestQueue = VolleySingelton.getInstance().getRequestQueue();

        JsonObjectRequest request = new JsonObjectRequest(method, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("response", "" + response);
                            T classObject = getObject(response, modelClass);
                            Log.i("object", "" + classObject);
                            apiResponse.onResponse(null, classObject);
                        } catch (IOException e) {
                            apiResponse.onResponse("Issue while parsing class object", null);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.onResponse(error.getMessage(), null);
            }
        });
        requestQueue.add(request);
    }

    public static <T> void makeGetRequest(String url, final Class<T> modelClass, final IChangaApiResponse apiResponse) {
        makeAPIRequest(Request.Method.GET, url, null, modelClass, apiResponse);
    }

    public static <T> void makePostRequest(String url, JSONObject postData, final Class<T> modelClass, final IChangaApiResponse apiResponse) {
        makeAPIRequest(Request.Method.POST, url, postData, modelClass, apiResponse);
    }

    public static <T> void makePutRequest(String url, JSONObject postData, final Class<T> modelClass, final IChangaApiResponse apiResponse) {
        makeAPIRequest(Request.Method.PUT, url, postData, modelClass, apiResponse);
    }

    public static <T> void makeDeleteRequest(String url, final IChangaApiResponse apiResponse) {
        makeAPIRequest(Request.Method.DELETE, url, null, JSONObject.class, apiResponse);
    }
}
