package nazianoorani.sportsfestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nazianoorani.sportsfestapp.networkmanager.AppController;

/**
 * Created by nazianoorani on 20/04/16.
 */
public class RegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {

    Button btnSubmit;
    EditText editName,editEmail,editCollege,editMobNo;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        editName = (EditText) view.findViewById(R.id.editName);
        editEmail = (EditText) view.findViewById(R.id.editEmail);
        editCollege = (EditText) view.findViewById(R.id.editCollege);
        editMobNo = (EditText) view.findViewById(R.id.editMobNo);
        btnSubmit.setOnClickListener(this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Spinner element
        spinner = (Spinner) view.findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add( "Female");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit :
                register();
        }

    }

    private void register() {
        JSONObject params = null;
        try {
            params = buildParams();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (params != null) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://feedboard.in/api/reg_sports_ind/.php", params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Toast.makeText(getActivity(),jsonObject.toString(),Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    Toast.makeText(getActivity(),volleyError.toString(),Toast.LENGTH_LONG).show();
                }
            });
        AppController.getInstance().addToRequestQueue(request);
        }
    }
    private JSONObject buildParams() throws JSONException {
        JSONObject params = new JSONObject();
        params.put("name",editName.getText().toString());
        params.put("phone",editMobNo.getText().toString());
        params.put("email",editEmail.getText().toString());
        params.put("college",editCollege.getText().toString());
        params.put("gender",spinner.getSelectedItem().toString());

        return params;

    }
}
