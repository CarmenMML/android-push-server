package com.example.lucerito.asvproject.retrofit;

import com.example.lucerito.asvproject.ASVApi;
import com.example.lucerito.asvproject.model.ApplicationCountModel;
import com.example.lucerito.asvproject.model.InstallationModel;
import com.example.lucerito.asvproject.model.MessageModel;
import com.google.android.gms.wearable.MessageApi;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by cmorenol on 10/05/2016.
 */
public interface RetrofitInterface {

    /*
    @GET(ASVApi.RIDES)
    Call<RouteListModel> getRides();

    @GET(ASVApi.CURRENCY)
    Call<CurrencyExchangeModel> getCurrencyRate(
            @Query("from") String from,
            @Query("to") String to);

    @GET(ASVApi.CHARACTERS + "/{warrior}")
    Call<GoTWarriorModel> getRandomWarrior(@Path("warrior") String warrior);
    */

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
            @Field("timeZone") String timeZone,
            @Field("userId") String userId);


    @FormUrlEncoded
    @POST(ASVApi.MESSAGE_CREATE)
    Call<MessageModel> createMessage(
            @Field("title") String title,
            @Field("text") String text,
            @Field("sent_date") String sent_date);


    @GET(ASVApi.MESSAGE_GET_BY_DEVICE)
    Call<ArrayList<MessageModel>> getMessagesByDevice(
            @Query("deviceToken") String deviceToken);
/*
    @FormUrlEncoded
    @POST("/2.2/answers/{id}/upvote")
    Call<ResponseBody> postUpvoteOnAnswer(@Path("id") int answerId, @Field("access_token") String accessToken, @Field("key") String key, @Field("site") String site, @Field("preview") boolean preview, @Field("filter") String filter);
*/
}
