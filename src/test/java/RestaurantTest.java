import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    // REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void addDefaultRestuarants() {
        restaurant = new Restaurant("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"),
                LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // -------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN
    // INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        // WRITE UNIT TEST CASE HERE

        Restaurant spy = Mockito.spy(restaurant);
        Mockito.when(spy.getCurrentTime()).thenReturn(LocalTime.parse("11:00:00"));
        assertTrue(spy.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        // WRITE UNIT TEST CASE HERE

        Restaurant spy = Mockito.spy(restaurant);
        Mockito.when(spy.getCurrentTime()).thenReturn(LocalTime.parse("23:00:00"));
        assertFalse(spy.isRestaurantOpen());

    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    // <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // >>>>>>>>>>>>>>>>>>>>>>>TOTAL ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    // TODO: Write a test case to check if the total order value is calculated correctly
    // 1) To calculate the total order value, we need to sum the price of all the items  in the menu.

    // 2) if the menu is empty, the total order value should be 0.

    // 3) if no items are selected, the total order value should be 0.

    // Implementing the test cases 1
    @Test
    public void when_items_are_selected_total_order_value_should_be_returned() {

        List<Item> selectedItems = new ArrayList<>();
        selectedItems.add(new Item("Sweet corn soup", 119));
        selectedItems.add(new Item("Vegetable lasagne", 269));
        int totalOrderValue = restaurant.totalOrderValue(selectedItems);
        assertEquals(388, totalOrderValue);
    }

    // Implementing the test cases 2
    @Test
    public void when_no_items_are_selected_total_order_value_should_be_0() {

        List<Item> selectedItems = new ArrayList<>();
        int totalOrderValue = restaurant.totalOrderValue(selectedItems);
        assertEquals(0, totalOrderValue);
    }

    // Implementing the test cases 3
    @Test
    public void when_menu_is_empty_total_order_value_should_be_0() {

        restaurant.getMenu().clear();
        int totalOrderValue = restaurant.totalOrderValue(restaurant.getMenu());
        assertEquals(0, totalOrderValue);
    }

    // Failing test case
    // the above test case is failing because the totalOrderValue method is not implemented in the Restaurant class yet.

    // <<<<<<<<<<<<<<<<<<<<<<<<<TOTAL ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}