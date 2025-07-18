package com.edigest.journalapp.scheduler;

import com.edigest.journalapp.cache.AppCache;
import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.enums.Sentiment;
import com.edigest.journalapp.repository.UserRepoImpl;
import com.edigest.journalapp.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;


    @Autowired
    private AppCache appCache;

    // Every Sunday at 9 AM
    // @Scheduled(cron = "0 0 9 ? * SUN ")
    public void fetchUsersAndSendEmails() {
        List<User> users = userRepo.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCount = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment,Integer> entry : sentimentCount.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null){
                emailService.sendEmail(user.getEmail(),
                        "Weekly Sentiment Summary",
                        "Your most frequent sentiment for the past week is: " + mostFrequentSentiment.toString());
            }
            log.info("sent email to user: {} {}", user.getEmail(), mostFrequentSentiment);

        }
    }

    @Scheduled(cron = "0 */5 * * * *") // Every 5 minutes
    public void clearAppCache() {
        appCache.init();
    }
}
