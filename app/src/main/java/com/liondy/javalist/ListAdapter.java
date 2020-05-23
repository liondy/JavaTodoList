package com.liondy.javalist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<String> listItems;
    private Activity activity;
    private Context context;

    public ListAdapter(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        this.listItems = new ArrayList<String>();
    }

    public void add(String newTodo){
        listItems.add(newTodo);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        String todo = (String) getItem(i);
        ViewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_list,parent,false);
            TextView item = (TextView) convertView.findViewById(R.id.item_list);
            ImageButton trash = convertView.findViewById(R.id.trash_item);
            vh = new ViewHolder(item,trash);
            convertView.setTag(vh);
        }
        else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.updateView(todo,i);
        return convertView;
    }

    private class ViewHolder implements View.OnClickListener{
        private TextView item;
        private ImageButton trash;
        private int position;

        public ViewHolder(TextView item,ImageButton trash) {
            this.item = item;
            this.trash = trash;
            this.trash.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == this.trash.getId()){
                listItems.remove(this.position);
                notifyDataSetChanged();
            }
        }

        public void updateView(String todo,int position){
            this.position = position;
            this.item.setText(todo);
        }
    }
}
