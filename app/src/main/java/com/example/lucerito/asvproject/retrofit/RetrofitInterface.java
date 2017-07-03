package com.example.lucerito.asvproject.retrofit;

import com.example.lucerito.asvproject.ASVApi;
import com.example.lucerito.asvproject.model.ApplicationCountModel;
import com.example.lucerito.asvproject.model.InstallationModel;
import com.example.lucerito.asvproject.model.MessageModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RetrofitInterface {

    @GET(ASVApi.APPLICATION_COUNT)
    Call<ApplicationCountModel> getApplicationCount();

    @FormUrlEncoded
    @POST(ASVApi.INSTALLATION)
    Call<InstallationModel> saveInstallation(
            @Field("appId") String appId,
            @Field("appVersion") String appVersion,
            @Field("badge") int badge,
            @Field("created") String created,
            @Field("deviceToken") String deviceToken,
            @Field("deviceType") String deviceType,
            @Field("modified") String modified,
            @Field("status") String status,
            @Field("subscriptions") ArrayList<String> subscriptions,
            @Field("timeZone") String timeZone);


    @FormUrlEncoded
    @POST(ASVApi.MESSAGE_CREATE)
    Call<MessageModel> createMessage(
            @Field("title") String title,
            @Field("text") String text,
            @Field("sent_date") String sent_date);


    @GET(ASVApi.MESSAGE_GET_MESSAGES_BY_DEVICE)
    Call<ArrayList<MessageModel>> getMessagesByDevice(
            @Query("deviceToken") String deviceToken);
}
