package com.example.firebasechatapplication1;

import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;

public abstract class FirebaseListAdapter<T> {
    public FirebaseListAdapter(MainActivity activity, Class<ChatMessage> modelClass, int modelLayout, DatabaseReference ref) {
    }

    protected abstract void populateView(View v, ChatMessage model, int position);

    public abstract View getView(int position, View view, ViewGroup viewGroup);

    public abstract int getViewTypeCount();

    public abstract int getItemViewType(int position);


}
