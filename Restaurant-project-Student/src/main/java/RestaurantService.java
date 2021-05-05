import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        if (StringUtils.isBlank(restaurantName) || restaurants == null || restaurants.isEmpty()) {
            return null;
        }
        Restaurant searchingRestaurant = restaurants.stream()
                .filter(restaurant -> StringUtils.equals(restaurant.getName(), restaurantName))
                .findFirst().orElse(null);
        if (searchingRestaurant == null) {
            throw new restaurantNotFoundException(restaurantName);
        }
        return searchingRestaurant;
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
}
