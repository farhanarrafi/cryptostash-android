package com.farhanarrafi.app.cryptostash.utils;

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

//    public void downloadEthereumJSON(String url) {
//
//        String result = "";
//        String error = "";
//
//        Response response = null;
//
//        Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//
//        try  {
//            response = client.newCall(request).execute();
//
//            if(response != null) {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        result = response.body().string();
//                    } else {
//                        error = "Got empty response from server.";
//                    }
//                } else {
//                    error = "Could not get data from server.";
//                }
//            }
//        } catch (Exception exception) {
//            CSLog.e(TAG, exception.getMessage());
//        } finally {
//            if(response != null) {
//                response.close();
//            }
//        }
//        //result = "[{\"symbol\":\"btcinr\",\"baseAsset\":\"btc\",\"quoteAsset\":\"inr\",\"openPrice\":\"3171639\",\"lowPrice\":\"3170428.0\",\"highPrice\":\"3235291.0\",\"lastPrice\":\"3213959.0\",\"volume\":\"75.66736\",\"bidPrice\":\"3214009.0\",\"askPrice\":\"3219210.0\",\"at\":1650094364000},{\"symbol\":\"xrpinr\",\"baseAsset\":\"xrp\",\"quoteAsset\":\"inr\",\"openPrice\":\"60.7\",\"lowPrice\":\"60.5111\",\"highPrice\":\"63.1458\",\"lastPrice\":\"62.1992\",\"volume\":\"480056.5\",\"bidPrice\":\"62.0801\",\"askPrice\":\"62.1981\",\"at\":1650094364000},{\"symbol\":\"ethinr\",\"baseAsset\":\"eth\",\"quoteAsset\":\"inr\",\"openPrice\":\"240000\",\"lowPrice\":\"239000.0\",\"highPrice\":\"242551.7\",\"lastPrice\":\"241400.7\",\"volume\":\"57.3549\",\"bidPrice\":\"241400.7\",\"askPrice\":\"241611.9\",\"at\":1650094364000},{\"symbol\":\"trxinr\",\"baseAsset\":\"trx\",\"quoteAsset\":\"inr\",\"openPrice\":\"4.8347\",\"lowPrice\":\"4.8233\",\"highPrice\":\"4.9404\",\"lastPrice\":\"4.92\",\"volume\":\"9586495.0\",\"bidPrice\":\"4.92\",\"askPrice\":\"4.9226\",\"at\":1650094364000}]";
//
//        EventBus.getDefault().post(new EthereumItemsLoadEvent(result, error));
//    }

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


    public void cleanUp() {
        client = null;
    }
}
