package com.ecom.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ecom.app.model.entity.ItemCart;

public class ListConverter {

    public static List<ItemCart> convertStringToOrderItems(String orderItemsString) {
        List<ItemCart> orderItems = new ArrayList<>();

        // Check if the string is not empty and contains items
        if (orderItemsString != null && orderItemsString.length() > 2) { // Assuming at least "[]" is present

            // Use regular expression to extract ItemCart strings
            Pattern pattern = Pattern.compile("com\\.ecom\\.app\\.model\\.entity\\.ItemCart@[a-f0-9]+");
            Matcher matcher = pattern.matcher(orderItemsString);

            while (matcher.find()) {
                String itemCartString = matcher.group();

                // Extract the unique identifier (assuming it's a hexadecimal number)
                String itemId = itemCartString.substring(itemCartString.lastIndexOf('@') + 1);

                // Create a new ItemCart instance and add it to the list
                ItemCart itemCart = new ItemCart();
                itemCart.setId(Long.parseLong(itemId, 16));
                orderItems.add(itemCart);
            }
        }

        return orderItems;
    }
}