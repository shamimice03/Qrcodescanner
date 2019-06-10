package com.ice.shamim.qrcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class user_datails extends AppCompatActivity {

    private List<Student> listItems;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_datails);

        txt = (TextView) findViewById(R.id.text_tex);

        txt.setText("shamim");

        listItems = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<List<Student>> call = api.getHeroes();

       call.enqueue(new Callback<List<Student>>() {
           @Override
           public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {

               List<Student> student = response.body();


               for (int i=0;i < student.size();i++)
               {
                   Log.i("Value of element "+i,student.get(i).getStudent_id());
                   Log.i("Value of element "+i,student.get(i).getStudent_session());
                   Log.i("Value of element "+i,student.get(i).getStudent_user().getEmail());
                   Log.i("Value of element "+i,student.get(i).getStudent_user().getUsername());

               }


               Toast.makeText(user_datails.this, "Yes", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<List<Student>> call, Throwable t) {

           }
       });


    }


}
