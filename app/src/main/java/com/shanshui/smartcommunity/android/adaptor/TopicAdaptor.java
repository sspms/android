package com.shanshui.smartcommunity.android.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.model.Topic;
import com.shanshui.smartcommunity.android.util.LogHelper;

/**
 * Created by I336253 on 4/5/2018.
 */

public class TopicAdaptor extends PagedListAdapterBase<Topic, TopicAdaptor.ViewHolder> {
    private Context context;

    public TopicAdaptor(Context context) {
        super();
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LogHelper.TAG, "start creating topic view holder");
        CardView convertView = (CardView) LayoutInflater.from(context).inflate(R.layout.bbs_topic, parent, false);
        Log.d(LogHelper.TAG, "start creating topic view holder - inflate done");
        ViewHolder vh = new ViewHolder(convertView);
        Log.d(LogHelper.TAG, "end creating topic view holder");
        return vh;
    }

    public static class ViewHolder extends PagedListAdapterBase.ViewHolderBase<Topic> {

        private TextView titleView;
        private TextView authorView;
        private TextView dateView;
        private TextView watchView;
        private TextView commentView;
        private TextView viewedView;

        public ViewHolder(View view) {
            super(view);
            authorView = view.findViewById(R.id.bbs_topic_author);
            commentView = view.findViewById(R.id.bbs_topic_comment_num);
            dateView = view.findViewById(R.id.bbs_topic_date);
            titleView = view.findViewById(R.id.bbs_topic_title);
            viewedView = view.findViewById(R.id.bbs_topic_view_num);
            watchView = view.findViewById(R.id.bbs_topic_watch_num);
        }

        @Override
        void bindTo(Topic instance, int position) {
            super.bindTo(instance,position);
            watchView.setText(instance.getWatch());
            viewedView.setText(instance.getViewed());
            titleView.setText(instance.getTitle());
            authorView.setText(String.valueOf(instance.getAuthor()));
            dateView.setText(instance.getDate());
            commentView.setText(instance.getComments());
        }

        @Override
        void clear() {

        }
    }
}
