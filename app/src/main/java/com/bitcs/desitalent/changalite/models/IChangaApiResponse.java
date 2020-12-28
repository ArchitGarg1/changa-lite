package com.bitcs.desitalent.changalite.models;

public interface IChangaApiResponse {
    <T> void onResponse(String error, T classObject);
}