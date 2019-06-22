package com.ice.shamim.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_page extends AppCompatActivity  {

    AppCompatButton login_btn;
    private List<Student> listItems;
    EditText UserName,UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_btn = findViewById(R.id.buttonlogin);
        UserName = findViewById(R.id.username);
        UserID = findViewById(R.id.userID);
        listItems = new ArrayList<>();


        /*Parsing Data from server*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<List<Student>> call = api.getHeroes();


        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {

                final List<Student> student = response.body();


                /*Submission task*/

                login_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String UserNameInput = UserName.getText().toString().trim();
                        String UserIDInput = UserID.getText().toString().trim();

                        if(UserIDInput.equals("") || UserNameInput.equals("")){
                            Toast.makeText(Login_page.this, "Insert data", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            int flag=0;

                            for (int i = 0; i < student.size(); i++) {
                                String id = student.get(i).getStudent_id();
                                String username = student.get(i).getStudent_user().getUsername();

                                if(UserIDInput.equals(id) && UserNameInput.equals(username)){
                                    flag =1;
                                    startActivity(new Intent(Login_page.this,MainActivity.class));
                                    finish();
                                    break;
                                 }

                            }

                            if(flag==0){

                                Toast.makeText(Login_page.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                            }

                        }


                    }
                });



            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }




}
