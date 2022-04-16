package com.farhanarrafi.app.cryptostash.events;

public class EthereumItemsLoadEvent {
    private final String json;
    private final String error;

    public EthereumItemsLoadEvent(String json, String error) {
        this.json = json;
        this.error = error;
    }

    public String getJson() {
        return json;
    }

    public String getError() {
        return error;
    }
}
