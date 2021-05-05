package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "order")
public class Order {
    @XmlAttribute
    private int customerID;
    @XmlAttribute
    private String comment;

    @XmlElementWrapper(name = "orderLines")
    @XmlElement(name = "orderLine")
    private List<OrderLine> orderLines;

    @XmlElementWrapper(name = "partyIds")
    @XmlElement(name = "partyId")
    private String[] partyIds;

    @XmlAttribute
    private boolean isInnerOrder;

    public Order() {
    }

    public Order(int customerID, String comment, List<OrderLine> orderLines, String[] partyIds, boolean isInnerOrder) {
        this.customerID = customerID;
        this.comment = comment;
        this.orderLines = orderLines;
        this.partyIds = partyIds;
        this.isInnerOrder = isInnerOrder;
    }

    public static void main(String[] args) throws JAXBException {

        final Order order = new Order(10,
                "ТЕСТОВЫЙ ЗАКАЗ",
                Arrays.asList(
                        new OrderLine("PN1", 5),
                        new OrderLine("PN2", 15)
                ),
                new String[]{"ПАРТИЯ1", "ПАРТИЯ50"},
                true);

        JAXBContext context = JAXBContext.newInstance(Order.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(order, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Order result = (Order) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
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
}




