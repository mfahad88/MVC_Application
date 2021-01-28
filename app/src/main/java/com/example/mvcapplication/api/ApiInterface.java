package com.example.mvcapplication.api;

import com.example.mvcapplication.todos.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("todos")
    Call<List<Todo>> getTodos();
}
