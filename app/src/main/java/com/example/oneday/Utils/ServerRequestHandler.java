package com.example.oneday.Utils;

import android.content.Context;
import android.util.Base64;
import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.oneday.Models.Profile;
import com.example.oneday.interfaces.ServerRequestResponse;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

public class ServerRequestHandler {
    private static String apiVersion="v2";
    private static String rootURL="http://122.179.201.31/";
    private static String profileRoute=rootURL+"api/"+apiVersion+"/profile";

    private static String getRequestID()
    {
        String rid= FirebaseAuth.getInstance().getCurrentUser().getUid()+System.currentTimeMillis();
        return Base64.encodeToString(rid.getBytes(),Base64.URL_SAFE);
    }
    public static void SaveProfile(Context context, Profile profile, ServerRequestResponse responseListener) {
        try{
            JSONObject reqObject=new JSONObject();
            reqObject.put("requestID",getRequestID());
            reqObject.put("data",profile.toJSON());
            JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST, profileRoute+"/create", reqObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        if(response.getBoolean("success") &&responseListener!=null)
                            responseListener.onResponse(response.getString("message"));
                        else
                            responseListener.onError(response.getString("message"));
                    }catch (Exception e)
                    {
                        responseListener.onError(e.getMessage());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (TimeoutError.class.equals(error.getClass())) {
                        if(responseListener!=null)
                            responseListener.onError("Request TimeOut");
                    }
                    if (AuthFailureError.class.equals(error.getClass())) {
                        if(responseListener!=null)
                            responseListener.onError("Auth Failure Error");
                    }
                    if (NetworkError.class.equals(error.getClass())) {
                        if(responseListener!=null)
                            responseListener.onError("Network Error");
                    }
                    if (ClientError.class.equals(error.getClass())) {
                        if(responseListener!=null)
                            responseListener.onError("Client Error");
                    }
                    if (NoConnectionError.class.equals(error.getClass())) {
                        if(responseListener!=null)
                            responseListener.onError("No Connection Error");
                    }
                    if (ParseError.class.equals(error.getClass())) {
                        if(responseListener!=null)
                            responseListener.onError("Parse Error");
                    }
                    if (ServerError.class.equals(error.getClass())) {
                        if(responseListener!=null)
                            responseListener.onError("Server Error");
                    }

                }
            });
            //Adding request to Queue
            ServerRequestQueue.getInstance(context).addToRequestQueue(request);
        }catch (Exception e)
        {
            if(responseListener!=null)
                responseListener.onError(e.getMessage());
        }


    }
}
