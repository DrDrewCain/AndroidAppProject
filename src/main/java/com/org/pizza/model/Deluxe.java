package com.org.pizza.model;

/**
 * Concrete class to represent a Deluxe Pizza.
 *
 * @author Mark Sturman, Alva Bandy
 */
public class Deluxe extends Pizza {

    // Base price and Toppings
    private final int TOPPINGS_INCLUDED = 5;
    private double price = 8.99;
    private double toppingsPrice = 0;
    private double sizePrice = 0.0;
    private final double AddCostForLarge = 4.0;
    private final double AddCostForMedium = 2.0;
    /**
     * Create Default Deluxe Pizza.
     */
    public Deluxe() {
        super("Deluxe");
    }

    /**
     * Constructor to create Deluxe Pizza with a given size.
     *
     * @param size
     */
    public Deluxe(Size size) {
        super(size, "Deluxe");
    }

    /**
     * Function to calculate the Pizza Price.
     *
     * @return calculated price
     */
    public double price() {

        double price = 0;
        double toppingsPrice = 0;
        double sizePrice = 0;

        // Base price
        price = 12.99;      // 5 toppings & Small size

        // add price of additional toppings
        if (toppings.size() > TOPPINGS_INCLUDED) {
            for (int i = TOPPINGS_INCLUDED; i < toppings.size(); i++) {
                toppingsPrice += toppings.get(i).getPrice();
            }
        }

        // add price for size
        if (size == Size.Medium) {
            sizePrice = AddCostForMedium;
        } else if (size == Size.Large) {
            sizePrice = AddCostForLarge;
        }

        price += toppingsPrice + sizePrice;

        return price;
    }
}
