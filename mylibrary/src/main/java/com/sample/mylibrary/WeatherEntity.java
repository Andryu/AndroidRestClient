package com.sample.mylibrary;

import java.util.List;

/**
 * Created by shunsuke_andoh on 15/07/25.
 */
public class WeatherEntity {
    public String base;
    public List<Weather> weather;

    public class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }


}
