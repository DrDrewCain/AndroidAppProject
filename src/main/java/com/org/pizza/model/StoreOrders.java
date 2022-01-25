package com.org.pizza.model;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * class to maintain Store Orders.
 *
 * @author Mark Sturman, Alva Bandy
 */
public class StoreOrders {

    /**
     * List of Orders
     **/
    private ArrayList<Order> orders;

    /**
     * Any message related to export of orders to file.
     */
    private String exportMessage;

    /**
     * Create instance of Store Orders.
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Function to add new order to the Store Orders list.
     *
     * @param order to be added
     */
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    /**
     * Function to remove an order based on its index in the list
     * of store orders.
     *
     * @param index of order going to be removed
     */
    public void removeOrder(int index) {
        this.orders.remove(index);
    }

    /**
     * Get the collection of Orders.
     *
     * @return store orders.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Function to export all the orders to the output file.
     *
     * @param fileName to export orders.
     */
    public boolean exportOrders(String fileName) {
        boolean result = false;

        try {
            File file = new File(fileName);
            PrintStream stream = new PrintStream(file);

            for (Order order : orders) {
                stream.print(order.toString());
            }

            stream.close();
            result = true;
        } catch (Exception e) {
            exportMessage = e.getMessage();
        }
        return result;
    }

    public String exportOrders() {
        StringBuilder builder = new StringBuilder();
        try {
            for (Order order : orders) {
                builder.append(order.toString()).append(System.lineSeparator());
            }
            return builder.toString();
        } catch (Exception e) {

        }
        return "";
    }
}
