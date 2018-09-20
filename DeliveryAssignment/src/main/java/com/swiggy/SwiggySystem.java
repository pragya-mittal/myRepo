package com.swiggy;

import java.io.IOException;

public interface SwiggySystem {

    public void addDeliveryExecutive(String deliveryInput) throws SwiggySystemException;

    public void addOrders(String orderInput) throws SwiggySystemException;

    public void getAssignments(String orderOutput) throws SwiggySystemException, IOException;

}
