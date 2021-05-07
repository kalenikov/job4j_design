package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;


public class SimpleJsonOrder {
    public static void main(String[] args) {
        Order order = new Order(10,
                "ТЕСТОВЫЙ ЗАКАЗ",
                Arrays.asList(
                        new OrderLine("PN1", 5),
                        new OrderLine("PN2", 15)
                ),
                new String[]{"ПАРТИЯ1", "ПАРТИЯ50"}
                , true);

        JSONArray jsonOrderLines =  new JSONArray();
        order.getOrderLines().forEach(jsonOrderLines::put);

        JSONObject jsonOrder = new JSONObject();
        jsonOrder.put("customerID", order.getCustomerID());
        jsonOrder.put("comment", order.getComment());
        jsonOrder.put("isInnerOrder", order.isInnerOrder());
        jsonOrder.put("orderLines", jsonOrderLines);
        jsonOrder.put("partyIds", order.getPartyIds());

        System.out.println(jsonOrder.toString());

        System.out.println(new JSONObject(order).toString());
    }

}
