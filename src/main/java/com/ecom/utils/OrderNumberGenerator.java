package com.ecom.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@OrderNoQualifier
public class OrderNumberGenerator implements TransactionNumberGenerator{

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "ORD" + dateFormat.format(new Date()) + "-" + ThreadLocalRandom.current().nextInt(2000, 3000 + 1);
    }
}
