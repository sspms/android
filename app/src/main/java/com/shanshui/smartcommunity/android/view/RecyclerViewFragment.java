package com.shanshui.smartcommunity.android.view;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.content.Context;
import android.net.Uri;
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
import com.shanshui.smartcommunity.android.adaptor.PagedListAdapterBase;
import com.shanshui.smartcommunity.android.model.Roomable;
import com.shanshui.smartcommunity.android.viewmodel.ViewModelBase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public abstract class RecyclerViewFragment<RM extends Roomable, VM extends ViewModelBase, AD extends PagedListAdapterBase> extends Fragment {
    public static final String ARG_LAYOUT = "ARG_LAYOUT";
    protected VM viewModel;
    protected int layout;
    private OnFragmentInteractionListener mListener;


    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    protected abstract RecyclerView onCreateRecyclerView(View fragmentLayout);

    protected abstract AD getRecyclerViewAdaptor();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            layout = getArguments().getInt(ARG_LAYOUT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(this.layout, container, false);
        final RecyclerView recyclerView = onCreateRecyclerView(layout);

        final AD adapter = getRecyclerViewAdaptor();
        this.viewModel.getData().observe(this, new Observer<PagedList<RM>>() {
            @Override
            public void onChanged(@Nullable PagedList<RM> propertyIssues) {
                adapter.submitList(propertyIssues);
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setSmoothScrollbarEnabled(true);
        llm.setItemPrefetchEnabled(true);// allow prefetch for better ux
        llm.setInitialPrefetchItemCount(6);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        //final FoldingCellListAdapter adapter = new FoldingCellListAdapter(getContext(), items);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            private GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(getContext(),
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                            if (view != null) {
                                RecyclerView.ViewHolder VH = recyclerView.getChildViewHolder(view);
                                onItemClick(VH);
                            }
                            return true;
                        }

                        @Override
                        public void onLongPress(MotionEvent e) {
                            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                            if (view != null) {
                                RecyclerView.ViewHolder VH = recyclerView.getChildViewHolder(view);
                                onItemClick(VH);
                            }
                        }

                        private void onItemClick(RecyclerView.ViewHolder viewHolder) {
                            if (viewHolder instanceof FoldingCellViewHolder) {
                                final FoldingCell fc = FoldingCellViewHolder.class.cast(viewHolder).getFoldingCell();
                                fc.toggle(false);
                            }
                        }
                    });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (gestureDetectorCompat != null) {
                    gestureDetectorCompat.onTouchEvent(e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                if (gestureDetectorCompat != null) {
                    gestureDetectorCompat.onTouchEvent(e);
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
