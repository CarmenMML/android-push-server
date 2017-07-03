package com.example.lucerito.asvproject.retrofit;


public class RetrofitListener {

    public interface ResponseListener<T>{
        public void onResponse(T response);
    }

    public interface ErrorListener {
        public void onErrorResponse(Throwable error);
    }
}
