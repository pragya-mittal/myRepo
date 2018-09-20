package com.swiggy;

import java.io.IOException;
import java.util.*;

public class SwiggySystemImpl implements SwiggySystem {
    Map<String , DeliveryExecutive> deliveryMap;
    Map<String , Order> orderMap;
    Map<String , String> deliveryOrder;
    JsonParser jsonParser;
    List<DeliveryExecutive> freeExceutives;
    List<Order> waitingOrders;

    DefaultScheduler defaultScheduler;

    public SwiggySystemImpl() {
        deliveryMap = new HashMap<String, DeliveryExecutive>();
        orderMap = new HashMap<String, Order>();
        deliveryOrder = new HashMap<String, String>();
        jsonParser = new JsonParser();
        freeExceutives = new ArrayList<DeliveryExecutive>();
        waitingOrders = new ArrayList<Order>();
        defaultScheduler = new DefaultScheduler(deliveryMap, orderMap, deliveryOrder, jsonParser, freeExceutives, waitingOrders);
    }

    public void addDeliveryExecutive(String deliveryInput) throws SwiggySystemException {
        deliveryMap = jsonParser.readDeliveryJSON(deliveryInput, deliveryMap);
        addFreeExecutive(deliveryMap);
    }

    public void addFreeExecutive(Map<String, DeliveryExecutive> deliveryMap) throws SwiggySystemException {
        if (deliveryMap.size()==0)
            throw  new SwiggySystemException("Delivery executives are empty");
        for (Map.Entry entry : deliveryMap.entrySet())
            freeExceutives.add((DeliveryExecutive) entry.getValue());
    }

    public void addOrders(String orderInput) throws SwiggySystemException {
        orderMap = jsonParser.readOrderJSON(orderInput, orderMap);
        addWaitingOrders(orderMap);
    }

    public void addWaitingOrders(Map<String, Order> orderMap) throws SwiggySystemException {
        if (orderMap.size()==0)
            throw  new SwiggySystemException("Orders are empty");
        for (Map.Entry entry : orderMap.entrySet())
            waitingOrders.add((Order) entry.getValue());
    }

    public void getAssignments(String orderOutput) throws IOException, SwiggySystemException {
        defaultScheduler.getAssignments(orderOutput);
    }

//    public void getAssignments(String orderOutput) throws SwiggySystemException, IOException {
//        if ((orderMap.isEmpty()) || (deliveryMap.isEmpty()))
//            throw new SwiggySystemException("Order/delivery cannot be empty");
//
//        if (freeExceutives.size()==0)
//            System.out.println("No free executive available");
//
//        Collections.sort(waitingOrders, new SortOrders());
//        for (Order order : waitingOrders) {
//            if (freeExceutives.size()==0) {
//                System.out.println("No free executive available");
//                break;
//            }
//            SortExecutives sortExecutives = new SortExecutives(order);
//            Collections.sort(freeExceutives, sortExecutives);
//            DeliveryExecutive del = freeExceutives.get(0);
//            freeExceutives.remove(del);
//            del.setStatus(Status.RUNNING);
//            order.setStatus(Status.RUNNING);
//            deliveryOrder.put(order.getId(),del.getId());
//
//            deliveryMap.put(freeExceutives.get(0).getId(), del);
//            orderMap.put(order.getId(), order);
//        }
//
//        jsonParser.jsonWrite(orderOutput, deliveryOrder);
//
//    }

    public static void main(String[] args) throws SwiggySystemException, IOException {

//        /Users/pragya.mittal/workspace/inmobi/design/DeliveryAssignment/src/main/resources/delivery.json
//        /Users/pragya.mittal/workspace/inmobi/design/DeliveryAssignment/src/main/resources/orders.json
//        /Users/pragya.mittal/workspace/inmobi/design/DeliveryAssignment/src/main/resources/output.json
        Scanner sc = new Scanner(System.in);
        String deliveryInput = sc.nextLine();
        String orderInput = sc.nextLine();
        String orderOutput = sc.nextLine();

        SwiggySystemImpl swiggySystem = new SwiggySystemImpl();
        swiggySystem.addDeliveryExecutive(deliveryInput);
        swiggySystem.addOrders(orderInput);
        swiggySystem.getAssignments(orderOutput);

    }
}
