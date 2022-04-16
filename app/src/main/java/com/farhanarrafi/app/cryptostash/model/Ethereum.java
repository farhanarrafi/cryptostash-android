package com.farhanarrafi.app.cryptostash.model;


import android.widget.ImageView;

import androidx.databinding.BindingAdapter;


import com.squareup.picasso.Picasso;


public final class Ethereum {

    private String openPrice;
    private String lowPrice;
    private String highPrice;
    private String lastPrice;

    private String shortName;
    private String FullName;
    private String priceChange;

    private static final String symbolUrlPrefix = "https://github.com/spothq/cryptocurrency-icons/blob/master/32/color/";
    private static final String symbolUrlPostfix = ".png";
    private String symbolUrl;

    public Ethereum() {
    }

    public Ethereum(String shortName, String fullName, String priceChange, String openPrice, String lowPrice, String highPrice, String lastPrice, String symbolUrl) {
        this.shortName = shortName;
        FullName = fullName;
        this.priceChange = priceChange;
        this.openPrice = openPrice;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.lastPrice = lastPrice;
        this.symbolUrl = symbolUrl;
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
        return symbolUrl;
    }

    public void setSymbolUrl(String symbolUrl) {
        this.symbolUrl = symbolUrlPrefix + shortName + symbolUrlPostfix;
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

    @BindingAdapter("image_Url")
    public void setImageUrl(ImageView view, String image_Url) {
        Picasso.get().load(image_Url).into(view);
    }
}
