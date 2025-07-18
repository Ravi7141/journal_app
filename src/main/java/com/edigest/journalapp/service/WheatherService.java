package com.edigest.journalapp.service;

import com.edigest.journalapp.api.response.WheatherResponse;
import com.edigest.journalapp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WheatherService {

    private static final String apiKey ="3d40954d54b817f601eaa760c2474adf";

//    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WheatherResponse getWeather(String city) {
        WheatherResponse wheatherResponse = redisService.get("weather_of_" + city, WheatherResponse.class);
        if (wheatherResponse != null) {
            return wheatherResponse;
        }
        else{
            String url = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<apiKey>", apiKey).replace("<city>", city);
            ResponseEntity<WheatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WheatherResponse.class);
            WheatherResponse body = response.getBody();
            if( body != null) {
                redisService.set("weather_of_" + city, body, 300l); // Cache for 24 hours
            }
            return body;
        }


    }
}
