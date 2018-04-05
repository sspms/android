package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.ramotion.foldingcell.FoldingCell;
import com.shanshui.smartcommunity.android.model.Topic;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NeighbourhoodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NeighbourhoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeighbourhoodFragment extends Fragment implements OnTabSelectListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mName;
    private final String[] subTabs = {
            "社区投票", "社区论坛", "社区提案"
    };
    private SlidingTabLayout tabLayout_2;
    private OnFragmentInteractionListener mListener;

    public NeighbourhoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NeighbourhoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NeighbourhoodFragment newInstance(String param1) {
        NeighbourhoodFragment fragment = new NeighbourhoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_neighbourhood, container, false);

        ViewPager vp = frameLayout.findViewById(R.id.vp);
        List<ListView> listViews = new ArrayList();
        for (int i = 0; i < this.subTabs.length; i++) {
            ListView listView;
            ListAdapter adapter;
            if ("社区论坛".equals(this.subTabs[i])) {
                listView = (ListView) inflater.inflate(R.layout.cell_fullscreen, frameLayout, false);
                adapter = new CardListAdaptor(getContext(), Topic.mock());
            } else if ("社区投票".equals(this.subTabs[i])) {
                listView = (ListView) inflater.inflate(R.layout.cell_listview, frameLayout, false);
                adapter = new VoteCardListAdapter(getContext(), VoteItem.mock());
                final VoteCardListAdapter theAdaptor = (VoteCardListAdapter) adapter;
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                        // toggle clicked cell state
                        ((FoldingCell) view).toggle(false);
                        // register in adapter that state for selected cell is toggled
                        theAdaptor.registerToggle(pos);
                    }
                });
            } else {
                listView = (ListView) inflater.inflate(R.layout.cell_listview, frameLayout, false);
                // prepare elements to display
                final ArrayList<Item> items = Item.getTestingList();

                // add custom btn handler to first list item
                items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
                    }
                });

                // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
                adapter = new FoldingCellListAdapter(getContext(), items);
                // set on click event listener to list view
                final FoldingCellListAdapter theAdaptor = (FoldingCellListAdapter) adapter;
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                        // toggle clicked cell state
                        ((FoldingCell) view).toggle(false);
                        // register in adapter that state for selected cell is toggled
                        theAdaptor.registerToggle(pos);
                    }
                });
            }
            listView.setAdapter(adapter);
            listViews.add(listView);
        }

        vp.setAdapter(new

                ViewPagerAdapter(listViews));
        vp.setCurrentItem(0);
        tabLayout_2 = frameLayout.findViewById(R.id.tl_2);

        tabLayout_2.setViewPager(vp, this.subTabs);
        tabLayout_2.setOnTabSelectListener(this);

        //int currentPos = 0;
        //vp.setCurrentItem(currentPos);

        tabLayout_2.showDot(0);

        tabLayout_2.showMsg(3, 5);
        tabLayout_2.setMsgMargin(3, 0, 10);
        MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
        if (rtv_2_3 != null)

        {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }

        tabLayout_2.showMsg(5, 5);
        tabLayout_2.setMsgMargin(5, 0, 10);
        return frameLayout;
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

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

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
