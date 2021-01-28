package com.example.mvcapplication.todos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.mvcapplication.client.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TodosListViewMvcImpl.Listener {

    private TodosListViewMvcImpl todosListViewMvc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todosListViewMvc=new TodosListViewMvcImpl(LayoutInflater.from(this),null);

        todosListViewMvc.registerListener(this);

        ApiClient.getInstance().getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if(response.isSuccessful()){
                    bindTodos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {

            }
        });
        setContentView(todosListViewMvc.getRootView());
    }

    private void bindTodos(List<Todo> body) {
        /*List<Todo> todos=new ArrayList<>(body.size());
        for(Todo todo:todos){
            todos.add(new Todo(todo.getUserId(),todo.getId(),todo.getTitle(),todo.getCompleted()));
        }*/
        todosListViewMvc.bindTodos(body);

    }

    @Override
    public void onTodoClicked(Todo todo) {
        Toast.makeText(this, ""+todo.getTitle(), Toast.LENGTH_SHORT).show();
    }


}