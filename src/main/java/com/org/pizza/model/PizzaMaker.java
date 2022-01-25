package com.org.pizza.model;

/**
 * Concrete class to represent the creation of customizable pizza with all the different toppings and size.
 * @author Mark Sturman, Alva Bandy
 */
public class PizzaMaker {

    // Collection of Toppings
    private final Topping toppings [] = { new Topping("Anchovies"), new Topping("Artichokes"),
            new Topping("Bacon"), new Topping("Beef"), new Topping("Broccoli"),
            new Topping("Chicken Cubes"), new Topping("Eggplant"), new Topping("Extra Cheese"),
            new Topping("Mushrooms"), new Topping("Onions"), new Topping("Pepperoni"),
            new Topping("Pineapple"), new Topping("Sausage")};

    /**
     * Function to get all the available toppings.
     *
     * @return array of toppings
     */
    public Topping[] getToppings() {
        return this.toppings;
    }

    /**
     * Make a Pizza using this factory method.
     *
     * @param size of pizza
     * @param type of pizza
     * @return new pizza object
     */
    public Pizza makePizza(Size size, Type type) {
        Pizza pizza = null;

        switch(type) {
            case DELUXE :{
                pizza = new Deluxe(Size.Small);
                pizza.addTopping(toppings[0]);
                pizza.addTopping(toppings[1]);
                pizza.addTopping(toppings[2]);
                pizza.addTopping(toppings[3]);
                pizza.addTopping(toppings[4]);
                break;
            }
            case HAWAIIAN :{
                pizza = new Hawaiian(Size.Small);
                pizza.addTopping(toppings[0]);
                pizza.addTopping(toppings[1]);
                break;
            }
            case PEPPERONI :{
                pizza = new Pepperoni(Size.Small);
                pizza.addTopping(toppings[10]);
                break;
            }
        }
        return pizza;
    }
}
