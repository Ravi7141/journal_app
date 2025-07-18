package com.edigest.journalapp.cache;

import com.edigest.journalapp.entity.ConfigJournalAppEntity;
import com.edigest.journalapp.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo;
    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll();
        for(ConfigJournalAppEntity config : all) {
            appCache.put(config.getKey(), config.getValue());
        }
    }
}
