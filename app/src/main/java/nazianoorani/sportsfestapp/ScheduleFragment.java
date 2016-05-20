package nazianoorani.sportsfestapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import nazianoorani.sportsfestapp.data.DatabaseContract;
import nazianoorani.sportsfestapp.dto.ScheduleDto;
import nazianoorani.sportsfestapp.networkmanager.AppController;
import nazianoorani.sportsfestapp.util.Constants;
import nazianoorani.sportsfestapp.util.EventName;

/**
 * Created by nazianoorani on 20/04/16.
 */
public class ScheduleFragment extends Fragment {
    int eventNo;
    RecyclerView mRecyclerView;
    ScheduleListAdapter adapter;
    ArrayList<ScheduleDto> listSchedule = new ArrayList<>();
    ContentResolver resolver;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_shedule);
        adapter = new ScheduleListAdapter(getActivity(),listSchedule);

        Bundle bundle = getArguments();
        if(bundle!= null) {
            eventNo = bundle.getInt("eventNo");
        }

        initRecyclerView();
        getSchedule();


        resolver = getActivity().getContentResolver();
//        Cursor eventCursor = resolver.query(DatabaseContract.match_table.
//                buildMatchWithMatchDate(), null, null, null, null);




        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((DetailsActivity) getActivity()).getSupportActionBar().setTitle(EventName.getEventName(eventNo)+" - Schedule");
        setHasOptionsMenu(true);

        return view;
    }

    private void initRecyclerView() {
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getSchedule() {
        //Enter URL
        String URL = Constants.BASE_URL + Constants.SCHEDULE_URL + eventNo;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("schedule");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ScheduleDto scheduleDto = new ScheduleDto();
                        if (jsonObject.has("event")) {
                            scheduleDto.setEvent(jsonObject.getString("event"));
                        }
                        if (jsonObject.has("matchDate")) {
                            scheduleDto.setMatchDate(jsonObject.getString("matchDate"));
                        }

                        if (jsonObject.has("matchTime")) {
                            scheduleDto.setMatchTime(jsonObject.getString("matchTime"));
                        }

                        if (jsonObject.has("nameTeamA")) {
                            scheduleDto.setNameTeamA(jsonObject.getString("nameTeamA"));
                        }

                        if (jsonObject.has("nameTeamB")) {
                            scheduleDto.setNameTeamB(jsonObject.getString("nameTeamB"));
                        }
                        if (jsonObject.has("chestURLTeamA")) {
                            scheduleDto.setTeamAChestURL(jsonObject.getString("chestURLTeamA"));
                        }

                        if (jsonObject.has("chestURLTeamB")) {
                            scheduleDto.setTeamBChestURL(jsonObject.getString("chestURLTeamB"));
                        }


                        listSchedule.add(scheduleDto);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), volleyError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        AppController.getInstance().addToRequestQueue(request);
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


    public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>{

        Context context;
        ArrayList<ScheduleDto> list;
        public ScheduleListAdapter(Context context , ArrayList<ScheduleDto>list){
            this.context = context;
            this.list = list;

        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_schedule, null);
            ViewHolder viewHolder = new ViewHolder(layoutView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ScheduleDto scheduleDto = list.get(position);
            holder.nameTeamA.setText(scheduleDto.getNameTeamA());
            holder.nameTeamB.setText(scheduleDto.getNameTeamB());
            holder.matchTime.setText(scheduleDto.getMatchTime());
            holder.matchDate.setText(scheduleDto.getMatchDate());

            String imgURLA = Constants.BASE_URL +Constants.IMAGE_URL +list.get(position).getTeamAChestURL();
            String imgURLB = Constants.BASE_URL + Constants.IMAGE_URL +list.get(position).getTeamBChestURL();
            Picasso.with(context)
                    .load(imgURLA)
                    .into(holder.chestTeamA);

            Picasso.with(context)
                    .load(imgURLB)
                    .into(holder.chestTeamB);

            updateDataBase(scheduleDto);
        }

        @Override
        public int getItemCount() {
            return listSchedule.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView nameTeamA , nameTeamB , matchTime , matchDate , venue;
            ImageView chestTeamA , chestTeamB;

            public ViewHolder(View itemView) {
                super(itemView);
                nameTeamA = (TextView) itemView.findViewById(R.id.teamA_name);
                nameTeamB = (TextView) itemView.findViewById(R.id.teamB_name);
                matchDate = (TextView) itemView.findViewById(R.id.match_date);
                matchTime = (TextView) itemView.findViewById(R.id.match_time);
                venue = (TextView) itemView.findViewById(R.id.venue);
                chestTeamA = (ImageView) itemView.findViewById(R.id.teamA_chest);
                chestTeamB = (ImageView) itemView.findViewById(R.id.teamB_chest);

            }
        }


        public void updateDataBase(ScheduleDto dto){
            Vector<ContentValues> values = new Vector <ContentValues> (listSchedule.size());
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContract.match_table.DATE_COL,dto .getMatchDate());
            contentValues.put(DatabaseContract.match_table.TIME_COL, dto.getMatchTime());
            contentValues.put(DatabaseContract.match_table.TEAM_A_COL, dto.getNameTeamA());
            contentValues.put(DatabaseContract.match_table.TEAM_B_COL, dto.getNameTeamB());
            contentValues.put(DatabaseContract.match_table.EVENT, dto.getEvent());
            contentValues.put(DatabaseContract.match_table.MATCH_DATE, dto.getMatchDate());
            values.add(contentValues);
            ContentValues[] insert_data = new ContentValues[values.size()];
            values.toArray(insert_data);
            resolver.bulkInsert(
                    DatabaseContract.BASE_CONTENT_URI,insert_data);


        }

    }



}
