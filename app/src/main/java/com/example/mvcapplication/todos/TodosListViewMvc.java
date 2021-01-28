package com.example.mvcapplication.todos;

import android.view.View;

public interface TodosListViewMvc {
    public interface Listener{
        void onTodoClicked(Todo todo);
    }

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    View getRootView();

}
