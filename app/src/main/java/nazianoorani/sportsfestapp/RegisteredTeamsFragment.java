package nazianoorani.sportsfestapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nazianoorani.sportsfestapp.Adapter.CustomAdapter;
import nazianoorani.sportsfestapp.dto.TeamDto;
import nazianoorani.sportsfestapp.networkmanager.AppController;
import nazianoorani.sportsfestapp.util.Constants;
import nazianoorani.sportsfestapp.util.EventName;

/**
 * Created by nazianoorani on 24/04/16.
 */
public class RegisteredTeamsFragment extends Fragment{
    ListView listView;
    ArrayList <String>listTeams = new ArrayList();
    ArrayList <TeamDto> listDto = new ArrayList<>();
    int eventNo;
    String eventName ="";
    CustomAdapter customAdapter;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registered_teams,container,false);
        Bundle bundle = getArguments();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        if(bundle !=  null){
            eventNo = bundle.getInt("eventNo");
            eventName = EventName.getEventName(eventNo);
            Log.e("EVENTTTTT",eventName);
        }
        getTeamList();
        listView = (ListView) view.findViewById(R.id.listTeams);
        customAdapter = new CustomAdapter(getActivity(),listDto);
        listView.setAdapter(customAdapter);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((DetailsActivity) getActivity()).getSupportActionBar().setTitle(EventName.getEventName(eventNo)+" - Registered Teams");
        setHasOptionsMenu(true);
        return view;

    }

    public ArrayList getTeamList() {
        String url = Constants.BASE_URL+"get_reg_teams.php?event="+eventName;
        Log.e("event",eventName);
        Log.e("URL", url);

        progressDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressDialog.dismiss();
                    if(!listDto.isEmpty()){
                        listDto.clear();
                    }
                    JSONArray jsonArray = response.getJSONArray("teams");
                    for(int i =0; i< jsonArray.length() ;i++){
                        JSONObject jsonObject =  jsonArray.getJSONObject(i);
                        TeamDto teamDto = new TeamDto();
                        if(jsonObject.has("name")){
                            teamDto.setName(jsonObject.getString("name"));
                        }
                        if(jsonObject.has("college")){
                            teamDto.setCollege(jsonObject.getString("college"));
                        }
                        if(jsonObject.has("phone")){
                            teamDto.setPhone(jsonObject.getString("phone"));
                        }
                        listDto.add(teamDto);
                        customAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
        return listTeams;
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
