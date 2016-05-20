package nazianoorani.sportsfestapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
import java.util.Timer;
import java.util.TimerTask;

import nazianoorani.sportsfestapp.Adapter.SlidingImageAdapter;

/**
 * Created by nazianoorani on 19/04/16.
 */
public class DetailsIndividualFragment extends Fragment  {

    ArrayList<String> arrayList = new ArrayList<String>() {{
        add("Rules");
        add("Schedule");
        add("Results");
        add("Register");
        add("Registered Teams");
    }};

    int eventNo;
    ViewPager mPager;
    ListView listView;
    ArrayAdapter listAdapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private  final int pageSize =3;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indivi_event_details, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);

        Bundle bundle = getArguments();
        if(bundle!= null){
           eventNo = bundle.getInt("event");
            if(ImagesArray.size() != 0){
                ImagesArray.clear();
            }
            switch (eventNo){
                case 1 :
                    ImagesArray.add(R.mipmap.img_bad1);
                    ImagesArray.add(R.mipmap.img_bad2);
                    ImagesArray.add(R.mipmap.img_bad3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Badminton");
//                    imageView.setBackgroundResource(R.mipmap.ic_badminton);
                    break;
                case 2 :
                    ImagesArray.add(R.mipmap.img_tt1);
                    ImagesArray.add(R.mipmap.img_tt2);
                    ImagesArray.add(R.mipmap.img_tt3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Table Tennis");
//                    imageView.setBackgroundResource(R.mipmap.ic_tt);
                    break;
                case 3 :
                    ImagesArray.add(R.mipmap.img_lt1);
                    ImagesArray.add(R.mipmap.img_lt2);
                    ImagesArray.add(R.mipmap.img_lt3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Lawn Tennis");
//                    imageView.setBackgroundResource(R.mipmap.ic_lt);
                    break;
                case 4 :
                    ImagesArray.add(R.mipmap.img1);
                    ImagesArray.add(R.mipmap.img2);
                    ImagesArray.add(R.mipmap.img3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Athletics");
//                    imageView.setBackgroundResource(R.mipmap.ic_ckt);
                    break;
                case 5 :
                    ImagesArray.add(R.mipmap.img1);
                    ImagesArray.add(R.mipmap.img2);
                    ImagesArray.add(R.mipmap.img3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Chess");
//                    imageView.setBackgroundResource(R.mipmap.ic_ckt);
                    break;
                case 6 :
                    ImagesArray.add(R.mipmap.img_ckt1);
                    ImagesArray.add(R.mipmap.img_ckt2);
                    ImagesArray.add(R.mipmap.img_ckt3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Cricket");
//                    imageView.setBackgroundResource(R.mipmap.ic_ckt);
                    break;
                case 7 :
                    ImagesArray.add(R.mipmap.img_fb1);
                    ImagesArray.add(R.mipmap.img5);
                    ImagesArray.add(R.mipmap.img_fb3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Football");
//                    imageView.setBackgroundResource(R.mipmap.ic_fb);
                    break;
                case 8 :
                    ImagesArray.add(R.mipmap.img_bb1);
                    ImagesArray.add(R.mipmap.img_bb2);
                    ImagesArray.add(R.mipmap.img5);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Basketball");
//                    imageView.setBackgroundResource(R.mipmap.ic_bb);
                    break;
                case 9 :
                    ImagesArray.add(R.mipmap.img_vb1);
                    ImagesArray.add(R.mipmap.img_vb2);
                    ImagesArray.add(R.mipmap.img_vb3);
                    ((DetailsActivity) getActivity()).getSupportActionBar().setTitle("Volleyball");
//                    imageView.setBackgroundResource(R.mipmap.ic_ckt);
                    break;



            }
        }
        init(view);
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
        Bundle bundle = new Bundle();
        bundle.putInt("eventNo",eventNo);
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new RulesFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, fragment).commit();
                break;

            case 1 :
                fragment = new ScheduleFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, fragment).commit();
                break;

            case 2 :
                fragment = new ResultsFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, fragment).commit();
                break;
            case 3 :
                fragment = new RegisterFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, fragment).commit();
                break;
            case 4 :
                fragment = new RegisteredTeamsFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().addToBackStack(null).
                        replace(R.id.fragment_container, fragment).commit();
                break;

        }
    }
    private void init(View view) {
//        if(ImagesArray.size() == 0) {
//            for (int i = 0; i < IMAGES.length; i++)
//                ImagesArray.add(IMAGES[i]);
//
//        }


        mPager.setAdapter(new SlidingImageAdapter(getActivity(),ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);



        //x
        //
        //       No of pages set to 3!!
        NUM_PAGES = pageSize;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

}

