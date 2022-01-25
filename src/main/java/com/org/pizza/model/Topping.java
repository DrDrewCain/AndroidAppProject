package com.org.pizza.model;

/**
 * Class that creates the pizza topping as well as get the cost of the toppings.
 * @author Mark Sturman, Alva Bandy
 */
public class Topping {

    private String name;
    private final double price = 1.49;  // fixed price

    /**
     * Default constructor
     */
    public Topping() {
    }

    /**
     * Parameter constructor to create this Topping Object.
     *
     * @param name of topping
     */
    public Topping(String name) {
        this.name = name;
    }

    /**
     * Get the name of this topping.
     *
     * @return name of topping
     */
    public String getName() {
        return name;
    }

    /**
     * Function to set or update the name of this topping.
     *
     * @param name of topping
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function to get the price of this topping.
     *
     * @return price of topping.
     */
    public double getPrice() {
        return price;
    }
}
