package com.swiggy;

import java.util.Comparator;

public class SortOrders implements Comparator<Order>
{
    public int compare(Order o1, Order o2) {
        return o2.getTimestamp().compareTo(o1.getTimestamp());

    }
}
