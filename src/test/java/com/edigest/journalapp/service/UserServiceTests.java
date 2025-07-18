package com.edigest.journalapp.service;

import com.edigest.journalapp.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;


    @ParameterizedTest
    @ValueSource(strings = {
            "Ram",
            "Shyam",
            "Hari"
    })
    public void testAdd(String name) {
        assertNotNull(userRepo.findByUsername(name),"failed for "+name);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,3",
            "2,3,5",
            "3,4,7"
    })
    public void test(int a ,int b,int expected){
        assertEquals(expected,a+b);
    }
}
