package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shanshui.smartcommunity.android.model.Topic;

import java.util.List;

/**
 * Created by I336253 on 2/24/2018.
 */

public class CardListAdaptor extends ArrayAdapter<Topic> {
    public CardListAdaptor(Context context, List<Topic> topics) {
        super(context, 0, topics);
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.bbs_topic, parent, false);

            viewHolder.authorView = convertView.findViewById(R.id.bbs_topic_author);
            viewHolder.commentView = convertView.findViewById(R.id.bbs_topic_comment_num);
            viewHolder.dateView = convertView.findViewById(R.id.bbs_topic_date);
            viewHolder.titleView = convertView.findViewById(R.id.bbs_topic_title);
            viewHolder.viewedView = convertView.findViewById(R.id.bbs_topic_view_num);
            viewHolder.watchView = convertView.findViewById(R.id.bbs_topic_watch_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        bind(viewHolder, getItem(position));
        return convertView;
    }

    void bind(ViewHolder viewHolder, Topic item) {
        if (item == null) {
            return;
        }
        viewHolder.watchView.setText(item.getWatch());
        viewHolder.viewedView.setText(item.getViewed());
        viewHolder.titleView.setText(item.getTitle());
        viewHolder.authorView.setText(String.valueOf(item.getAuthor()));
        viewHolder.dateView.setText(item.getDate());
        viewHolder.commentView.setText(item.getComments());
    }

    private static class ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView dateView;
        TextView watchView;
        TextView commentView;
        TextView viewedView;
    }
}
