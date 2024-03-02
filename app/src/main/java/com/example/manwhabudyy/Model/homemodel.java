package com.example.manwhabudyy.Model;

public class homemodel {
    String url,animename;

    public homemodel(){}

    public homemodel(String url, String animename) {
        this.url = url;
        this.animename = animename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAnimename() {
        return animename;
    }

    public void setAnimename(String animename) {
        this.animename = animename;
    }
}
