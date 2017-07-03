package com.example.lucerito.asvproject;

import com.example.lucerito.asvproject.model.ApplicationCountModel;
import com.example.lucerito.asvproject.model.InstallationModel;
import com.example.lucerito.asvproject.model.MessageModel;
import com.example.lucerito.asvproject.retrofit.RetrofitInterface;
import com.example.lucerito.asvproject.retrofit.RetrofitListener;
import com.example.lucerito.asvproject.retrofit.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cmorenol on 10/05/2016.
 */
public class ASVApi {

    /* Service URLs and endpoints
    public static final String URL_RIDES = "http://odigeo-testandroid.herokuapp.com";
    public static final String URL_CURRENCY = "http://jarvisstark.herokuapp.com";
    public static final String URL_GoT = "http://anapioficeandfire.com/";*/

    //http://localhost:3000/api/applications/count
    //public static final String URL = "http://10.0.2.2:3000";
    public static final String URL = "http://192.168.1.131:3000";

/*
    public static final String RIDES = "/";
    public static final String CURRENCY = "/currency"; // ?from=xxx&to=yyy
    public static final String CHARACTERS = "/api/characters";*/

    public static final String APPLICATION_COUNT = "/api/applications/count";
    public static final String INSTALLATION = "/api/installations";
    public static final String MESSAGE_CREATE = "/api/messages";
    public static final String MESSAGE_GET_BY_DEVICE = "/api/messages/byDevice?";

/*
    private RetrofitInterface service_rides;
    private RetrofitInterface service_currency;
    private RetrofitInterface service_GoT_characters;*/

    private RetrofitInterface service;


    public ASVApi() {
        /*
        service_rides = ServiceGenerator.createService(RetrofitInterface.class, URL_RIDES);
        service_currency = ServiceGenerator.createService(RetrofitInterface.class, URL_CURRENCY);
        service_GoT_characters = ServiceGenerator.createService(RetrofitInterface.class, URL_GoT);
        */
        service = ServiceGenerator.createService(RetrofitInterface.class, URL);
    }


    public void getApplicationCount(final RetrofitListener.ResponseListener responseListener, final RetrofitListener.ErrorListener errorListener) {
        Call<ApplicationCountModel> call = service.getApplicationCount();
        call.enqueue(new Callback<ApplicationCountModel>() {
            @Override
            public void onResponse(Call<ApplicationCountModel> call, Response<ApplicationCountModel> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<ApplicationCountModel> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }

    public void saveInstallation(InstallationModel installation, final RetrofitListener.ResponseListener responseListener,
                                 final RetrofitListener.ErrorListener errorListener) {
        Call<InstallationModel> call = service.saveInstallation(
                installation.getAppId(),
                installation.getAppVersion(),
                installation.getBadge(),
                installation.getCreated(),
                installation.getDeviceToken(),
                installation.getDeviceType(),
                installation.getModified(),
                installation.getStatus(),
                installation.getSubscriptions(),
                installation.getTimeZone(),
                installation.getUserId());
        call.enqueue(new Callback<InstallationModel>() {
            @Override
            public void onResponse(Call<InstallationModel> call, Response<InstallationModel> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<InstallationModel> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }

    public void createMessage(MessageModel message, final RetrofitListener.ResponseListener responseListener,
                              final RetrofitListener.ErrorListener errorListener) {
        Call<MessageModel> call = service.createMessage(
                message.getTitle(),
                message.getText(),
                message.getSent_date());
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }


    public void getMessagesByDevice(String deviceToken, final RetrofitListener.ResponseListener responseListener, final RetrofitListener.ErrorListener errorListener) {
        Call<ArrayList<MessageModel>> call = service.getMessagesByDevice(deviceToken);
        call.enqueue(new Callback<ArrayList<MessageModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MessageModel>> call, Response<ArrayList<MessageModel>> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<ArrayList<MessageModel>> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }

    /*
    public void saveInstallation(final RetrofitListener.ResponseListener responseListener, final RetrofitListener.ErrorListener errorListener) {
        Call<RouteListModel> call = service_rides.getRides();
        call.enqueue(new Callback<RouteListModel>() {
            @Override
            public void onResponse(Call<RouteListModel> call, Response<RouteListModel> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<RouteListModel> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }
*/









    /*
    public void getRides(final RetrofitListener.ResponseListener responseListener, final RetrofitListener.ErrorListener errorListener) {
        Call<RouteListModel> call = service_rides.getRides();
        call.enqueue(new Callback<RouteListModel>() {
            @Override
            public void onResponse(Call<RouteListModel> call, Response<RouteListModel> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<RouteListModel> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }

    public void getCurrencyRate(String from, String to,
                                final RetrofitListener.ResponseListener responseListener,
                                final RetrofitListener.ErrorListener errorListener) {
        Call<CurrencyExchangeModel> call = service_currency.getCurrencyRate(from, to);
        call.enqueue(new Callback<CurrencyExchangeModel>() {
            @Override
            public void onResponse(Call<CurrencyExchangeModel> call, Response<CurrencyExchangeModel> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<CurrencyExchangeModel> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }

    public void getRandomWarrior(String warrior, final RetrofitListener.ResponseListener responseListener,
                                final RetrofitListener.ErrorListener errorListener) {
        Call<GoTWarriorModel> call = service_GoT_characters.getRandomWarrior(warrior);
        call.enqueue(new Callback<GoTWarriorModel>() {
            @Override
            public void onResponse(Call<GoTWarriorModel> call, Response<GoTWarriorModel> response) {
                responseListener.onResponse(response);
            }

            @Override
            public void onFailure(Call<GoTWarriorModel> call, Throwable t) {
                errorListener.onErrorResponse(t);
            }
        });
    }
    */
}
