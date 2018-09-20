package com.swiggy;

import java.io.IOException;
import java.util.Map;

public interface Scheduler {

    public Map<String, DeliveryExecutive> getAssignments(String ordeOutput) throws SwiggySystemException, IOException;

}
