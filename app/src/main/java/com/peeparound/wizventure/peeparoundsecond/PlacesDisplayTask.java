package com.peeparound.wizventure.peeparoundsecond;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacesDisplayTask extends AsyncTask<Object, Integer, List<HashMap<String, String>>> {

    JSONObject googlePlacesJson;
    GoogleMap googleMap;
    ListView listView;
    LazyAdapter adapter;
    Context ctx;
    static final String KEY_TITLE = "title";
    PlacesDisplayTask(Context ctx){

        this.ctx=ctx;
    }
    @Override
    protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

        List<HashMap<String, String>> googlePlacesList = null;
        Places placeJsonParser = new Places();

        try {
          listView = (ListView) inputObj[0];
            googlePlacesJson = new JSONObject((String) inputObj[1]);
            googlePlacesList = placeJsonParser.parse(googlePlacesJson);

        } catch (Exception e) {

            Log.d("Exception", e.toString());
        }
        return googlePlacesList;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, String>> list) {
      //  googleMap.clear();


        HashMap<Integer,List<String>> bndl=new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = list.get(i);
            List< String> d=new ArrayList<>();
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String icon=googlePlace.get("icon");

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");

            LatLng latLng = new LatLng(lat, lng);
            Log.d("photo",""+icon);
            System.out.println("photophotophoto"+icon);
            Log.d("placeNamee",""+placeName);
            Log.d("vicinityuu",""+vicinity);
            d.add(placeName);
            d.add(vicinity);
            d.add(icon);
        bndl.put(i,d);

        }
        System.out.println("kkkkkkkkkk"+bndl.get(2));
        adapter=new LazyAdapter((ListActivity)ctx,bndl);
        listView.setAdapter(adapter);
    }
}

