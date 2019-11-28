package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//String meuJson = "";
//String meuJssonArray;

public class MainActivity extends AppCompatActivity {


//  private String URL = "http://www.json-generator.com/api/json/get/cqKZcoEcKq?indent=2";
//  private String URL = "http://10.0.3.2:5000/evaluate";
//  private String URL = "http://10.0.3.2:5000/teste_evaluate/AAPL/1-1-2015/1-1-2017";
//  private String URL = "http://10.0.3.2:5000/teste_evaluate/";
  private String URL = "http://stock-evaluate-api.herokuapp.com/teste_evaluate/";
  EditText txtSymbol;

  Button btnEvaluate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);



    btnEvaluate = findViewById(R.id.btnEvaluate);
    btnEvaluate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        EditText txtStart;
        EditText txtEnd;

        txtSymbol = findViewById(R.id.edtSymbol);
        txtStart = findViewById(R.id.edtIni);
        txtEnd = findViewById(R.id.edtFin);

        String symbol = txtSymbol.getText().toString();
        String star = txtStart.getText().toString();
        String end = txtEnd.getText().toString();
//
////        String teste = "http://10.0.3.2:5000/teste_evaluate/";
//
        URL += symbol + "/" + star + "/" + end;

        new JsonTask().execute(URL);
      }
    });

//    new JsonTask().execute(URL);


  }

  private class JsonTask extends AsyncTask<String, String, String>{
    protected void onPreExecute(){
      super.onPreExecute();
    }

    protected String doInBackground(String... params){
      HttpURLConnection connection = null;
      BufferedReader reader = null;

      try {
        URL url = new URL(params[0]);
        Log.i("url", String.valueOf(url));

        connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        InputStream stream = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(stream));

        StringBuffer buffer = new StringBuffer();

        String line = "";

        while ((line = reader.readLine()) != null){
          buffer.append(line+"\n");
        }

        Log.d("Response", "> " + line);
        return buffer.toString();

      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }finally {
        if (connection != null){
          connection.disconnect();
        }
        try {
          if(reader != null){
            reader.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return null;
    }

    @Override
    protected void onPostExecute(String result){
      super.onPostExecute(result);
//      Log.i("Result", Result);
//      return Result;

      try {
        JSONObject listaJson = new JSONObject(result);
        Log.i("meulog", listaJson.getString("knn_regression"));
        Log.i("meulog", listaJson.getString("linear_regression"));
        Log.i("meulog", listaJson.getString("quadratic_regression_2"));
        Log.i("meulog", listaJson.getString("quadratic_regression_3"));


        Intent intent = new Intent(getApplicationContext(), Result.class);

        txtSymbol = findViewById(R.id.edtSymbol);

        intent.putExtra("symbol", txtSymbol.getText().toString());
        intent.putExtra("knn_regression", listaJson.getString("knn_regression"));
        intent.putExtra("linear_regression", listaJson.getString("linear_regression"));
        intent.putExtra("quadratic_regression_2", listaJson.getString("quadratic_regression_2"));
        intent.putExtra("quadratic_regression_3", listaJson.getString("quadratic_regression_3"));
        finish();
        startActivity(intent);


      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

  }

}
