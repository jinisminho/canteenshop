package com.spring2020.coffeeshop.coffee.shop;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class CoffeeShopApplicationTests {

    @Test
    void contextLoads() {
        String x = "1581991919000";

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        long milliSeconds = Long.parseLong(x);
        System.out.println(milliSeconds);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        System.out.println(formatter.format(calendar.getTime()));
    }

}
