package nazianoorani.sportsfestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by nazianoorani on 22/04/16.
 */
public class TeamEventsFragment extends Fragment implements View.OnClickListener{
    ImageView imgCkt ,imgFB, imgBB ,imgVB;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_events,container,false);
        imgCkt = (ImageView) view.findViewById(R.id.imgCKT);
        imgFB = (ImageView) view.findViewById(R.id.imgFB);
        imgBB = (ImageView) view.findViewById(R.id.imgBB);
        imgVB = (ImageView) view.findViewById(R.id.imgVB);
        imgCkt.setBackground(getActivity().getResources().getDrawable(R.mipmap.ic_ckt));
        imgFB.setBackground(getActivity().getResources().getDrawable(R.mipmap.ic_fb));
        imgBB.setBackground(getActivity().getResources().getDrawable(R.mipmap.ic_bb));
        imgVB.setBackground(getActivity().getResources().getDrawable(R.mipmap.ic_vb));
        imgCkt.setOnClickListener(this);
        imgFB.setOnClickListener(this);
        imgBB.setOnClickListener(this);
        imgVB.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        switch (v.getId()){
            case R.id.imgCKT :
                Toast.makeText(getContext(),"Cricket",Toast.LENGTH_SHORT).show();
                intent.putExtra("event",6);
                getActivity().startActivity(intent);
                break;

            case R.id.imgFB :
                intent.putExtra("event",7);
                getActivity().startActivity(intent);
                Toast.makeText(getContext(),"Football",Toast.LENGTH_SHORT).show();
                break;

            case R.id.imgBB :
                Toast.makeText(getContext(),"Basketball",Toast.LENGTH_SHORT).show();
                intent.putExtra("event",8);
                getActivity().startActivity(intent);
                break;
            case R.id.imgVB :
                Toast.makeText(getContext(),"Volleyball",Toast.LENGTH_SHORT).show();
                intent.putExtra("event",9);
                getActivity().startActivity(intent);
                break;
        }
    }


}
