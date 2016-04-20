package nazianoorani.sportsfestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by nazianoorani on 24/02/16.
 */
public class IndividualEventsFragment extends Fragment implements View.OnClickListener {
    ImageView imgBadminton ,imgTT, imgLT;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual_events,container,false);
        imgBadminton = (ImageView) view.findViewById(R.id.imgBadminton);
        imgLT = (ImageView) view.findViewById(R.id.imgLT);
        imgTT = (ImageView) view.findViewById(R.id.imgTT);
        imgBadminton.setImageResource(R.mipmap.ic_badminton);
        imgTT.setImageResource(R.mipmap.ic_tt);
        imgLT.setImageResource(R.mipmap.ic_lt);
        imgBadminton.setOnClickListener(this);
        imgLT.setOnClickListener(this);
        imgTT.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        switch (v.getId()){
            case R.id.imgBadminton :
                Toast.makeText(getContext(),"Badminton Details",Toast.LENGTH_SHORT).show();
                intent.putExtra("event",1);
                getActivity().startActivity(intent);
                break;
            case R.id.imgLT :
                Toast.makeText(getContext(),"LT Details",Toast.LENGTH_SHORT).show();
                intent.putExtra("event",2);
                getActivity().startActivity(intent);
                break;
            case R.id.imgTT :
                intent.putExtra("event",3);
                getActivity().startActivity(intent);
                Toast.makeText(getContext(),"TT Details",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
