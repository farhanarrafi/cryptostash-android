package com.farhanarrafi.app.cryptostash.model;

/**
 *
 * {
 *     "symbol": "btcinr",
 *     "baseAsset": "btc",
 *     "quoteAsset": "inr",
 *     "openPrice": "704999.0",
 *     "lowPrice": "702603.0",
 *     "highPrice": "730001.0",
 *     "lastPrice": "720101.0",
 *     "volume": "891.8329",
 *     "bidPrice": "720102.0",
 *     "askPrice": "722999.0",
 *     "at": 1588829734
 *   }
 */
public class EthereumJson {
    private String symbol;
    private String baseAsset;
    private String quoteAsset;
    private String openPrice;
    private String lowPrice;
    private String highPrice;
    private String lastPrice;
    private String volume;
    private String bidPrice;
    private String askPrice;
    private String at;

    public EthereumJson() {

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }
}
