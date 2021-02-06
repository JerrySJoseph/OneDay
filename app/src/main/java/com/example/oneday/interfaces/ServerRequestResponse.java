package com.example.oneday.interfaces;

import org.json.JSONObject;

public interface ServerRequestResponse {
    void onResponse(String response);
    void onError(String error);
}
