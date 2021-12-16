package sharedPreferences;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Place;

public class DataLocalManager {
    private static final String PREF_OBJECT_PLACE = "PREF_OBJECT_PLACE";
    private static  DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setListPlace(ArrayList<Place> listPlace){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(listPlace).getAsJsonArray();
        String strJsonPlace = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_OBJECT_PLACE,strJsonPlace);
    }

    public static List<Place> getListPlace(){
        String strJsonPlace = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_OBJECT_PLACE);
        List<Place> listPlace = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonPlace);
            JSONObject jsonObject;
            Place place ;
            Gson gson = new Gson();
            for(int i = 0; i <jsonArray.length() ; i++ )
            {
                jsonObject = jsonArray.getJSONObject(i);
                place = gson.fromJson(jsonObject.toString(), Place.class);
                listPlace.add((Place) place);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listPlace;
    }
}
