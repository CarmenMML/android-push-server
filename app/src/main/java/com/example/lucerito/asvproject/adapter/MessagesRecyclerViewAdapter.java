package com.example.lucerito.asvproject.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucerito.asvproject.R;
import com.example.lucerito.asvproject.model.MessageModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> {

    private final List<MessageModel> mMessages;
   /// private final OnListFragmentInteractionListener mListener;
   List<String> itemsPendingRemoval;
    int lastInsertedIndex;


    public MessagesRecyclerViewAdapter(List<MessageModel> items){
        mMessages = items;
        itemsPendingRemoval = new ArrayList<>();
        lastInsertedIndex = 15;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String message = mMessages.get(position).getText();
        String date = mMessages.get(position).getSent_date();

        holder.mMessage.setText(message);
        holder.mDate.setText(date);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              holder.getItemId();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mMessage;
        public final TextView mDate;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMessage = (TextView) view.findViewById(R.id.activity_main_list_item_message);
            mDate = (TextView) view.findViewById(R.id.activity_main_list_item_date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "prueba" + "'";
        }

    }

    public void removeItem(int position) {
        mMessages.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mMessages.size());
    }
}
