package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Result extends AppCompatActivity {

  TextView knn;
  TextView linear;
  TextView quadratic2;
  TextView quadratic3;
  Button voltar;
  ImageView graph;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    knn = findViewById(R.id.txtKnn);
    linear = findViewById(R.id.txtLinear);
    quadratic2 = findViewById(R.id.txtQuadratic2);
    quadratic3 = findViewById(R.id.txtQuadratic3);

    graph = findViewById(R.id.imgGraph);

    knn.setText(getIntent().getExtras().getString("knn_regression"));
    linear.setText(getIntent().getExtras().getString("linear_regression"));
    quadratic2.setText(getIntent().getExtras().getString("quadratic_regression_2"));
    quadratic3.setText(getIntent().getExtras().getString("quadratic_regression_3"));

//    Picasso.with(getApplicationContext()).load("https://stock-evaluate-api.herokuapp.com/grafico").memoryPolicy(MemoryPolicy.NO_CACHE).into(graph);

    Picasso.with(getApplicationContext()).load("https://stock-evaluate-api.herokuapp.com/grafico")
            .memoryPolicy(MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(graph);



    voltar = findViewById(R.id.btnVoltar);

    voltar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Picasso.with(getApplicationContext()).invalidate("https://stock-evaluate-api.herokuapp.com/grafico");

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);
      }
    });


  }

}
