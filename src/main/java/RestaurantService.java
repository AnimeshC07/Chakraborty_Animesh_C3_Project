import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        //return null;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    	for(Restaurant restaurant: restaurants) {
            if(restaurant.getName().equals(restaurantName))
                return restaurant;
        }
    	throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    
    public Double getTotalAmount(List<Item> menu) {
    	
    	Double sum = 0.0;
    	for(Item items : menu) {
    		sum += items.getPrice();
    	}
    	return sum;
    }
}
