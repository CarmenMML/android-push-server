package com.example.lucerito.asvproject.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucerito.asvproject.R;
import com.example.lucerito.asvproject.model.MessageModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> {

    private final List<MessageModel> mMessages;
   /// private final OnListFragmentInteractionListener mListener;
   List<String> itemsPendingRemoval;
    int lastInsertedIndex; // so we can add some more items for testing purposes
    boolean undoOn; // is undo on, you can turn it on from the toolbar menu

    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<String, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be

    public MessagesRecyclerViewAdapter(List<MessageModel> items){///, OnListFragmentInteractionListener listener) {
        mMessages = items;
        itemsPendingRemoval = new ArrayList<>();
        // let's generate some items
        lastInsertedIndex = 15;
        // this should give us a couple of screens worth
      /*  for (int i=1; i<= lastInsertedIndex; i++) {
            items.add("Item " + i);
        }*/
       /// mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

       // holder.mItem = mMessages.get(position);
        String message = mMessages.get(position).getText();
       // holder.mPrice.setText(price);

      /*  String ride = mMessages.get(position).getOutbound().getOrigin() + " - " + mRides.get(position).getOutbound().getDestination();
        ride += " / " + mRides.get(position).getOutbound().getDestination() + " - " + mRides.get(position).getOutbound().getOrigin();
       */
      holder.mMessage.setText(message);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              holder.getItemId();

              /* if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }*/
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

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMessage = (TextView) view.findViewById(R.id.activity_main_list_item_message);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "prueba" + "'";
        }

    }

    public void addItem(String country) {
    //    countries.add(country);
      //  notifyItemInserted(countries.size());
    }

    public void removeItem(int position) {
      //  countries.remove(position);
      //  notifyItemRemoved(position);
     //   notifyItemRangeChanged(position, countries.size());
    }
}
