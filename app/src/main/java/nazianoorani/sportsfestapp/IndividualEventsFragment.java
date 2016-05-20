package nazianoorani.sportsfestapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

import nazianoorani.sportsfestapp.dto.EventDto;
import nazianoorani.sportsfestapp.networkmanager.AppController;
import nazianoorani.sportsfestapp.util.Constants;

/**
 * Created by nazianoorani on 24/02/16.
 */
public class IndividualEventsFragment extends Fragment  {
    RecyclerView recyclerIndiEvents;
    IndiEventsAdapter adapter;
    ArrayList <EventDto> arrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual_events,container,false);
        recyclerIndiEvents = (RecyclerView) view.findViewById(R.id.recycler_indivi_events);
        adapter = new IndiEventsAdapter(getActivity(),arrayList);
        getEvents();
        initRecycler();
        return view;
    }

    private void getEvents() {
        //Enter URL
        String URL = Constants.BASE_URL+Constants.FETCH_INDIVIDUAL_EVENTS;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

//                    progressDialog.dismiss();
                    if(!arrayList.isEmpty()){
                        arrayList.clear();
                    }
                try {
                    JSONArray jsonArray = response.getJSONArray("events");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        EventDto eventDto = new EventDto();
                        if (jsonObject.has("event")) {
                            eventDto.setEvent(jsonObject.getString("event"));
                        }
                        if (jsonObject.has("eventImageURL")) {
                            eventDto.setEventImageURL(jsonObject.getString("eventImageURL"));
                        }

                        if (jsonObject.has("eventNo")) {
                            eventDto.setEventNo(jsonObject.getInt("eventNo"));
                        }

                        arrayList.add(eventDto);
                        adapter.notifyDataSetChanged();

                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),volleyError.getMessage(),Toast.LENGTH_LONG);
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

    private void initRecycler() {
        recyclerIndiEvents.setAdapter(adapter);
        recyclerIndiEvents.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public class IndiEventsAdapter extends RecyclerView.Adapter<IndiviEventsViewHolder>{

        Context context ;
        ArrayList <EventDto> list;
        public IndiEventsAdapter(Context context , ArrayList <EventDto> list){
            this.context = context;
            this.list = list ;
        }


        @Override
        public IndiviEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_indivi_events, parent, false);
            IndiviEventsViewHolder viewHolder = new IndiviEventsViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(IndiviEventsViewHolder holder, int position) {
            String imgURL = Constants.BASE_URL +Constants.IMAGE_URL +arrayList.get(position).getEventImageURL();
            Picasso.with(context)
                    .load(imgURL)
                    .into(holder.imgEvent);
            holder.tvEvent.setText(arrayList.get(position).getEvent());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class IndiviEventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvEvent;
        ImageView imgEvent;
        public IndiviEventsViewHolder(View itemView) {
            super(itemView);
            tvEvent = (TextView) itemView.findViewById(R.id.event_name);
            imgEvent = (ImageView) itemView.findViewById(R.id.imgEvent);
            imgEvent.setOnClickListener(this);
            tvEvent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            Intent intent = new Intent(getActivity(),DetailsActivity.class);
            intent.putExtra("event",arrayList.get(getLayoutPosition()).getEventNo());
            getActivity().startActivity(intent);

        }

    }
}

