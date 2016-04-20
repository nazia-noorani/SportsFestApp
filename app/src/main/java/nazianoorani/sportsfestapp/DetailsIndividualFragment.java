package nazianoorani.sportsfestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nazianoorani on 19/04/16.
 */
public class DetailsIndividualFragment extends Fragment  {

    ArrayList<String> arrayList = new ArrayList<String>() {{
        add("Rules");
        add("Schedule");
        add("Results");
        add("Register");
        add("More");
    }};

    ListView listView;
    ArrayAdapter listAdapter;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indivi_event_details, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        Bundle bundle = getArguments();
        if(bundle!= null){
           int eventNo = bundle.getInt("event");
            switch (eventNo){
                case 1 :
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Badminton");
                    imageView.setBackgroundResource(R.mipmap.ic_badminton);
                    break;
                case 2 :
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Table Tennis");
                    imageView.setBackgroundResource(R.mipmap.ic_tt);
                    break;
                case 3 :
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Lawn Tennis");
                    imageView.setBackgroundResource(R.mipmap.ic_lt);
                    break;
            }
        }

        listView = (ListView) view.findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(listAdapter);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        setHasOptionsMenu(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetails(position);

            }
        });
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
    public  void openDetails(int position){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        switch (position){
            case 0:
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, new RulesFragment()).commit();
                break;

            case 1 :
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, new ScheduleFragment()).commit();
                break;

            case 2 :
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, new ResultsFragment()).commit();
                break;
            case 3 :
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, new RegisterFragment()).commit();
                break;
            case 4 :
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, new MoreFragment()).commit();
                break;
        }
    }

}

