package com.org.pizza.model;

import java.util.ArrayList;

/**
 * The class that contains the functionality of the Pizza.
 * @author Mark Sturman, Alva Bandy
 */
public abstract class Pizza {

    // maximum toppings
    protected final int MAX_TOPPINGS = 7;

    // Collection of Toppings
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();

    // Pizza Size
    protected Size size;

    // Pizza name
    private String name;

    /**
     * default constructor
     */
    public Pizza(String name) {
        this.name = name;
    }

    /**
     * constructor to create Pizza with given size.
     *
     * @param size of pizza
     * @param name of pizza
     */
    public Pizza(Size size, String name) {
        this.size = size;
        this.name = name;
    }

    /**
     * Get the name of the Pizza.
     *
     * @return pizza name
     */
    public String getName() {
        return name;
    }

    /**
     * Function to calculate the Pizza Price.
     *
     * @return calculated price
     */
    public abstract double price();

    /**
     * Function to add new Topping to the system.
     *
     * @param topping to add
     */
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Get all the toppings of this Pizza.
     *
     * @return toppings
     */
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * Function to set or update the size of this Pizza.
     *
     * @param size of pizza.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Function to get hte Size of this Pizza.
     *
     * @return size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Remove a topping from this Pizza.
     *
     * @param topping to remove
     */
    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    @Override
    /**
     * Get the String representation of the Pizza.
     *
     * @return string representation
     */
    public String toString() {
        String sizeStr = "Small";

        if(getSize() == Size.Medium) {
            sizeStr = "Medium";
        } else if(getSize() == Size.Large) {
            sizeStr = "Large";
        }
        String output = String.format("\tPizza: %-12s Size: %-12s%n", name, sizeStr);
        output += String.format("\tToppings: %n");
        for(Topping t: toppings) {
            output += String.format("\t\t%s%n", t.getName());
        }

        output += String.format("\t\t\tPrice: $%.2f%n", price());

        System.out.println(output);

        return output;
    }

}
