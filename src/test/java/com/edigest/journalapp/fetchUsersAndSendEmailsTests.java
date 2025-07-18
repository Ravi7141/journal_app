package com.edigest.journalapp;

import com.edigest.journalapp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class fetchUsersAndSendEmailsTests {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void fetchUsersAndSendEmailsTest() {
        userScheduler.fetchUsersAndSendEmails();
    }
}
