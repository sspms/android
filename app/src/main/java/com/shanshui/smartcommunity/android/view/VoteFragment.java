package com.shanshui.smartcommunity.android.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.adaptor.VoteAdaptor;
import com.shanshui.smartcommunity.android.model.Vote;
import com.shanshui.smartcommunity.android.repository.DatabaseRepo;
import com.shanshui.smartcommunity.android.viewmodel.VoteViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VoteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoteFragment extends RecyclerViewFragment<Vote, VoteViewModel, VoteAdaptor> {
    private static final String ARG_NAME = "ARG_NAME";
    private String name;

    public VoteFragment() {
        // Required empty public constructor
    }

    @Override
    protected RecyclerView onCreateRecyclerView(View fragmentLayout) {
        RecyclerView recyclerView = fragmentLayout.findViewById(R.id.property_issue_recycler_view);
        return recyclerView;
    }

    @Override
    protected VoteAdaptor getRecyclerViewAdaptor() {
        return new VoteAdaptor(getContext());
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name fragment name.
     * @return A new instance of fragment VoteFragment.
     */
    public static VoteFragment newInstance(String name, int layout) {
        VoteFragment fragment = new VoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_LAYOUT, layout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
        this.viewModel = ViewModelProviders.of(this).get(VoteViewModel.class);
        this.viewModel.setup(DatabaseRepo.newInstance(getContext()).voteDao());
    }

}
