package com.farhanarrafi.app.cryptostash.adapter;

import android.content.Context;
import android.content.res.AssetManager;

import com.farhanarrafi.app.cryptostash.model.Ethereum;
import com.farhanarrafi.app.cryptostash.model.EthereumJson;
import com.farhanarrafi.app.cryptostash.utils.Constants;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import com.squareup.moshi.Types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class EthereumJsonAdapter {

    private Map<String,String> cryptoCurrencyNameMap;

    public EthereumJsonAdapter(Context context) {
        loadCryptoCurrencyNameMap(context);
    }

    @FromJson
    Ethereum eventFromJson(EthereumJson ethereumJson) {
        Ethereum ethereum = new Ethereum();
        ethereum.setShortName(ethereumJson.getBaseAsset());
        ethereum.setSymbol(ethereumJson.getSymbol());
        if(cryptoCurrencyNameMap != null) {
            ethereum.setFullName(cryptoCurrencyNameMap.get(ethereumJson.getBaseAsset()));
        }
        ethereum.setPriceChange(String.valueOf(Double.parseDouble(ethereumJson.getOpenPrice())-Double.parseDouble(ethereumJson.getLastPrice())));
        ethereum.setLastPrice(ethereumJson.getLastPrice());
        ethereum.setOpenPrice(ethereumJson.getOpenPrice());
        ethereum.setHighPrice(ethereumJson.getHighPrice());
        ethereum.setLowPrice(ethereumJson.getLowPrice());
        ethereum.setSymbolUrl(Constants.ICON_URL_PREFIX + ethereumJson.getBaseAsset() + Constants.ICON_URL_POSTFIX);
        return ethereum;
    }

    @ToJson
    EthereumJson eventToJson(Ethereum event) {
        EthereumJson json = new EthereumJson();

        return json;
    }

    /**
     * https://stackoverflow.com/a/9544781/3148856
     * @param context application context
     */
    private void loadCryptoCurrencyNameMap(Context context) {

        String json = loadAssetFile(context, "cryptocurrencies.json", "");

        Type type = Types.newParameterizedType(Map.class, String.class, String.class);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Map<String,String>> adapter = moshi.adapter(type);

        try {
            cryptoCurrencyNameMap = adapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * https://stackoverflow.com/a/59568835/3148856
     * @param context
     * @param fileName
     * @param defaultValue
     * @return
     */
    public static String loadAssetFile(Context context, String fileName, String defaultValue) {
        String result=defaultValue;
        InputStreamReader inputStream=null;
        BufferedReader bufferedReader=null;
        try {
            inputStream = new InputStreamReader(context.getAssets().open(fileName));
            bufferedReader = new BufferedReader(inputStream);
            StringBuilder out= new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                out.append(line);
                line = bufferedReader.readLine();
            }
            result=out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(inputStream).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Objects.requireNonNull(bufferedReader).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
