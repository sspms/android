package com.shanshui.smartcommunity.android.view;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ramotion.foldingcell.FoldingCell;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.adaptor.FoldingCellViewHolder;
import com.shanshui.smartcommunity.android.adaptor.PropertyIssueAdaptor;
import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.viewmodel.ViewModelBase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropertyIssueFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public abstract class PropertyIssueFragment<VM extends ViewModelBase> extends RecyclerViewFragment<PropertyIssue, VM, PropertyIssueAdaptor> {

    public PropertyIssueFragment() {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
