package com.github.nicon777.kafkaexample.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @BeforeAll
    static void setUp(){
    }

    @Test
    public void checkOrdersAreEqual(){
        Order order1 = new Order(1, "Nicolas Nunez", "3600 Bristol Motor Pass", null, 1f, new Date());
        Order order2 = new Order(1, "Nicolas Nunez", "3600 Bristol Motor Pass", null, 1f, new Date());

        assertEquals(order1, order2, "Orders are equal");
    }

    @Test
    public void checkOrdersAreEqualSecond(){
        Order order1 = new Order(1, "Nicolas Nunez", "3600 Bristol Motor Pass", null, 1f, new Date());
        Order order2 = new Order(1, "Nicolas Nunez", "3600 Bristol Motor Pass", null, 1f, new Date());

        assertEquals(order1, order2, "Orders are equal");
    }

    @Test
    public void checkDeduplicateOrders(){
        Date now = new Date();
        List<Order> duplicateOrders = Arrays.asList(
                new Order(
                        1, "Nicolas Nunez",
                        "3600 Bristol Motor Pass", null,
                        1f, now),
                new Order(1, "Nicolas Nunez",
                        "3600 Bristol Motor Pass", null,
                        1f, now),
                new Order(1, "Nicolas Nunez",
                        "3600 Bristol Motor Pass", null,
                        1f, now));

        List<Order> noDuplicates = Arrays.asList(new Order(1, "Nicolas Nunez",
                "3600 Bristol Motor Pass", null,
                1f, now));


        assertEquals(noDuplicates, deduplicate(duplicateOrders), "WOMP WOMP!");
        assertEquals(1, deduplicate(duplicateOrders).size());

    }

    private List deduplicate(List<Order> orderList) {
        return new ArrayList<>(new HashSet<>(orderList));
    }


    @AfterEach
    public void tearDownEach(){
        System.out.println("bye");
    }

    @AfterAll
    static void tearDown(){
        System.out.println("All complete");
    }
}
