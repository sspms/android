package com.shanshui.smartcommunity.android.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.adaptor.PropertyIssueAdaptor;
import com.shanshui.smartcommunity.android.repository.DatabaseRepo;
import com.shanshui.smartcommunity.android.viewmodel.MyPropertyIssueViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyPropertyIssueFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyPropertyIssueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPropertyIssueFragment extends PropertyIssueFragment<MyPropertyIssueViewModel> {
    private static final String ARG_NAME = "ARG_NAME";
    private String name;

    public MyPropertyIssueFragment() {
        super();
        // Required empty public constructor
    }

    @Override
    protected RecyclerView onCreateRecyclerView(View fragmentLayout) {
        return fragmentLayout.findViewById(R.id.property_issue_recycler_view);
    }

    @Override
    protected PropertyIssueAdaptor getRecyclerViewAdaptor() {
        return new PropertyIssueAdaptor(getContext());
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment MyPropertyIssueFragment.
     */
    public static MyPropertyIssueFragment newInstance(String name, int layout) {
        MyPropertyIssueFragment fragment = new MyPropertyIssueFragment();
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        long id = 1L;//preferences.getLong("id", 0L);
        this.viewModel = ViewModelProviders.of(this).get(MyPropertyIssueViewModel.class);
        this.viewModel.setup(id, DatabaseRepo.newInstance(getContext()).propertyIssueDao());
    }
}
