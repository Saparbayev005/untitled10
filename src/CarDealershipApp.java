import java.util.ArrayList;
import java.util.List;

class Car {
    private String brandName;
    private int maxPassengers;
    private double cost;
    private int quantityInStock;
    private boolean availability;
    private List<PurchaseRequest> purchaseRequests;

    public Car(String brandName, int maxPassengers, double cost, int quantityInStock) {
        this.brandName = brandName;
        this.maxPassengers = maxPassengers;
        this.cost = cost;
        this.quantityInStock = quantityInStock;
        this.availability = true;
        this.purchaseRequests = new ArrayList<>();
    }

    public String getBrandName() {
        return brandName;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public boolean getAvailability() {
        return availability;
    }

    public List<PurchaseRequest> getPurchaseRequests() {
        return purchaseRequests;
    }

    public void addPurchaseRequest(PurchaseRequest request) {
        purchaseRequests.add(request);
        updateAvailability();
    }

    public void removePurchaseRequest(PurchaseRequest request) {
        purchaseRequests.remove(request);
        updateAvailability();
    }

    private void updateAvailability() {
        availability = (quantityInStock - purchaseRequests.size()) > 0;
    }
}

class PurchaseRequest {
    private String buyerName;
    private String phoneNumber;
    private Car car;

    public PurchaseRequest(String buyerName, String phoneNumber, Car car) {
        this.buyerName = buyerName;
        this.phoneNumber = phoneNumber;
        this.car = car;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Car getCar() {
        return car;
    }
}

class CarDealership {
    private String showroomName;
    private List<Car> cars;

    public CarDealership(String showroomName) {
        this.showroomName = showroomName;
        this.cars = new ArrayList<>();
    }

    public String getShowroomName() {
        return showroomName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public Car searchCarByBrand(String brandName) {
        for (Car car : cars) {
            if (car.getBrandName().equals(brandName)) {
                return car;
            }
        }
        return null;
    }
}

public class CarDealershipApp {
    public static void main(String[] args) {
        // Create CarDealership
        CarDealership dealership = new CarDealership("BestCars");

        // Create Cars
        Car car1 = new Car("Toyota", 5, 25000.0, 10);
        Car car2 = new Car("Honda", 4, 22000.0, 8);

        // Add Cars to dealership
        dealership.addCar(car1);

        // Create Purchase Requests
        PurchaseRequest request1 = new PurchaseRequest("John Doe", "123-456-7890", car1);
        PurchaseRequest request2 = new PurchaseRequest("Alice Smith", "987-654-3210", car2);
        PurchaseRequest request3 = new PurchaseRequest("Bob Johnson", "555-123-4567", car2);

        // Add Purchase Requests to Cars
        car1.addPurchaseRequest(request1);
        car2.addPurchaseRequest(request2);
        car2.addPurchaseRequest(request3);

        // Display information about Car2 and its Purchase Requests
        System.out.println("Car Information:");
        System.out.println("Brand: " + car2.getBrandName());
        System.out.println("Max Passengers: " + car2.getMaxPassengers());
        System.out.println("Cost: $" + car2.getCost());
        System.out.println("Availability: " + car2.getAvailability());

        System.out.println("\nPurchase Requests for Car2:");
        for (PurchaseRequest request : car2.getPurchaseRequests()) {
            System.out.println("Buyer: " + request.getBuyerName() + ", Phone: " + request.getPhoneNumber());
        }

        // Search for a car by brand name
        String searchBrand = "Toyota";
        Car foundCar = dealership.searchCarByBrand(searchBrand);

        if (foundCar != null) {
            System.out.println("\nFound Car:");
            System.out.println("Brand: " + foundCar.getBrandName());
            System.out.println("Max Passengers: " + foundCar.getMaxPassengers());
            System.out.println("Cost: $" + foundCar.getCost());
            System.out.println("Availability: " + foundCar.getAvailability());
        } else {
            System.out.println("\nCar with brand " + searchBrand + " not found.");
        }
    }
}
