package com.shanshui.smartcommunity.android.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.adaptor.TopicAdaptor;
import com.shanshui.smartcommunity.android.adaptor.VoteAdaptor;
import com.shanshui.smartcommunity.android.model.Topic;
import com.shanshui.smartcommunity.android.model.Vote;
import com.shanshui.smartcommunity.android.repository.DatabaseRepo;
import com.shanshui.smartcommunity.android.util.LogHelper;
import com.shanshui.smartcommunity.android.viewmodel.TopicViewModel;
import com.shanshui.smartcommunity.android.viewmodel.VoteViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicFragment extends RecyclerViewFragment<Topic, TopicViewModel, TopicAdaptor> {
    private static final String ARG_NAME = "ARG_NAME";
    private String name;

    public TopicFragment() {
        // Required empty public constructor
    }

    @Override
    protected RecyclerView onCreateRecyclerView(View fragmentLayout) {
        RecyclerView recyclerView = fragmentLayout.findViewById(R.id.property_issue_recycler_view);
        return recyclerView;
    }

    @Override
    protected TopicAdaptor getRecyclerViewAdaptor() {
        return new TopicAdaptor(getContext());
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name fragment name.
     * @return A new instance of fragment VoteFragment.
     */
    public static TopicFragment newInstance(String name, int layout) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_LAYOUT, layout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LogHelper.TAG, "start creating topic fragment");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
        this.viewModel = ViewModelProviders.of(this).get(TopicViewModel.class);
        this.viewModel.setup(DatabaseRepo.newInstance(getContext()).topicDao());
        Log.d(LogHelper.TAG, "end creating topic fragment");
    }

}
