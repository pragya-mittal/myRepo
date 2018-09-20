package com.swiggy;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultScheduler implements Scheduler {

    Map<String , DeliveryExecutive> deliveryMap;
    Map<String , Order> orderMap;
    Map<String , String> deliveryOrder;
    JsonParser jsonParser;
    List<DeliveryExecutive> freeExceutives;
    List<Order> waitingOrders;


    public DefaultScheduler(Map<String, DeliveryExecutive> deliveryMap, Map<String, Order> orderMap, Map<String, String> deliveryOrder, JsonParser jsonParser, List<DeliveryExecutive> freeExceutives, List<Order> waitingOrders) {
        this.deliveryMap = deliveryMap;
        this.orderMap = orderMap;
        this.deliveryOrder = deliveryOrder;
        this.jsonParser = jsonParser;
        this.freeExceutives = freeExceutives;
        this.waitingOrders = waitingOrders;
    }

    public Map<String, DeliveryExecutive> getAssignments(String orderOutput) throws SwiggySystemException, IOException {
        if ((orderMap.isEmpty()) || (deliveryMap.isEmpty()))
            throw new SwiggySystemException("Order/delivery cannot be empty");

        if (freeExceutives.size()==0)
            System.out.println("No free executive available");

        Collections.sort(waitingOrders, new SortOrders());
        for (Order order : waitingOrders) {
            if (freeExceutives.size()==0) {
                System.out.println("No free executive available");
                break;
            }
            SortExecutives sortExecutives = new SortExecutives(order);
            Collections.sort(freeExceutives, sortExecutives);
            DeliveryExecutive del = freeExceutives.get(0);
            freeExceutives.remove(del);
            del.setStatus(Status.RUNNING);
            order.setStatus(Status.RUNNING);
            deliveryOrder.put(order.getId(),del.getId());

            deliveryMap.put(freeExceutives.get(0).getId(), del);
            orderMap.put(order.getId(), order);
//            waitingOrders.remove(order);
        }
        jsonParser.jsonWrite(orderOutput, deliveryOrder);
        System.out.println("Done");
        return deliveryMap;
    }
}
