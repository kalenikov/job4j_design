package ru.job4j.io.serialization.xml;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "orderLine")
public class OrderLine {
    @XmlAttribute
    private String productCode;
    @XmlAttribute
    private int productCount;

    public OrderLine() {
    }

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
