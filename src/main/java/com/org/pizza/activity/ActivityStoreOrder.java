package com.org.pizza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.org.pizza.R;
import com.org.pizza.adapter.RecycleAdapter;
import com.org.pizza.adapter.SpinAdapter;
import com.org.pizza.base.BaseActivity;
import com.org.pizza.databinding.ActivityStoreOrderBinding;
import com.org.pizza.model.Order;
import com.org.pizza.model.SelectModel;
import com.org.pizza.model.StoreOrders;
import com.org.pizza.utils.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * @author Mark Sturman, Alva Bandy
 */
public class ActivityStoreOrder extends BaseActivity {
    ActivityStoreOrderBinding bind;
    RecycleAdapter order_adapter;
    List<SelectModel> orderList;

        /***
     * Creates the button for Delete Order and export Order.
     * @param view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityStoreOrderBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        initView();
    }
    
    /***
     * Creates the order list and adapter calls the recycle adapter.
     */

    private void initView(){
        bind.deleteOrder.setOnClickListener(this);
        bind.exportOrder.setOnClickListener(this);

        orderList = new ArrayList<>();

        bind.orderList.setLayoutManager(new LinearLayoutManager(context));
        bind.orderList.setNestedScrollingEnabled(false);

        order_adapter = new RecycleAdapter(context, orderList);
        order_adapter.setListener(new RecycleAdapter.Listener() {
            /***
             * Creates the on select for the order list.
             */

            @Override
            public void onSelect(int pos) {
                for(SelectModel model : orderList){
                    model.setSelect(false);
                }
                orderList.get(pos).setSelect(true);
                order_adapter.notifyDataSetChanged();
            }
        });

        bind.orderList.setAdapter(order_adapter);

        displayOrders();
    }
   /***
     * Creates the button for Delete Order and export Order.
     * @param view
     */
    @Override
    public void onClick(View view) {
        if(view == bind.exportOrder){
            onExport();
        } else if(view == bind.deleteOrder){
            onDeleteOrder();
        }
    }

    /***
     * Displays the orders and format it correctly.
     */
    private void displayOrders() {
        StoreOrders orders = Data.orders;
        orderList.clear();
        double total = 0;

        for(Order order: orders.getOrders()) {
            String line = String.format("Customer: %s, SubTotal: $%.2f, Tax: $%.2f, Total Price: $%.2f",
                    order.getPhoneNumber(), order.getSubTotal(), order.getTax(),
                    order.getTotalPrice());
            orderList.add(new SelectModel(line));
            total += order.getTotalPrice();
        }
        order_adapter.notifyDataSetChanged();
        bind.totalPrice.setText(String.format("$%.2f", total));
    }

    /***
     * Displays the orders and format it correctly.
     */
    private void onDeleteOrder() {
        int index = -1;
        for(int i = 0; i < orderList.size(); i++){
            if(orderList.get(i).isSelect()){
                index = i;
                break;
            }
        }
        if(index >= 0) {
            Data.orders.removeOrder(index);
            displayOrders();

        } else {
            showMessage(getString(R.string.message_8));
        }
    }

    /**
     * Export to the output file.
     */
    private void onExport() {
        //File name;
        String result = Data.orders.exportOrders();
        Intent intent = new Intent(context, ActivityExportResult.class);
        intent.putExtra("export", result);
        startActivity(intent);
    }
}
