/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.lucerito.asvproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.lucerito.asvproject.model.InstallationModel;
import com.example.lucerito.asvproject.retrofit.RetrofitListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


import retrofit2.Response;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    InstallationModel installationResponse = new InstallationModel();


        /**
         * Called if InstanceID token is updated. This may occur if the security of
         * the previous token had been compromised. Note that this is called when the InstanceID token
         * is initially generated so this is where you would retrieve the token.
         */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        // If you want to send messages to this application instance or manage this apps subscriptions on the server side,
        // send the Instance ID token to your app server.
       saveToken(refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    private void saveToken(String token){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token", token);
        editor.commit();
    }

    private void sendRegistrationToServer(String token) {
        InstallationModel installationModel = new InstallationModel();
        installationModel.setAppId("loopback-push-server");
        installationModel.setDeviceToken(token);
        installationModel.setDeviceType("android");

        ASVApplication.getASVApi().saveInstallation(installationModel, new RetrofitListener.ResponseListener<Response<InstallationModel>>() {
            @Override
            public void onResponse(Response<InstallationModel> response) {
                if (response.body() != null) {
                    installationResponse = response.body();

                    //////  groupRidesByDestination(response.body().getResults());
                    //////  loadCurrencyExchange();

                    ////  sortRidesByPrice();
                    ////  recyclerView.setAdapter(new MessagesRecyclerViewAdapter(listGroupedRides, mListener));
                }
            }
        }, new RetrofitListener.ErrorListener() {
            @Override
            public void onErrorResponse(Throwable error) {
                //resultadoTextView.setText(error.getMessage());
            }
        });

    }











    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    /*
    private void sendRegistrationToServer(String token) {
        final ASVApplication app = (ASVApplication) getApplication();
        final RestAdapter adapter = app.getLoopBackAdapter();
        final LocalInstallation installation = new LocalInstallation(this, adapter);

        // Substitute the real ID of the LoopBack application as created by the server
        installation.setAppId("loopback-push-server");

        // Substitute a real ID of the user logged in to the application
        //  installation.setUserId("loopback-android");
        // Set subscriptions if needed
        installation.setSubscriptions(new String[] { "all" });

        if (token != null) {
            installation.setDeviceToken(token);
            saveInstallation(installation);
        }
    }*/

    /**
     * Saves the Installation to the LoopBack server and reports the result.
     * @param installation
     */
    /*
    void saveInstallation(final LocalInstallation installation) {
        try {
            ///installation.save(new Model.Callback() {
            installation.save(new VoidCallback() {
                @Override
                public void onSuccess() {
                    final Object id = installation.getId();
                    final String msg = "Installation saved with id " + id;
                    Log.i(TAG, msg);
                    ///  mDisplay.append(msg + "\n\n");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(Throwable t) {
                    Log.e(TAG, "Cannot save Installation", t);

                    final String msg = "Cannot save device registration,"
                            + " will re-try when restarted.\n"
                            + "Reason: " + t.getMessage();
                    /// mDisplay.append(msg + "\n\n");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {}
    }
    */
}
