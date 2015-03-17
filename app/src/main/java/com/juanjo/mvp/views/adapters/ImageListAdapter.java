package com.juanjo.mvp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.juanjo.mvp.R;
import com.juanjo.mvp.models.ImageDto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by juanjo on 4/08/14.
 */
public class ImageListAdapter extends ArrayAdapter<ImageDto> {

    Context context;
    LayoutInflater inflater;

    public ImageListAdapter(Context context, List<ImageDto> comments) {
        super(context, 0, comments);

        this.context = context;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, parent,
                    false);

            holder = new ImageHolder();
            addViewsToHolder(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (ImageHolder) convertView.getTag();
        }
        ImageDto image = getItem(position);
        setDataIntoHolder(holder, image);

        return convertView;
    }

    private void addViewsToHolder(View convertView, ImageHolder holder) {
        holder.title = (TextView) convertView.findViewById(R.id.title);
        holder.image = (ImageView) convertView
                .findViewById(R.id.image);
    }

    private void setDataIntoHolder(ImageHolder holder, ImageDto image) {
        Picasso.with(context).load(image.getUrl()).fit().into(holder.image);
        holder.title
                .setText(image.getTitle());
    }

    class ImageHolder {
        ImageView image;
        TextView title;
    }
}
