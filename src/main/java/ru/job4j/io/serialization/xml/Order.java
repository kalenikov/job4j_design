package ru.job4j.io.serialization.xml;

import java.util.Arrays;
import java.util.List;

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

    }
}




