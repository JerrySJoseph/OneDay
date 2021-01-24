package com.example.oneday.interfaces;

public interface FirebaseSaveResponse {
    void onSaveSuccess();
    void onSaveFailure(String reason);
    void onSaveError(String reason);
}
