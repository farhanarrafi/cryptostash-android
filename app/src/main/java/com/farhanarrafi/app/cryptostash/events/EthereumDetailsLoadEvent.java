package com.farhanarrafi.app.cryptostash.events;

public class EthereumDetailsLoadEvent {
    private final String json;
    private final String error;

    public EthereumDetailsLoadEvent(String json, String error) {
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
