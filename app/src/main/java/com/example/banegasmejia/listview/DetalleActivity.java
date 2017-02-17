package com.example.banegasmejia.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetalleActivity extends AppCompatActivity {
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        try {
            ((TextView) findViewById(R.id.nombre)).setText(json.getString("name"));
            ((TextView) findViewById(R.id.pais)).setText(json.getJSONObject("location").getString("country"));
            ((TextView) findViewById(R.id.cantidad)).setText(json.getString("loan_amount"));
            ((TextView) findViewById(R.id.uso)).setText(json.getString("use"));

            JSONObject imagenJSON = json.getJSONObject("image");
            int idImagen = imagenJSON.getInt("id");
            int idPlantilla = imagenJSON.getInt("id_template");

            JsonArrayRequest plantilla = new JsonArrayRequest("", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


        } catch (JSONException excepcion) {

        }
    }

    protected  void onResume() {

    }
}
