package design.prateek.parking;

import java.util.*;

// cd /Users/pragya.mittal/workspace/inmobi/JavaBasic
// java -cp /Users/pragya.mittal/workspace/inmobi/JavaBasic/target/classes  design.prateek.parking.CabSharing


public class CabSharing {
    TreeSet<Driver> drivers;
    HashMap<String, Driver> driverHashMap;
    TreeSet<Customer> customers;
    HashMap<String, Customer> customerHashMap;
    List<Trip> trips;
    public CabSharing() {
        drivers = new TreeSet<Driver>(new Comparator<Driver>() {
            public int compare(Driver o1, Driver o2) {
                if (o1.avgRating > o2. avgRating) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        customers = new TreeSet<Customer>(new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                if (o1.avgRating > o2. avgRating) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        trips = new ArrayList<Trip>();
        driverHashMap = new HashMap<String, Driver>();
        customerHashMap = new HashMap<String, Customer>();
    }

    float getDriverRating(String name, Date birthdate) throws CabSharingException {
        Driver driver = driverHashMap.get(new String(name + birthdate.toString()));
        if (driver != null) {
            return driver.avgRating;
        } else {
            throw new CabSharingException("Driver not found");
        }
    }

    float getCustomerRating(String name, Date birthdate) throws CabSharingException   {
        Customer customer = customerHashMap.get(new String(name + birthdate.toString()));
        if (customer != null) {
            return customer.avgRating;
        } else {
            throw new CabSharingException("Customer not found");
        }
    }

    List<Driver> getTopDrivers(int size) {
        Iterator<Driver> iterator = drivers.iterator();
        int i = 0;
        List <Driver> res = new ArrayList<Driver>();
        while (iterator.hasNext() && i<size) {
            res.add(iterator.next());
            i++;
        }
        res.sort(new Comparator<Driver>() {
            public int compare(Driver o1, Driver o2) {
                if (o1.avgRating > o2. avgRating) {
                    return -1;
                } else if (o1.avgRating < o2.avgRating) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return res;
    }

    List<Customer> getTopCustomers(int size) {
        Iterator<Customer> iterator = customers.iterator();
        int i = 0;
        List <Customer> res = new ArrayList<Customer>();
        while (iterator.hasNext() && i<size) {
            res.add(iterator.next());
            i++;
        }
        res.sort(new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                if (o1.avgRating > o2. avgRating) {
                    return -1;
                } else if (o1.avgRating < o2.avgRating) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return res;
    }

    void addTrip(String driverName, Date driverBirthDate, float driverRating, String custName,
                 Date custBirthDate, float custRating) throws CabSharingException {
        Driver driver = driverHashMap.get(driverName + driverBirthDate.toString());
        if (driver == null) {
            throw new CabSharingException("Driver with name " + driverName + " and Birthdate "
                    + driverBirthDate.toString() + " not found");
        }
        Customer customer = customerHashMap.get(custName + custBirthDate.toString());
        if (customer == null) {
            throw new CabSharingException("Customer with name " + custName + " and Birthdate "
                    + custBirthDate.toString() + " not found");
        }
        Trip trip = new Trip(driver, customer, driverRating, custRating);
        trips.add(trip);
        long trips = driver.getNumTrips();
        float avg = driver.avgRating;
        driver.setNumTrips(trips + 1);
        driver.setAvgRating((avg * trips + driverRating)/(trips+1));
        trips = customer.numTrips;
        avg = customer.avgRating;
        customer.setNumTrips(trips + 1);
        customer.setAvgRating((avg * trips + custRating)/(trips+1));
        if (driverRating <= 1.0 && !customer.blockedDrivers.contains(driver)) {
            customer.blockedDrivers.add(driver);
        }
    }

    void addDriver(String name, Date birthdate) throws CabSharingException  {
        String id = name + birthdate.toString();
        Driver driver = new Driver(id, name);
        driverHashMap.put(id, driver);
        drivers.add(driver);
    }

    void addCustomer(String name, Date birthdate) throws CabSharingException {
        String id = name + birthdate.toString();
        Customer customer = new Customer(id, name);
        customerHashMap.put(id, customer);
        customers.add(customer);
    }

    List<Driver> eligibileDrivers(String custName, Date birthDate) throws CabSharingException  {
        Customer customer = customerHashMap.get(custName + birthDate.toString());
        List<Driver> eligibileDrivers = new ArrayList<Driver>();
        float custRating = customer.getAvgRating();
        Iterator<Driver> iterator = drivers.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            if (driver.avgRating > custRating) {
                eligibileDrivers.add(driver);
            }
        }
        List<Driver> backup = new ArrayList<Driver>();
        iterator = eligibileDrivers.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            if (customer.blockedDrivers.contains(driver)) {
                backup.add(driver);
            }
        }
        if (backup.size() == eligibileDrivers.size()) {
            return backup;
        } else {
            eligibileDrivers.removeAll(backup);
            return eligibileDrivers;
        }
    }

    public static void main(String[] args) throws CabSharingException {
        CabSharing cabSharing = new CabSharing();
        cabSharing.addCustomer("Raju", new Date(2222));
        cabSharing.addCustomer("Kaju", new Date(3333));
        cabSharing.addCustomer("Paju", new Date(4444));
        cabSharing.addCustomer("Laju", new Date(5555));
        cabSharing.addCustomer("Maju", new Date(6666));
        cabSharing.addCustomer("Naju", new Date(7777));
        cabSharing.addDriver("bhaiyya", new Date(1111));
        cabSharing.addDriver("bhaiyyagi", new Date(2222));
        cabSharing.addDriver("bhai", new Date(3333));
        cabSharing.addDriver("anna", new Date(4444));
        cabSharing.addDriver("uber", new Date(5555));
        cabSharing.addDriver("ola", new Date(6666));
        cabSharing.addTrip("bhaiyya", new Date(1111), 3.9f, "Raju", new Date(2222), 4.4f);
        cabSharing.addTrip("bhaiyyagi", new Date(2222), 4.1f, "Raju", new Date(2222), 4.3f);
        cabSharing.addTrip("bhai", new Date(3333), 1.0f, "Paju", new Date(4444), 3.5f);
        cabSharing.addTrip("bhai", new Date(3333), 3.8f, "Laju", new Date(5555), 3.7f);
        cabSharing.addTrip("anna", new Date(4444), 4.8f, "Maju", new Date(6666), 4.1f);
        cabSharing.addTrip("anna", new Date(4444), 5.0f, "Naju", new Date(7777), 3.9f);
        cabSharing.addTrip("uber", new Date(5555), 1.0f, "Raju", new Date(2222), 4.3f);
        cabSharing.addTrip("uber", new Date(5555), 4.7f, "Maju", new Date(6666), 3.4f);
        cabSharing.addTrip("ola", new Date(6666), 3.5f, "Paju", new Date(4444), 5.0f);
        cabSharing.addTrip("ola", new Date(6666), 4.5f, "Naju", new Date(7777), 4.0f);
        List<Customer> customers = cabSharing.getTopCustomers(7);
        System.out.println("Top Customers " + customers.toString());
        List<Driver> drivers = cabSharing.getTopDrivers(7);
        System.out.println("Top Drivers " + drivers.toString());
        drivers = cabSharing.eligibileDrivers("Raju", new Date(2222));
        System.out.println("Drivers for Raju " + drivers.toString());
    }
}
