package com.example.ss.coolweather.gson;

import com.google.gson.annotations.SerializedName;

public class AQI {
    public AQICity city;
    public class AQICity{
        @SerializedName("aqi")
        public String aqi;
        @SerializedName("pm25")
        public String pm25;
        @SerializedName("qlty")
        public String qlty;
    }
}
