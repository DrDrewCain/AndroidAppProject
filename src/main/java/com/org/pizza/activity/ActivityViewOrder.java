package com.org.pizza.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.org.pizza.R;
import com.org.pizza.adapter.RecycleAdapter;
import com.org.pizza.adapter.SpinAdapter;
import com.org.pizza.base.BaseActivity;
import com.org.pizza.databinding.ActivityCurrentOrderBinding;
import com.org.pizza.model.Pizza;
import com.org.pizza.model.SelectModel;
import com.org.pizza.model.Size;
import com.org.pizza.model.Topping;
import com.org.pizza.utils.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * The ActivityViewOrder class that takes topping list and orderlsit.
 * @author Alva Bandy
 */
public class ActivityViewOrder extends BaseActivity {
    ActivityCurrentOrderBinding bind;
    SpinAdapter topping_adapter;
    RecycleAdapter order_adapter;

    List<String> toppingList;
    List<SelectModel> orderList;
    int selectedOrder = -1;
    int negativeone = -1;
    int zero = 0;

    /***
     * Creates the OnCreate Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCurrentOrderBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        initView();
    }

    /***
     * Initiates the View
     */
    private void initView(){
        bind.placeOrderBtn.setOnClickListener(this);
        bind.removeOrder.setOnClickListener(this);

        toppingList = new ArrayList<>();
        orderList = new ArrayList<>();

        bind.pizzaList.setLayoutManager(new LinearLayoutManager(context));
        bind.pizzaList.setNestedScrollingEnabled(false);

        topping_adapter = new SpinAdapter(context,toppingList);
        order_adapter = new RecycleAdapter(context, orderList);
        order_adapter.setListener(new RecycleAdapter.Listener() {
            /***
             * Initializes the OnSelect
             * @param pos
             */
            @Override
            public void onSelect(int pos) {
                for(SelectModel model : orderList){
                    model.setSelect(false);
                }
                orderList.get(pos).setSelect(true);
                order_adapter.notifyDataSetChanged();
                displayPizza(pos);
                selectedOrder = pos;
            }
        });

        bind.toppingList.setAdapter(topping_adapter);
        bind.pizzaList.setAdapter(order_adapter);

        displayOrder();

        if(!Data.currentOrder.getOrder().isEmpty()){
            orderList.get(0).setSelect(true);
            selectedOrder = 0;
            order_adapter.notifyItemChanged(0);
            displayPizza(0);
        }
    }

    /***
     * Initializes the Onclick
     * @param view
     */
    @Override
    public void onClick(View view) {
        if(view == bind.placeOrderBtn){
            onPlaceOrder();
        } else if(view == bind.removeOrder){
            onRemoveOrder();
        }
    }

    /***
     * Creates the DisplayOrder of the pizza cost, name, and size. Then changes the format.
     */
    private void displayOrder() {
        if (Data.currentOrder != null) {
            // Clear list of pizzas
            orderList.clear();
            // Load new pizza with name, size and price
            for (Pizza p : Data.currentOrder.getOrder()) {
                String line = String.format("Type: %s, Size: %s, Price: $%.2f", p.getName(), p.getSize(), p.price());
                orderList.add(new SelectModel(line));
            }
            order_adapter.notifyDataSetChanged();
            bind.subTotal.setText(String.format("$%.2f", Data.currentOrder.getSubTotal()));
            bind.tax.setText(String.format("$%.2f", Data.currentOrder.getTax()));
            bind.totalPrice.setText(String.format("$%.2f", Data.currentOrder.getTotalPrice()));
            bind.phone.setText(String.format("%s", Data.currentOrder.getPhoneNumber()));

        }else{
            orderList.clear();
            toppingList.clear();
            order_adapter.notifyDataSetChanged();
            topping_adapter.notifyDataSetChanged();

            bind.subTotal.setText("");
            bind.tax.setText("");
            bind.totalPrice.setText("");
            bind.phone.setText("");
        }
    }

    /***
     * Displays the Pizza by their sizes
     * @param index
     */
    private void displayPizza(int index) {
        if (index >= 0) {
            Pizza pizza = Data.currentOrder.getOrder().get(index);
            bind.type.setText(pizza.getName());
            String size = "Small";
            if (pizza.getSize() == Size.Medium) {
                size = "Medium";
            } else if (pizza.getSize() == Size.Large) {
                size = "Large";
            }
            bind.size.setText(size);
            bind.price.setText(String.format("$%.2f", pizza.price()));
            toppingList.clear();
            for (Topping t : pizza.getToppings()) {
                toppingList.add(t.getName());
            }
            topping_adapter.notifyDataSetChanged();

        } else {
            bind.type.setText("");
            bind.size.setText("");
            bind.price.setText("");
            toppingList.clear();
            topping_adapter.notifyDataSetChanged();
            showMessage(getString(R.string.message_3));
        }
    }

    /***
     * Creates the onRemoveOrder
     */
    private void onRemoveOrder() {
        if(selectedOrder == negativeone){
            return;
        }
        Data.currentOrder.removePizza(selectedOrder);
        displayOrder();

        if(Data.currentOrder.getOrder().isEmpty()){
            selectedOrder = negativeone;
            displayPizza(selectedOrder);
        }else{
            selectedOrder = zero;
            orderList.get(selectedOrder).setSelect(true);
            order_adapter.notifyItemChanged(selectedOrder);
            displayPizza(selectedOrder);
        }
    }

    /**
     * Place the order
     */
    private void onPlaceOrder() {
        if(Data.currentOrder == null) return;
        // Get phone number
        String phone = bind.phone.getText().toString().trim();
        if(phone.isEmpty()) {
            showMessage(getString(R.string.message_5));
            return;
        }
        Data.currentOrder.setPhoneNumber(phone);
        Data.orders.addOrder(Data.currentOrder);
        //create new Order, reset frame
        Data.currentOrder = null;
        showMessage(getString(R.string.message_6));
        finish();
    }
}
