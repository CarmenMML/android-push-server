package com.example.lucerito.asvproject;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucerito.asvproject.adapter.MessagesRecyclerViewAdapter;
import com.example.lucerito.asvproject.model.ApplicationCountModel;
import com.example.lucerito.asvproject.model.MessageModel;
import com.example.lucerito.asvproject.retrofit.RetrofitListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import me.leolin.shortcutbadger.ShortcutBadger;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private EditText etMessage;
    private Button btSendMessage;
    private RecyclerView mRecyclerView;
    private Paint p = new Paint();

    private ApplicationCountModel applicationCount;
    private ArrayList<MessageModel> listMessages;
    private MessagesRecyclerViewAdapter mAdapter;

    String SENDER_ID = "925079268327";
    String LOOPBACK_APP_ID = "loopback-push-server";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicationCount = new ApplicationCountModel();

        etMessage = (EditText) findViewById(R.id.activity_main_message_et);
        btSendMessage = (Button) findViewById(R.id.activity_main_send_message_bt);
        btSendMessage.setOnClickListener(onClickListenerSendBt);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_messages);
        initSwipe();

        checkPlayServices();
        getMessagesList();
        clearBadge();



/* Probando servidor
       ASVApplication.getASVApi().getApplicationCount(new RetrofitListener.ResponseListener<Response<ApplicationCountModel>>() {
            @Override
            public void onResponse(Response<ApplicationCountModel> response) {
                if(response.body() != null) {
                    applicationCount = response.body();
                }
            }
        }, new RetrofitListener.ErrorListener(){
            @Override
            public void onErrorResponse(Throwable error) {
                //resultadoTextView.setText(error.getMessage());
            }
        });
*/

    }

    private void clearBadge() {
        ShortcutBadger.applyCount(getApplicationContext(), 0);
        ASVApplication.saveIconBadge(getApplicationContext(), 0);
    }

    private void getMessagesList() {
        String token = ASVApplication.getFCMToken();
        //Toast.makeText(getApplicationContext(), "Messages by device: " + token, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Messages by device: " + token);

        ASVApplication.getASVApi().getMessagesByDevice(token,
                new RetrofitListener.ResponseListener<Response<ArrayList<MessageModel>>>() {
            @Override
            public void onResponse(Response<ArrayList<MessageModel>> response) {
                if(response.body() != null) {

                    listMessages = response.body();
                    Collections.reverse(listMessages);
                    mAdapter = new MessagesRecyclerViewAdapter(listMessages);
                    mRecyclerView.setAdapter(mAdapter);//, mListener));
                }
            }
        }, new RetrofitListener.ErrorListener() {
            @Override
            public void onErrorResponse(Throwable error) {
            }
        });
    }

    // Save new message
    private View.OnClickListener onClickListenerSendBt = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String message = etMessage.getText().toString();
            if(!message.equals("")) {
                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());
                MessageModel messageModel = new MessageModel("titulo", message, date);

                ASVApplication.getASVApi().createMessage(messageModel, new RetrofitListener.ResponseListener<Response<MessageModel>>() {
                    @Override
                    public void onResponse(Response<MessageModel> response) {
                        if(response.body() != null) {
                           messageCreated();
                        }
                    }
                }, new RetrofitListener.ErrorListener() {
                    @Override
                    public void onErrorResponse(Throwable error) {

                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "No se puede insertar un mensaje vacio", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void messageCreated() {
        Toast.makeText(getApplicationContext(), "Mensaje guardado", Toast.LENGTH_SHORT).show();
        etMessage.setText("");
        getMessagesList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check device for Play Services APK.
        checkPlayServices();
    }

    /**
     * Checks the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability googleApi = GoogleApiAvailability.getInstance();
        final int resultCode = googleApi.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApi.isUserResolvableError(resultCode)) {
                googleApi.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    // Send an upstream message.
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    mAdapter.removeItem(position);
                }
            }

/*
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }*/
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
