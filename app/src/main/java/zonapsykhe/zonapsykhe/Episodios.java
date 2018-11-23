package zonapsykhe.zonapsykhe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import zonapsykhe.zonapsykhe.episodes.BotonTextoImagenAsociacion;
import zonapsykhe.zonapsykhe.segment.EpisodeSegment;
import zonapsykhe.zonapsykhe.episodes.Item;
import zonapsykhe.zonapsykhe.episodes.MyDataEpisodes;


public class Episodios extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Creacion de lista que guarda los ids de los episodios, nombres e imagen del episodio
    List<BotonTextoImagenAsociacion> myButtons = Arrays.asList(
            new BotonTextoImagenAsociacion(R.id.episode1, R.id.textEpisode1, R.id.imageEpisodio1),
            new BotonTextoImagenAsociacion(R.id.episode2, R.id.textEpisode2, R.id.imageEpisodio2),
            new BotonTextoImagenAsociacion(R.id.episode3, R.id.textEpisode3, R.id.imageEpisodio3),
            new BotonTextoImagenAsociacion(R.id.episode4, R.id.textEpisode4, R.id.imageEpisodio4),
            new BotonTextoImagenAsociacion(R.id.episode5, R.id.textEpisode5, R.id.imageEpisodio5),
            new BotonTextoImagenAsociacion(R.id.episode6, R.id.textEpisode6, R.id.imageEpisodio6),
            new BotonTextoImagenAsociacion(R.id.episode7, R.id.textEpisode7, R.id.imageEpisodio7),
            new BotonTextoImagenAsociacion(R.id.episode8, R.id.textEpisode8, R.id.imageEpisodio8),
            new BotonTextoImagenAsociacion(R.id.episode9, R.id.textEpisode9, R.id.imageEpisodio9),
            new BotonTextoImagenAsociacion(R.id.episode10, R.id.textEpisode10, R.id.imageEpisodio10)
    );

    private String currentDataSource = "";

    public String getCurrentDataSource() {
//        Log.d("Juan", "current datasource is " + currentDataSource);
        return currentDataSource;
    }

    public void setCurrentDataSource(String currentDataSource) {
//        Log.d("Juan", "setting datasource to " + currentDataSource);
        this.currentDataSource = currentDataSource;
    }

    MediaPlayer mPlayer;
    RequestQueue requestQueue;

    // URL de la api spreaker
    String SPREAKER_URL = "https://api.spreaker.com/v2/";
    // Id del Podcast de la universidad del rosario
    String AUTHOR_ID = "8262147";

    private OnFragmentInteractionListener mListener;

    public Episodios() {

    }

    public List<BotonTextoImagenAsociacion> getMyButtons() {
        return myButtons;
    }

    // TODO: Rename and change types and number of parameters
    public static Episodios newInstance(String param1, String param2) {
        Episodios fragment = new Episodios();
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

        View episodiesView = inflater.inflate(R.layout.fragment_episodios, container, false);

        requestQueue = Volley.newRequestQueue(getActivity());

        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        getLastEpisodes(episodiesView);

        return episodiesView;
    }

    //Obtencion de los ultimos 10 episodios del podcast
    private void getLastEpisodes(final View v) {
        //URL de la api para obtener los episodios
        final String episodes_url = SPREAKER_URL + "/users/" + AUTHOR_ID + "/episodes?&limit=10";

        //Llamada get para la api, llamada para obtener los 10 episodios
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, episodes_url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                       final Gson gson = new Gson();
                       // GSON nos ayuda en la "traduccion" de json para un objeto java, java no entiende java
                       final MyDataEpisodes myResponse = gson.fromJson(response.toString(), MyDataEpisodes.class);
                        // Lista Java com las informaciones de los ultimos 10 episodios del podcast
                       final List<Item> ListaEpisodios = myResponse.getResponse().getItems();
                        for(int i = 0; i < ListaEpisodios.size(); i++) {
                            //URL para llamada http que obtendra la informacion de donde encontrar el mp3 del episodio
                           final String segment_url = SPREAKER_URL + "episodes/" + ListaEpisodios.get(i).getEpisodeId() + "?export=episode_segments";

                           final Button btn = (Button) v.findViewById(getMyButtons().get(i).getBotonId());
                           final TextView txt = (TextView) v.findViewById(getMyButtons().get(i).getTextoId());
                           final ImageView img = (ImageView) v.findViewById(getMyButtons().get(i).getImageId());

                           //Llamada get para la api, llamada para obtener los segmentos de cada episodio
                           JsonObjectRequest fileRequest = new JsonObjectRequest(Request.Method.GET, segment_url,
                                   new Response.Listener<JSONObject>() {
                                       @Override
                                       public void onResponse(JSONObject response) {
                                           final EpisodeSegment fileResponse = gson.fromJson(response.toString(), EpisodeSegment.class);
                                           final String segmentFileUrl = fileResponse.getResponse().getEpisode().getSegments().get(0).getHttpUrl();
                                           final String titleFile = fileResponse.getResponse().getEpisode().getTitle();
                                           final String imageFile = fileResponse.getResponse().getEpisode().getImageOriginalUrl();

                                           // Cargar la imagen del episodio de Internet
                                          new DownloadImageFromInternet(img).execute(imageFile);
                                            //Mostrar el titulo del episodio en el textview
                                           txt.setText(titleFile);
                                           //Accion del boton play pause para cada episodio
                                           btn.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   try {
                                                       if(mPlayer.isPlaying()) {
                                                        mPlayer.pause();
                                                       } else {
                                                           if (!getCurrentDataSource().equals(segmentFileUrl)) {
                                                               mPlayer.reset();
                                                               mPlayer.setDataSource(segmentFileUrl);
                                                               mPlayer.prepare();
                                                               setCurrentDataSource(segmentFileUrl);
                                                           }
                                                           mPlayer.start();
                                                       }
                                                   } catch (IOException e) {
                                                       Log.e("erro", e.getMessage());
                                                       e.printStackTrace();
                                                   }
                                               }
                                           });
                                       }
                                   }
                                   ,
                                   new Response.ErrorListener() {
                                       @Override
                                       public void onErrorResponse(VolleyError error) {
                                           Log.e("Volley", error.toString());
                                       }
                                   });

                           requestQueue.add(fileRequest);
                       }
                       }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }

    //Hace el download de la imagen
    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
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
