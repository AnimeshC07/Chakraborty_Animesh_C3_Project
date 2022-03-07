import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

	RestaurantService service = new RestaurantService();
	Restaurant restaurant;
	
	@BeforeEach
	public void before_every_test() {
		
		LocalTime openingTime = LocalTime.parse("10:30:00");
		LocalTime closingTime = LocalTime.parse("22:00:00");
		restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
		restaurant.addToMenu("Sweet corn soup", 119);
		restaurant.addToMenu("Vegetable lasagne", 269);
		
	}
	// REFACTOR ALL THE REPEATED LINES OF CODE

	// >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
			throws restaurantNotFoundException {
		// WRITE UNIT TEST CASE HERE

		// int initialNumberOfRestaurants = service.getRestaurants().size();
		service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
		Restaurant toSearch = service.findRestaurantByName("Amelie's cafe");
		assertEquals(restaurant.getName(), toSearch.getName());
		assertEquals(restaurant.getClosingTime(), toSearch.getClosingTime());
		assertEquals(restaurant.getLocation(), toSearch.getLocation());
		assertEquals(restaurant.getOpeningTime(), toSearch.getOpeningTime());
	}

	// You may watch the video by Muthukumaran on how to write exceptions in Course
	// 3: Testing and Version control: Optional content
	@Test
	public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
		// WRITE UNIT TEST CASE HERE

		assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("Pantry d'or"));

	}
	// <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING
	// RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.removeRestaurant("Amelie's cafe");
		assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
	}

	@Test
	public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

		assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
	}

	@Test
	public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {

		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
		assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
	}
	
	@Test
	public void get_total_amount_should_return_right_value_for_menu_passed() {

		restaurant.addToMenu("Coffee", 50);
		restaurant.addToMenu("Tea", 50);
		restaurant.addToMenu("Dosa", 100);
		restaurant.addToMenu("Biryani", 300);
		restaurant.addToMenu("Curry", 150);
		
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("Biryani", 300));
		items.add(new Item("Coffee", 50));
		items.add(new Item("Biryani", 300));
		
		Double sum = service.getTotalAmount(items);
		assertEquals(650.0, sum);
		
		

	}
	// <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING
	// RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}