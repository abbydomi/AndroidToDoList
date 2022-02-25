package com.dunny.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ActivityAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<Activity> objects;

    public ActivityAdapter(@NonNull Context context, int resource, @NonNull List<Activity> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    @NonNull
   @Override
   public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);
        TextView tvText = convertView.findViewById(R.id.tvText);
        //RadioButton rbTask = convertView.findViewById(R.id.rbTask);
        tvText.setText(objects.get(position).getName());
        //rbTask.setChecked(objects.get(position).isDone());

        return convertView;
    }
}
