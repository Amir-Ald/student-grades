

package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    final String FILE_NAME = "user.json";
    final String PREF_FILE_NAME = "my_Prefs";
    TextView nameTV;
    TextView ageTV;
    TextView isStudentTV;
    TextView scoreTV;
    TextView mathTV;
    TextView progTV;
    String data = "";
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTV = findViewById(R.id.nameTV);
        ageTV= findViewById(R.id.ageTV);
        isStudentTV= findViewById(R.id.isStudentTV);
        scoreTV=findViewById(R.id.scoreTV) ;
        mathTV=findViewById(R.id.mathTV) ;
        progTV=findViewById(R.id.progTV) ;

        viewModel= new ViewModelProvider(this).get(MyViewModel.class);

        nameTV.setText(viewModel.getName());
        ageTV.setText(Integer.toString(viewModel.getAge()));
        isStudentTV.setText(Boolean.toString(viewModel.getStudent()));
        mathTV.setText(Float.toString(viewModel.getMath()));
        progTV.setText(Float.toString(viewModel.getProgramming()));
        scoreTV.setText("Score");

    }


    public void loadData(View view) {


                StringBuilder stringBuilder = new StringBuilder();

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(FILE_NAME)));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    data = stringBuilder.toString();


                    JSONObject jObject = new JSONObject(data);
                    JSONArray jScore = jObject.getJSONArray("scores");


                    SharedPreferences sharedPreferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("name", jObject.getString("name"));
                    editor.putInt("age", jObject.getInt("age"));
                    editor.putBoolean("isStudent", jObject.getBoolean("isStudent"));

                    editor.putFloat("Math", (float) jScore.getJSONObject(0).getDouble("Math"));
                    editor.putFloat("Programming", (float) jScore.getJSONObject(1).getDouble("Programming"));
                    editor.commit();

                    Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();

                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                }


    }

    public void readData(View view) {

        SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

        viewModel.setName(preferences.getString("name", "No Name"));
        viewModel.setAge(preferences.getInt("age", 0));
        viewModel.setStudent(preferences.getBoolean("isStudent", false));
        viewModel.setProgramming(preferences.getFloat("Programming", 0));
        viewModel.setMath(preferences.getFloat("Math", 0));

        nameTV.setText(viewModel.getName());
        ageTV.setText(Integer.toString(viewModel.getAge()));
        isStudentTV.setText(Boolean.toString(viewModel.getStudent()));
        mathTV.setText(Float.toString(viewModel.getMath()));
        progTV.setText(Float.toString(viewModel.getProgramming()));
        scoreTV.setText("Score");

    }
}