package com.farhanarrafi.app.cryptostash.model;


import android.widget.ImageView;

import androidx.databinding.BindingAdapter;


import com.farhanarrafi.app.cryptostash.utils.CSLog;
import com.squareup.picasso.Picasso;


public final class Ethereum {

    private String openPrice;
    private String lowPrice;
    private String highPrice;
    private String lastPrice;

    private String shortName;
    private String FullName;
    private String priceChange;
    private String symbolUrl;
    private String symbol;

    public Ethereum() {
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getSymbolUrl() {
        return this.symbolUrl;
    }

    public void setSymbolUrl(String symbolUrl) {
        this.symbolUrl = symbolUrl;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    //    @BindingAdapter({"imageUrl"})
//    public void setImageUrl(ImageView view, String poserPath){
//        Picasso.get().load("http://image.tmdb.org/t/p/w185"+
//                poserPath).into(view);
//
//    }

//    @BindingAdapter({"imageUrl", "error"})
//    public static void loadImage(ImageView view, String url, Drawable error) {
//        Picasso.get().load(url).error(error).into(view);
//    }

    @BindingAdapter("app:image_url")
    public static void imageUrl(ImageView view, String image_Url) {
        CSLog.d("Ethereum image_Url " + image_Url);
        Picasso.get().load(image_Url).into(view);
    }

    @Override
    public String toString() {
        return "Ethereum{" +
                "openPrice='" + openPrice + '\'' +
                ", lowPrice='" + lowPrice + '\'' +
                ", highPrice='" + highPrice + '\'' +
                ", lastPrice='" + lastPrice + '\'' +
                ", shortName='" + shortName + '\'' +
                ", FullName='" + FullName + '\'' +
                ", priceChange='" + priceChange + '\'' +
                ", symbolUrl='" + symbolUrl + '\'' +
                '}';
    }
}
