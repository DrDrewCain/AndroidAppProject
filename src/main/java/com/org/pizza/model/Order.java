package com.org.pizza.model;

import java.util.ArrayList;

/**
 * Order representation with a String of Unique Phone number.
 *  @author Mark Sturman, Alva Bandy
 */
public class Order {

    /* Tax Rate */
    private final double TAX_RATE = 0.06625;

    /** Phone Number of Customer */
    private String phoneNumber;

    /* Pizza List */
    ArrayList<Pizza> order;

    /**
     * Default constructor to create an empty order.
     */
    public Order() {
        this.order = new ArrayList<>();
        this.phoneNumber = "";
    }

    /**
     * Parameter constructor to create an empty Order for a given
     * Customer's Phone Number.
     *
     * @param phoneNumber of the customer
     */
    public Order(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.order = new ArrayList<>();
    }

    /**
     * Get the Customer's Phone number.
     *
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set or update the Phone Number.
     *
     * @param phoneNumber of customer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the pizzas being ordered.
     *
     * @return pizza list
     */
    public ArrayList<Pizza> getOrder() {
        return order;
    }

    /**
     * Add new pizza to this order.
     *
     * @param pizza
     */
    public void addPizza(Pizza pizza) {
        this.order.add(pizza);
    }

    /**
     * Remove a Pizza from this Order at a given index.
     *
     * @param index of the pizza to remove
     */
    public void removePizza(int index) {
        if(index >= 0 && index < order.size()) {
            this.order.remove(index);
        }
    }

    /**
     * Get the Subtotal of this Pizza.
     *
     * @return subtotal
     */
    public double getSubTotal() {
        double price = 0;

        for(Pizza p: order) {
            price += p.price();
        }

        return price;
    }

    /**
     * Get the Applicable tax on this Order.
     *
     * @return applicable tax
     */
    public double getTax() {
        double price = getSubTotal();
        return price * TAX_RATE;
    }

    /**
     * Function to compute the total price of this Pizza Order.
     * Total price is price of pizzas + tax
     *
     * @return total price of order.
     */
    public double getTotalPrice() {
        return getSubTotal() + getTax();
    }

    /**
     * Get the string representation of the Order.
     *
     * @return string representation
     */
    public String toString() {
        String output = "Customer Name + Phone Number: " + phoneNumber + "\n";
        output += "===========================================================\n";
        for(int i=0; i<order.size(); i++) {
            output += "Pizza #" + (i+1) + "\n";
            output += order.get(i).toString() + "\n";
        }

        output += String.format("Order Subtotal: $%.2f%n", getSubTotal());
        output += String.format("Order Tax:      $%.2f%n", getTax());
        output += String.format("Order Total:    $%.2f%n", getTotalPrice());
        output += "===========================================================\n";
        return output;
    }

}
