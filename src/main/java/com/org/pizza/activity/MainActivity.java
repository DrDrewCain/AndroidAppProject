package com.org.pizza.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.org.pizza.R;
import com.org.pizza.base.BaseActivity;
import com.org.pizza.databinding.ActivityMainBinding;
import com.org.pizza.model.Order;
import com.org.pizza.model.PizzaMaker;
import com.org.pizza.model.Size;
import com.org.pizza.model.Type;
import com.org.pizza.utils.Data;

/***
 * @author Mark Sturman, Alva Bandy
 */

/**
 * Create and initialize activity
 * @param savedInstanceState
 */
public class MainActivity extends BaseActivity {

    ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        initView();
    }
    /**
     * Initialize and binds the xml views to listeners
     */
    private void initView(){
        bind.addOrderBtn.setOnClickListener(this);
        bind.customizeBtn.setOnClickListener(this);
        bind.viewOrder.setOnClickListener(this);
        bind.manageOrder.setOnClickListener(this);
        bind.deluxe.setOnClickListener(this);
        bind.hawai.setOnClickListener(this);
        bind.pepperon.setOnClickListener(this);

        deselectPizza();
    }
    /**
     * onClick handler for all buttons
     * @param view
     */
    @Override
    public void onClick(View view) {
        if(view == bind.addOrderBtn){
            onAddPizzaToCurrentOrder();
        } else if(view == bind.viewOrder){
            if(Data.currentOrder == null || Data.currentOrder.getOrder().isEmpty()) {
                showMessage(getString(R.string.message_7));
                return;
            }
            startIntentLiveMode(ActivityViewOrder.class);
        } else if(view == bind.customizeBtn){
            startIntentLiveMode(ActivityCustomPizza.class);
        } else if(view == bind.deluxe){
            selectPizza(0);
        } else if(view == bind.hawai){
            selectPizza(1);
        } else if(view == bind.pepperon){
            selectPizza(2);
        } else if(view == bind.manageOrder){
            startIntentLiveMode(ActivityStoreOrder.class);
        }
    }
    /**
     *
     * @param type
     */
    private void selectPizza(int type) {
        deselectPizza();
        PizzaMaker maker = new PizzaMaker();

        if(type == 0){
            Data.currentPizza = maker.makePizza(Size.Small, Type.DELUXE);
            bind.deluxe.setBackgroundResource(R.drawable.round_gray_background);
        } else if(type == 1){
            Data.currentPizza = maker.makePizza(Size.Small, Type.HAWAIIAN);
            bind.hawai.setBackgroundResource(R.drawable.round_gray_background);
        } else if(type == 2){
            Data.currentPizza = maker.makePizza(Size.Small, Type.PEPPERONI);
            bind.pepperon.setBackgroundResource(R.drawable.round_gray_background);
        }
    }

    private void deselectPizza(){
        bind.deluxe.setBackgroundResource(R.drawable.round_white_background);
        bind.hawai.setBackgroundResource(R.drawable.round_white_background);
        bind.pepperon.setBackgroundResource(R.drawable.round_white_background);
    }

    private void onAddPizzaToCurrentOrder() {
        // Create order if not already exist
        if(Data.currentOrder == null) {
            Data.currentOrder = new Order();
        }
        if(Data.currentPizza != null){
            Data.currentOrder.addPizza(Data.currentPizza);
            showMessage(getString(R.string.message_pizza_added));
        }else{
            showMessage(getString(R.string.message_pizza_select));
        }
    }
}