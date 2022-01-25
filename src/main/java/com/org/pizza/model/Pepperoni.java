package com.org.pizza.model;

/**
 * Concrete class to represent a Deluxe Pizza.
 * @author Mark Sturman, Alva Bandy
 */
public class Pepperoni extends Pizza {

    // Base price and Toppings
    private final int TOPPINGS_INCLUDED = 1;
    private double price = 8.99;
    private double toppingsPrice = 0;
    private double sizePrice = 0.0;
    private final double AddCostForLarge = 4.0;
    private final double AddCostForMedium = 2.0;


    /**
     *
     * Create Default Pepperoni Pizza.
     *
     */
    public Pepperoni() {
        super("Pepperoni");
    }

    /**
     * Constructor to create Pepperoni Pizza with a given size.
     *
     * @param size
     */
    public Pepperoni(Size size) {
        super(size, "Pepperoni");
    }

    /**
     * Function to calculate the Pizza Price.
     *
     * @return calculated price
     */
    public double price() {



        // Base price

        // add price of additional toppings
        if(toppings.size() > TOPPINGS_INCLUDED) {
            for(int i=TOPPINGS_INCLUDED; i<toppings.size(); i++) {
                toppingsPrice += toppings.get(i).getPrice();
            }
        }

        // add price for size
        if(size == Size.Medium) {
            sizePrice = AddCostForMedium;
        } else if(size == Size.Large) {
            sizePrice = AddCostForLarge;
        }

        price += toppingsPrice + sizePrice;

        return price;
    }
}
