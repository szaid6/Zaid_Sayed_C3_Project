import java.time.LocalTime;

public class main {

    public static void main(String[] args) throws restaurantNotFoundException {
        System.out.println("Welcome to the Restaurant Service");

        RestaurantService service = new RestaurantService();

        // Add a new restaurant and menue
        Restaurant restaurant = service.addRestaurant("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"),
                LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        restaurant = service.addRestaurant("Delhi Darbar", "Delhi", LocalTime.parse("09:00:00"),
                LocalTime.parse("23:00:00"));
        restaurant.addToMenu("Butter Chicken", 119);
        restaurant.addToMenu("Veg Roll", 269);

        System.out.println("\nList of restaurants: \n");

        // Display all the restaurants
        for (Restaurant rest : service.getRestaurants()) {
            rest.displayDetails();
        }

        // Remove a restaurant
        service.removeRestaurant("Delhi Darbar");

        System.out.println("\nList of restaurants after removing: \n");

        for (Restaurant rest : service.getRestaurants()) {
            rest.displayDetails();
        }

        // Search for a restaurant
        System.out.println("\nSearching for a restaurant: \n");

        Restaurant rest = service.findRestaurantByName("Amelie's cafe");
        rest.displayDetails();
    }
}