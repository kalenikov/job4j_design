package ru.job4j.io.serialization.json;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Order {
    private int customerID;
    private String comment;
    private List<OrderLine> orderLines;
    private String[] partyIds;
    private boolean isInnerOrder;

    public Order(int customerID, String comment, List<OrderLine> orderLines, String[] partyIds, boolean isInnerOrder) {
        this.customerID = customerID;
        this.comment = comment;
        this.orderLines = orderLines;
        this.partyIds = partyIds;
        this.isInnerOrder = isInnerOrder;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getComment() {
        return comment;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public String[] getPartyIds() {
        return partyIds;
    }

    public boolean isInnerOrder() {
        return isInnerOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerID=" + customerID +
                ", comment='" + comment + '\'' +
                ", orderLines=" + orderLines +
                ", partyIds=" + Arrays.toString(partyIds) +
                ", isInnerOrder=" + isInnerOrder +
                '}';
    }

    public static void main(String[] args) {
        Order order = new Order(10,
                "ТЕСТОВЫЙ ЗАКАЗ",
                Arrays.asList(
                        new OrderLine("PN1", 5),
                        new OrderLine("PN2", 15)
                ),
                new String[]{"ПАРТИЯ1", "ПАРТИЯ50"}
                , true);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(order));

        String orderString = "{\"customerID\":15," +
                "\"comment\":\"РАБОЧИЙ ЗАКАЗ\"," +
                "\"orderLines\":" +
                "[{\"productCode\":\"PN1\",\"productCount\":5}," +
                "{\"productCode\":\"PN2\",\"productCount\":25}]," +
                "\"partyIds\":[\"ПАРТИЯ2\",\"ПАРТИЯ20\"]," +
                "\"isInnerOrder\":true}";
        Order orderFromString = gson.fromJson(orderString, Order.class);
        System.out.println(orderFromString);
    }
}




