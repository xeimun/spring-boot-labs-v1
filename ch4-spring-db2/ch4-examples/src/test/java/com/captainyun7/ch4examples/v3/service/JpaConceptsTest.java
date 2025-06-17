package com.captainyun7.ch4examples.v3.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaConceptsTest {

    @Autowired
    JpaBasicConcepts jpaConcepts;

    @Test
    void test_findTwice() {
        jpaConcepts.findTwice(2L);
    }
}