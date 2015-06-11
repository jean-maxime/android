package net.hetic.hetweetic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import net.hetic.hetweetic.Models.Tweet;
import net.hetic.hetweetic.Utils.Config;
import net.hetic.hetweetic.Utils.KeyboardBehavior;
import net.hetic.hetweetic.Webservices.Webservices;

import org.apache.http.Header;


public class HomeFragment extends Fragment {

    private EditText mUsernameEt;
    private Button mDisplayTimelineBt;
    private TextView mRawResultTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        mUsernameEt = (EditText) layout.findViewById(R.id.username_et);
        mRawResultTv = (TextView) layout.findViewById(R.id.raw_result_tv);
        mDisplayTimelineBt = (Button) layout.findViewById(R.id.display_timeline_bt);

        mDisplayTimelineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              KeyboardBehavior.hide(getActivity());

                List_tweet listTweet = new List_tweet();

                Bundle tweetBundle = new Bundle();
                tweetBundle.putString("Username", mUsernameEt.getText().toString());

                listTweet.setArguments(tweetBundle);

                android.app.FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, listTweet);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });

        return layout;
    }


    @Override
    public void onResume() {
        super.onResume();

    }


}
