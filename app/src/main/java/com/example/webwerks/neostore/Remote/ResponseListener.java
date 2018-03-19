package com.example.webwerks.neostore.Remote;

import retrofit2.Response;

public interface ResponseListener {
    void onResponseSuccess(Response baseResponse);

    void onResponseFailure(Throwable throwable);
}
