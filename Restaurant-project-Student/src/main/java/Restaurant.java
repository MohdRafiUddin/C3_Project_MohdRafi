import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen(LocalTime currentTime) {
        return openingTime.compareTo(getCurrentTime(currentTime)) <= 0 && closingTime.compareTo(getCurrentTime(currentTime)) > 0;
    }

    public LocalTime getCurrentTime(LocalTime currentTime) {
        if (currentTime == null) {
            return LocalTime.now();
        }
        return currentTime;
    }

    public List<Item> getMenu() { return this.menu; }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName)) { return item; }
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null) { throw new itemNotFoundException(itemName); }
        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    public int findTotalMenuItemsCost() {
        int menuCost = 0;
        if (getMenu() == null || getMenu().isEmpty()) {
            return menuCost;
        }
        return getMenu().stream().mapToInt(Item::getPrice).sum();
    }
}
