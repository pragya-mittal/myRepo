package design.CabCompany;

import java.util.*;

public class CabShare {

    TreeSet<Driver> driverSet;
    TreeSet<Customer> customerSet;
    List<Trip> tripSet;
    int totalTrips;

    Map<Integer, List<Integer>> customerBlockedDriverList; //  customer, blocked driver list

    Map<Integer, Integer> driverMap; // driverId, driver rating
    Map<Integer, Integer> customerMap; //  customer, customer rating

    Map<Integer, Driver> drivers; // driverId, driver rating
    Map<Integer, Customer> customers; //  customer, customer rating

    Map<Integer, List<Integer>> customerDriverList; //  customer, driver list

    public CabShare() {
        driverSet = new TreeSet<>();
        customerSet = new TreeSet<>();
        tripSet = new ArrayList<>();
        driverMap = new HashMap<Integer, Integer>();
        customerMap = new HashMap<Integer, Integer>();
        drivers = new HashMap<Integer, Driver>();
        customers = new HashMap<Integer, Customer>();
        customerDriverList = new HashMap<Integer, List<Integer>>();
        customerBlockedDriverList = new HashMap<Integer, List<Integer>>();
        totalTrips=0;
    }

    private void addDriver(Driver driver, int rating, int totalTrips) throws CabShareException {
        if ((driver==null) || (totalTrips<0) || (rating<0) || (rating>5))
            throw new CabShareException();
        int trips = 0;
        if (drivers.containsKey(driver.getDriverId())) {
            trips = drivers.get(driver.getDriverId()).getTotalTrips();
            rating = (drivers.get(driver.getDriverId()).getRating()*trips + rating)/(trips+1);
        }
        trips = trips + 1;
        driverMap.put(driver.driverId, rating);
        driver.setRating(rating);
        driver.setTotalTrips(trips);
        drivers.put(driver.getDriverId(), driver);
        driverSet.add(driver);

    }

    private void addCustomer(Customer customer, int rating, int totalTrips) throws CabShareException {
        if ((customer==null) || (totalTrips<0) || (rating<0) || (rating>5))
            throw new CabShareException();
        int trips = 0;
        if (customers.containsKey(customer.getCustomerId())) {
            trips = customers.get(customer.getCustomerId()).getTotalTrips();
            rating = (customers.get(customer.getCustomerId()).getRating()*trips + rating)/(trips+1);
        }
        trips = trips + 1;
        customer.setRating(rating);
        customer.setTotalTrips(trips);
        customerMap.put(customer.customerId, rating);
        customers.put(customer.customerId, customer);
        customerSet.add(customer);
    }

    private void addTrip(Trip trip) throws CabShareException {
        if (trip==null)
            throw new CabShareException();
        tripSet.add(trip);
    }

    private void addCustomerDriver(Customer customer, Driver driver, int driverRating) throws CabShareException {
        if ((customer==null) || (driver==null) || (driverRating<0) || (driverRating>5))
            throw new CabShareException();
        List<Integer> drivers;
        if(customerDriverList.containsKey(customer.getCustomerId())) {
            drivers = customerDriverList.get(customer.getCustomerId());
            drivers.add(driver.getDriverId());
        } else {
            drivers  = new ArrayList<>();
            drivers.add(driver.getDriverId());
        }
        customerDriverList.put(customer.getCustomerId(), drivers);
        addBlockedDriver(customer, driver, driverRating);
    }

    private void addBlockedDriver(Customer customer, Driver driver, int driverRating) throws CabShareException {
        if ((customer==null) || (driver==null) || (driverRating<0) || (driverRating>5))
            throw new CabShareException();
        if (driverRating==1) {
            List<Integer> blockedDrivers;
            if(customerBlockedDriverList.containsKey(customer.getCustomerId())) {
                blockedDrivers = customerBlockedDriverList.get(customer.getCustomerId());
                blockedDrivers.add(driver.getDriverId());
            } else {
                blockedDrivers  = new ArrayList<>();
                blockedDrivers.add(driver.getDriverId());
            }
            customerBlockedDriverList.put(customer.getCustomerId(), blockedDrivers);
        }
    }

    public int addTrip(Customer customer, Driver driver, Location location, int custRating, int driverRating) throws CabShareException {
        if ((customer==null) || (driver==null) || (driverRating<0) || (driverRating>5) || (custRating<0) || (custRating>5))
            throw new CabShareException();
        totalTrips++;
        Random random = new Random();
        Trip trip = new Trip(random.nextInt(1000), driver, driverRating, customer, custRating, location);
        addTrip(trip);
        addCustomer(customer, custRating, totalTrips);
        addDriver(driver, driverRating, totalTrips);
        addCustomerDriver(customer, driver, driverRating);
        return trip.getTripId();
    }

    public int getDriverRating(Driver driver) throws CabShareException {
        if (driver==null)
            throw new CabShareException();
        if (driverMap.containsKey(driver.getDriverId()))
            return driverMap.get(driver.getDriverId());
        else
            return -1;
    }

    public int getCustomerRating(Customer customer) throws CabShareException {
        if (customer==null)
            throw new CabShareException();
        if (customerMap.containsKey(customer.getCustomerId()))
            return customerMap.get(customer.getCustomerId());
        else
            return -1;
    }

    public List<Integer> getEligibleDrivers(Customer customer) throws CabShareException {
        if (customer==null)
            throw new CabShareException();
        List<Integer> totalDrivers = customerDriverList.get(customer.getCustomerId());
        List<Integer> blockedDrivers = customerBlockedDriverList.get(customer.getCustomerId());

        if (blockedDrivers!=null)
            totalDrivers.removeAll(blockedDrivers);

        if (totalDrivers.size()!=0) {
            for (int driverId : totalDrivers) {
                if (driverMap.get(driverId) < customerMap.get(customer.getCustomerId()))
                    totalDrivers.remove(driverId);
            }
        } else
            return customerDriverList.get(customer.getCustomerId());

        return totalDrivers;
    }

    public List<Driver> getTopDrivers(int n) throws CabShareException {

        if (n<0)
            throw new CabShareException();

        if (n>driverSet.size())
            return (List<Driver>) driverSet;

        List<Driver> drivers = new ArrayList<Driver>();
        int i=0;
        Iterator iter = driverSet.iterator();
        while (iter.hasNext() && i<n) {
            drivers.add((Driver) iter.next());
            i++;
        }

        return drivers;
    }

    public List<Customer> getTopCustomers(int n) throws CabShareException {
        if (n<0)
            throw new CabShareException();
        if (n>customerSet.size())
            return (List<Customer>) customerSet;

        List<Customer> customers = new ArrayList<Customer>();
        int i=0;
        Iterator iter = customerSet.iterator();
        while (iter.hasNext() && i<n) {
            customers.add((Customer) iter.next());
            i++;
        }

        return customers;
    }


    public static void main(String[] args) throws CabShareException {
        Driver driver1 = new Driver(1, 1);
        Driver driver2 = new Driver(2, 1);
        Driver driver3 = new Driver(3, 5);
        Driver driver4 = new Driver(4, 4);
        Driver driver5 = new Driver(5, 5);

        Customer customer1 = new Customer(1,1);
        Customer customer2 = new Customer(2,1);
        Customer customer3 = new Customer(3,3);
        Customer customer4 = new Customer(4,2);
        Customer customer5 = new Customer(5,2);

        Location location = new Location("A", "B");

        CabShare cabShare = new CabShare();
        cabShare.addTrip(customer1, driver1, location, 5, 2);
        cabShare.addTrip(customer2, driver2, location, 4, 1);
        cabShare.addTrip(customer3, driver3, location, 3, 2);
        cabShare.addTrip(customer4, driver4, location, 1, 2);
        cabShare.addTrip(customer5, driver5, location, 1, 2);

        System.out.println("average rating points for the driver ID given : " + cabShare.getDriverRating(driver2));
        System.out.println("average rating points for the customer ID given : " + cabShare.getCustomerRating(customer3));
        System.out.println("TOP Customers : ");
        for (Customer customer : cabShare.getTopCustomers(3)) {
            System.out.print(customer.getCustomerId() + " ");
        }
//        System.out.println("eligible drivers for the customerID : " + cabShare.getEligibleDrivers(customer3).toString());// fix it
    }

}
