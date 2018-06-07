package com.example.webwerks.neostore.remote;

import retrofit2.Response;

public interface ResponseListener {
    void onResponseSuccess(Response baseResponse);

    void onResponseFailure(Throwable throwable);
}
