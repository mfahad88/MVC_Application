package com.example.mvcapplication.todos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvcapplication.R;

import java.util.Collection;

public class TodosAdapter extends ArrayAdapter<Todo> {
    private final OnTodoClickListener onTodoClickListener;
    public interface OnTodoClickListener{
        void onTodoClicked(Todo todo);
    }

    public TodosAdapter(@NonNull Context context, OnTodoClickListener onTodoClickListener) {
        super(context, 0);
        this.onTodoClickListener=onTodoClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item,parent,false);
        }

        final Todo todo=getItem(position);

        TextView textView=convertView.findViewById(android.R.id.text1);
        textView.setText(todo.getTitle());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTodoClickListener.onTodoClicked(todo);
            }
        });
        return convertView;
    }
}
