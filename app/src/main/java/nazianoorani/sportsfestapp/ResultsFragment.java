package nazianoorani.sportsfestapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import nazianoorani.sportsfestapp.util.EventName;

/**
 * Created by nazianoorani on 20/04/16.
 */
public class ResultsFragment extends Fragment {

    int eventNo;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results,container,false);
        Bundle bundle = getArguments();
        if(bundle!= null) {
            eventNo = bundle.getInt("eventNo");
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((DetailsActivity) getActivity()).getSupportActionBar().setTitle(EventName.getEventName(eventNo)+" - Results");
        setHasOptionsMenu(true);
        return view;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return true;
    }
}
