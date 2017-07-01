package com.example.lucerito.asvproject.retrofit;

/**
 * Created by cmorenol on 10/05/2016.
 */
public class RetrofitListener {

    public interface ResponseListener<T>{
        public void onResponse(T response);
    }

    public interface ErrorListener {
        public void onErrorResponse(Throwable error);
    }
}
