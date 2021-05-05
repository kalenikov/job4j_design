package ru.job4j.io.serialization.xml;

public class OrderLine {
    private String productCode;
    private int productCount;

    public OrderLine(String productCode, int productCount) {
        this.productCode = productCode;
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "productCode='" + productCode + '\'' +
                ", productCount=" + productCount +
                '}';
    }
}
