package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

  TextView knn;
  TextView linear;
  TextView quadratic2;
  TextView quadratic3;
  Button voltar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    knn = findViewById(R.id.txtKnn);
    linear = findViewById(R.id.txtLinear);
    quadratic2 = findViewById(R.id.txtQuadratic2);
    quadratic3 = findViewById(R.id.txtQuadratic3);

    knn.setText(getIntent().getExtras().getString("knn_regression"));
    linear.setText(getIntent().getExtras().getString("linear_regression"));
    quadratic2.setText(getIntent().getExtras().getString("quadratic_regression_2"));
    quadratic3.setText(getIntent().getExtras().getString("quadratic_regression_3"));

    voltar = findViewById(R.id.btnVoltar);

    voltar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);
      }
    });


  }
}