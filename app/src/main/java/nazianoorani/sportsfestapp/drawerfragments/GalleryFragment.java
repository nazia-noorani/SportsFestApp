package nazianoorani.sportsfestapp.drawerfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nazianoorani.sportsfestapp.Adapter.RecyclerViewAdapter;
import nazianoorani.sportsfestapp.R;

/**
 * Created by nazianoorani on 24/04/16.
 */
public class GalleryFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Integer> imageList = new ArrayList<Integer>() {{




    }};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_gallery,container,false);
       // recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
      //  adapter = new RecyclerViewAdapter(getActivity(),imageList);
     //   initRecyler();
        return view;
    }

    private void initRecyler() {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
    }
}
