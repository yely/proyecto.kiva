package com.example.banegasmejia.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

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
            final int idImagen = imagenJSON.getInt("id");
            final int idPlantilla = imagenJSON.getInt("id_template");
            final String patronURL;

            JsonObjectRequest plantillaRequest = new JsonObjectRequest("http://api.kivaws.org/v1/templates/images.json", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String patronURL;
                        JSONArray plantillas = response.getJSONArray("templates");
                        for (int i = 0; i < plantillas.length(); i++) {
                            JSONObject patron = plantillas.getJSONObject(i);
                            if (patron.getInt("id") == idPlantilla) {
                                patronURL = patron.getString("pattern");
                                cargarImagen(patronURL, idImagen);
                                break;
                            }
                        }
                    } catch (JSONException excepcion) {

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            MySingleton.getInstance(this).getRequestQueue().add(plantillaRequest);

        } catch (JSONException excepcion) {

        }
    }

    private void cargarImagen(String patronURL, int idImagen) {
        String url = patronURL.replace("<size>", "400");
        url = url.replace("<id>", "" + idImagen);

        NetworkImageView imagen = (NetworkImageView) findViewById(R.id.imagen);
        imagen.setImageUrl(url, MySingleton.getInstance(this).getImageLoader());
    }
}
