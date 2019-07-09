package com.hfad.weatherlive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("main")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String iconId;


    public Weather(String description, String iconId) {
        this.description = description;
        this.iconId = iconId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }
}
