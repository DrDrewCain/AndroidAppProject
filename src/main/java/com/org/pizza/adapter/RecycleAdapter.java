package com.org.pizza.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.pizza.R;
import com.org.pizza.model.SelectModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SelectModel> localDataSet;
    private Listener mListener;

    public interface Listener {
        void onSelect(int pos);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private LinearLayout data_area;
        private TextView data_view;

        public viewHolder(View view) {
            super(view);
            data_area = view.findViewById(R.id.data_area);
            data_view = view.findViewById(R.id.text);
        }
    }

    public RecycleAdapter(Context context, List<SelectModel> dataSet) {
        this.context = context;
        localDataSet = dataSet;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        return new RecycleAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        SelectModel model = localDataSet.get(position);
        RecycleAdapter.viewHolder holder = (RecycleAdapter.viewHolder) viewHolder;

        holder.data_view.setText(model.getValue());

        if(model.isSelect()){
            holder.data_area.setBackgroundResource(R.drawable.round_gray_background);
        } else {
            holder.data_area.setBackgroundResource(R.drawable.round_white_background);
        }

        holder.data_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onSelect(position);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}


