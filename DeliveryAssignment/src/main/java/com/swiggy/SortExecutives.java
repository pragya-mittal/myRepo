package com.swiggy;

import java.util.Comparator;

public class SortExecutives implements Comparator<DeliveryExecutive>
{
    Order order;
    float endLatOrder;
    float endLongOrder;
    public SortExecutives(Order order) {
        this.order = order;
        endLatOrder = Float.parseFloat(order.getLocation().getLatitide());
        endLongOrder = Float.parseFloat(order.getLocation().getLongitude());
    }

    public int compare(DeliveryExecutive o1, DeliveryExecutive o2) {
        float startLat = Float.parseFloat(o1.getLocation().getLatitide());
        float startLong = Float.parseFloat(o1.getLocation().getLongitude());

        float endLat = Float.parseFloat(o2.getLocation().getLatitide());
        float endLong = Float.parseFloat(o2.getLocation().getLongitude());

        float distanceO1 = (float) Haversine.distance(startLat, startLong, endLatOrder
                , endLongOrder);

        float distanceO2 = (float) Haversine.distance(endLat, endLong, endLatOrder
                , endLongOrder);

//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            System.out.println( sdf.format(cal.getTime()) );
//            String currTime = sdf.format(cal.getTime());

        if (distanceO1!=distanceO2)
            return (int) (distanceO2-distanceO1);
        else
            return o2.getTimestamp().compareTo(o1.getTimestamp());

    }
}
