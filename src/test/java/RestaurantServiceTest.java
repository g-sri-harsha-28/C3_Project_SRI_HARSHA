import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    LocalTime openingTime;
    LocalTime closingTime;
    int initialNumberOfRestaurants;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void beforeEachTest(){
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        initialNumberOfRestaurants = service.getRestaurants().size();
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        restaurant.addToMenu("Vegetable lasagne", 269);

        Restaurant restaurantSearched = service.findRestaurantByName("Amelie's cafe");
        assertNotNull(restaurantSearched);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE

        assertThrows(restaurantNotFoundException.class, ()-> service.findRestaurantByName("Bawarchi"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>Cost Calculation (Total Cost)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void total_cost_of_two_menu_items_Sweet_corn_soup_priced_119_and_Vegetable_lasagne_priced_269_should_return_388(){

        Integer finalPrice = service.getTotalCost(restaurant, "Sweet corn soup", "Vegetable lasagne");
        assertEquals(388, finalPrice);
    }
    //<<<<<<<<<<<<<<<<<<<<Cost Calculation (Total Cost)>>>>>>>>>>>>>>>>>>>>>>>>>>


}