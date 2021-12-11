package api;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import model.Location;
import model.ModelCommon;
import model.OverViewInfo;
import model.Today;
import model.Total;

public class VolleyService {
    public static void getRequest(Context context, final VolleryResponseListener listener) {
        //Khai bao de nhan du lieu
        ModelCommon modelCommon = new ModelCommon();

        //Khai bao URL API
        String url = ServiceInfo.Base_URL+"data.json";

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(JSONObject response) {
                //Lay cac Json tuong ung
                if(response!=null){
                    JSONObject total = response.optJSONObject("total");
                    JSONObject today = response.optJSONObject("today");
                    JSONArray overview = response.optJSONArray("overview");
                    JSONArray locations = response.optJSONArray("locations");
                    // Convert Json Object va Json Array bang Gson
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonElement mJson = parser.parse(String.valueOf(total));
                    Total objectTotal = gson.fromJson(mJson,Total.class);
                    mJson = parser.parse(String.valueOf(total));
                    Today objectToday = gson.fromJson(mJson,Today.class);

                    mJson = parser.parse(String.valueOf(overview));
                    OverViewInfo[] objectOverViewInfo = gson.fromJson(mJson,OverViewInfo[].class);

                    ArrayList<OverViewInfo> overviewInfos = new ArrayList<>();
                    Arrays.stream(objectOverViewInfo).forEach(overviewInfos::add);

                    mJson = parser.parse(String.valueOf(locations));
                    Location[] objectLocation = gson.fromJson(mJson,Location[].class);

                    ArrayList<Location> locationList = new ArrayList<>();
                    Arrays.stream(objectLocation).forEach(locationList::add);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Log", error.toString());
            }
        }
        );

    }
}
