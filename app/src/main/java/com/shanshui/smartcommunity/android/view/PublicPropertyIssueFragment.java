package com.shanshui.smartcommunity.android.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.shanshui.smartcommunity.android.repository.DatabaseRepo;
import com.shanshui.smartcommunity.android.util.LogHelper;
import com.shanshui.smartcommunity.android.viewmodel.PublicPropertyIssueViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PublicPropertyIssueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicPropertyIssueFragment extends PropertyIssueFragment<PublicPropertyIssueViewModel> {
    private static final String ARG_NAME = "ARG_NAME";
    private String name;

    public PublicPropertyIssueFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment MyPropertyIssueFragment.
     */
    public static PublicPropertyIssueFragment newInstance(String name, int layout) {
        PublicPropertyIssueFragment fragment = new PublicPropertyIssueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_LAYOUT, layout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LogHelper.TAG, "start creating public property issue fragment");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
        this.viewModel = ViewModelProviders.of(this).get(PublicPropertyIssueViewModel.class);
        this.viewModel.setup(DatabaseRepo.newInstance(getContext()).propertyIssueDao());
        Log.d(LogHelper.TAG, "end creating public property issue fragment");
    }
}
