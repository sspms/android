package com.shanshui.smartcommunity.android.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.model.PropertyIssueComment;
import com.shanshui.smartcommunity.android.util.DateHelper;

/**
 * Created by I336253 on 4/17/2018.
 */

public class PropertyIssueCommentAdaptor extends PagedListAdapterBase<PropertyIssueComment,
        PropertyIssueCommentAdaptor.ViewHolder> {

    private Context context;

    public PropertyIssueCommentAdaptor(Context context) {
        super();
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(context)
                .inflate(R.layout.comments, parent, false);
        return new ViewHolder(layout);
    }

    public static class ViewHolder extends PagedListAdapterBase.ViewHolderBase<PropertyIssueComment> {
        private TextView name;
        private TextView date;
        private TextView content;

        public ViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.bbs_topic_author);
            this.date = view.findViewById(R.id.bbs_topic_date);
            this.content = view.findViewById(R.id.bbs_topic_title);
        }

        @Override
        void bindTo(PropertyIssueComment comment, int position) {
            super.bindTo(comment, position);
            this.name.setText(comment.getUser().getNickName());
            this.date.setText(DateHelper.toChineseTimeZone(comment.getDate()));
            this.content.setText(comment.getContent());
        }

        @Override
        void clear() {

        }
    }
}
