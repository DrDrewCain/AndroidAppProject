package com.org.pizza.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.org.pizza.R;
import com.org.pizza.adapter.RecycleAdapter;
import com.org.pizza.adapter.SpinAdapter;
import com.org.pizza.base.BaseActivity;
import com.org.pizza.databinding.ActivityCustomPizzaBinding;
import com.org.pizza.databinding.ActivityMainBinding;
import com.org.pizza.model.Deluxe;
import com.org.pizza.model.Hawaiian;
import com.org.pizza.model.Pepperoni;
import com.org.pizza.model.PizzaMaker;
import com.org.pizza.model.SelectModel;
import com.org.pizza.model.Size;
import com.org.pizza.model.Topping;
import com.org.pizza.utils.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityCustomPizza extends BaseActivity {

    ActivityCustomPizzaBinding bind;
    SpinAdapter size_adapter;
    SpinAdapter topping_adapter;
    RecycleAdapter current_topping_adapter;

    List<String> sizeList;
    List<String> toppingList;
    List<SelectModel> currentToppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCustomPizzaBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        initView();
    }

    private void initView(){
        bind.addTopping.setOnClickListener(this);
        bind.selectSize.setOnClickListener(this);

        sizeList = new ArrayList<>();
        toppingList = new ArrayList<>();
        currentToppingList = new ArrayList<>();

        bind.currentToppingList.setLayoutManager(new LinearLayoutManager(context));
        bind.currentToppingList.setNestedScrollingEnabled(false);

        size_adapter = new SpinAdapter(context,sizeList);
        topping_adapter = new SpinAdapter(context,toppingList);
        current_topping_adapter = new RecycleAdapter(context, currentToppingList);
        current_topping_adapter.setListener(new RecycleAdapter.Listener() {
            @Override
            public void onSelect(int pos) {
                for(SelectModel model : currentToppingList){
                    model.setSelect(false);
                }
                currentToppingList.get(pos).setSelect(true);
                current_topping_adapter.notifyDataSetChanged();
            }
        });

        bind.sizeList.setAdapter(size_adapter);
        bind.toppingList.setAdapter(topping_adapter);
        bind.currentToppingList.setAdapter(current_topping_adapter);

        setToppingValues();
        setSizeValues();

        displayPizza();
    }

    private void setToppingValues(){

        toppingList.add("Anchovies");
        toppingList.add("Artichokes");
        toppingList.add("Bacon");
        toppingList.add("Beef");
        toppingList.add("Broccoli");
        toppingList.add("Chicken Cubes");
        toppingList.add("Eggplant");
        toppingList.add("Extra Cheese");
        toppingList.add("Mushrooms");
        toppingList.add("Onions");
        toppingList.add("Pepperoni");
        toppingList.add("Pineapple");
        toppingList.add("Sausage");

        topping_adapter.notifyDataSetChanged();
    }
    private void setSizeValues(){
        sizeList.add("Small");
        sizeList.add("Medium");
        sizeList.add("Large");

        size_adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(view == bind.addTopping){
            addTopping();
        } else if(view == bind.selectSize){
            updateSize();
        }
    }

    private void displayPizza() {
        // clear all items
        currentToppingList.clear();
        for(Topping t: Data.currentPizza.getToppings()) {
            currentToppingList.add(new SelectModel(t.getName()));
        }
        current_topping_adapter.notifyDataSetChanged();

        // set current size
        if(Data.currentPizza.getSize() == Size.Small) {
            bind.sizeList.setSelection(0);
        } else if(Data.currentPizza.getSize() == Size.Medium) {
            bind.sizeList.setSelection(1);
        } else if(Data.currentPizza.getSize() == Size.Large) {
            bind.sizeList.setSelection(2);
        }
        // current price
        bind.price.setText(String.format(Locale.getDefault(), "$%.2f", Data.currentPizza.price()));
        if (Data.currentPizza instanceof Deluxe) {
            bind.pizzaImage.setImageResource(R.drawable.deluxe);
        } else if (Data.currentPizza instanceof Hawaiian) {
            bind.pizzaImage.setImageResource(R.drawable.hawaiian);
        } else if (Data.currentPizza instanceof Pepperoni) {
            bind.pizzaImage.setImageResource(R.drawable.pepperoni);
        }
    }

    /**
     * Update the Size of the Pizza and the price accordingly.
     */
    private void updateSize() {
        int index = bind.sizeList.getSelectedItemPosition();
        if(index >= 0) {
            if(index == 0) {
                Data.currentPizza.setSize(Size.Small);
            } else if(index == 1) {
                Data.currentPizza.setSize(Size.Medium);
            } else {
                Data.currentPizza.setSize(Size.Large);
            }
            displayPizza();
        }
    }

    /**
     * Add new topping to the Pizza.
     */
    private void addTopping() {
        int index = bind.toppingList.getSelectedItemPosition();
        PizzaMaker maker = new PizzaMaker();
        if(index >= 0) {
            // add to pizza
            // Check if Topping already added.
            if(toppingAlreadyAdded(maker.getToppings()[index].getName())) {
                showMessage(maker.getToppings()[index].getName() + " already added.");
            } else {
                // if already 7 toppings added
                if(Data.currentPizza.getToppings().size() == 7) {
                    showMessage(getString(R.string.message_1));
                } else {
                    Data.currentPizza.addTopping(maker.getToppings()[index]);
                    displayPizza();
                }
            }
        } else {
            showMessage(getString(R.string.message_2));
        }
    }

    /**
     * check if topping already added into the pizza.
     * @param topping to check
     * @return true if already added, false otherwise
     */
    private boolean toppingAlreadyAdded(String topping) {
        for(Topping t: Data.currentPizza.getToppings()) {
            if(t.getName().equalsIgnoreCase(topping)) {
                return true;
            }
        }
        return false;
    }
}
