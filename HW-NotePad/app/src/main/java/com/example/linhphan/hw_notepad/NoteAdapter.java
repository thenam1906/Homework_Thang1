package com.example.linhphan.hw_notepad;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Linh Phan on 10/8/2017.
 */

public class NoteAdapter extends ArrayAdapter<NoteModel> {

    List<NoteModel> listNote;
    Context context;
    int resource;

    public NoteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<NoteModel> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listNote=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            viewHolder.title= convertView.findViewById(R.id.tv_title);
            viewHolder.description= convertView.findViewById(R.id.tv_description);

            viewHolder.title.setText(listNote.get(position).getTitle());
            viewHolder.description.setText(listNote.get(position).getDescription());
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        return convertView;

    }
    public class ViewHolder
    {
        TextView title;
        TextView description;
    }
}
