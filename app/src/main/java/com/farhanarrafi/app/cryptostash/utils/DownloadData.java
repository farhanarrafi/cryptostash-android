package com.farhanarrafi.app.cryptostash.utils;

import com.farhanarrafi.app.cryptostash.events.EthereumDetailsLoadEvent;
import com.farhanarrafi.app.cryptostash.events.EthereumItemsLoadEvent;

import org.greenrobot.eventbus.EventBus;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadData {

    private static final String TAG = "DownloadData";

    private OkHttpClient client;

    public DownloadData() {
        this.client = new OkHttpClient();
    }

    public void downloadEthereumJSON(String url) {
        Response response = null;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String result = "";
                String error = "Got empty response from server.";
                EventBus.getDefault().post(new EthereumItemsLoadEvent(result, error));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = "";
                String error = "";
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        result = response.body().string();
                    } else {
                        error = "Got empty response from server.";
                    }
                } else {
                    error = "Could not get data from server.";
                }
                EventBus.getDefault().post(new EthereumItemsLoadEvent(result, error));
            }
        });
    }


    public void downloadEthereumDetailsJSON(String url) {
        Response response = null;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String result = "";
                String error = "Got empty response from server.";
                EventBus.getDefault().post(new EthereumItemsLoadEvent(result, error));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = "";
                String error = "";
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        result = response.body().string();
                    } else {
                        error = "Got empty response from server.";
                    }
                } else {
                    error = "Could not get data from server.";
                }
                EventBus.getDefault().post(new EthereumDetailsLoadEvent(result, error));
            }
        });
    }


    public void cleanUp() {
        client = null;
    }
}
