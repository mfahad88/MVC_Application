package com.example.mvcapplication.todos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mvcapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TodosListViewMvcImpl implements TodosAdapter.OnTodoClickListener, TodosListViewMvc {
    private View mRootView;
    private ListView listView;
    private TodosAdapter todosAdapter;
    private final List<Listener> mListeners=new ArrayList<>(1);
    public TodosListViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {

        mRootView = inflater.inflate(R.layout.activity_main,parent,false);
        listView=findViewById(R.id.listView);
        todosAdapter=new TodosAdapter(getContext(),this);
        listView.setAdapter(todosAdapter);
    }

    @Override
    public void registerListener(Listener listener){
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener){
        mListeners.remove(listener);
    }


    private Context getContext() {
        return getRootView().getContext();
    }


    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    public void bindTodos(List<Todo> todos) {

        todosAdapter.clear();
        todosAdapter.addAll(todos);
        todosAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTodoClicked(Todo todo) {
        for (Listener listener:mListeners){
            listener.onTodoClicked(todo);
        }

    }
}
