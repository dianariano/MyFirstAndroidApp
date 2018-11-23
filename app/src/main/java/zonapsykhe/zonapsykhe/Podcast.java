package zonapsykhe.zonapsykhe;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.io.IOException;


public class Podcast extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button play;

    MediaPlayer mPlayer;

    public Podcast() {

    }

    // TODO: Rename and change types and number of parameters
    public static Podcast newInstance(String param1, String param2) {
        Podcast fragment = new Podcast();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // crea la pagina del fragamento podcast
        View podcastView = inflater.inflate(R.layout.fragment_podcast, container, false);

        //boton de play para podcast
        play = (Button) podcastView.findViewById(R.id.play);

        //creacion de reproduccion de musica

        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String url = "http://108.178.57.196:8312/;stream.mp3";

        try {
            mPlayer.setDataSource(url);
            mPlayer.prepare();

        } catch (IOException e){
            Log.e("erro",e.getMessage());
            e.printStackTrace();
        }
        //boton para reproducir y parar la musica

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer.isPlaying()){
                    mPlayer.pause();
                }
                else{
                    mPlayer.start();
                }
            }
        });

        return podcastView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
