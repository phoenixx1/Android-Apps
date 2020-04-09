/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    String userName;
    String password;
    EditText username, pass;
    Button check;
    ImageView head;
    TextView LogIn;
    Boolean signOrLog = true;
    RelativeLayout appBg;

    public void showUserList() {
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN) {
            signUp(view);
        }
        return false;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.textView) {
            if (signOrLog) {
                signOrLog = false;
                check.setText("Login");
                LogIn.setText("Sign Up");
                head.setImageResource(R.drawable.log);
            } else {
                signOrLog = true;
                check.setText("Sign Up");
                LogIn.setText("Login");
                head.setImageResource(R.drawable.signup);
            }
        } else if (view.getId() == R.id.appBgLayout || view.getId() == R.id.imageView) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void signUp(View view) {
        userName = username.getText().toString();
        password = pass.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            username.setError("Enter Username");
            Toast.makeText(MainActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            pass.setError("Enter Password");
            Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else {
            if (signOrLog) {

                ParseUser user = new ParseUser();

                user.setUsername(userName);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            Toast.makeText(MainActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            showUserList();

                        } else {

                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            } else {

                ParseUser.logInInBackground(userName, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null) {

                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            showUserList();

                        } else {

                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Server");

        username = (EditText) findViewById(R.id.userNameEditText);
        pass = (EditText) findViewById(R.id.passwordEditText);
        check = (Button) findViewById(R.id.button);
        LogIn = (TextView) findViewById(R.id.textView);
        head = (ImageView) findViewById(R.id.imageView);
        appBg = (RelativeLayout) findViewById(R.id.appBgLayout);

        head.setOnClickListener(this);
        appBg.setOnClickListener(this);

        LogIn.setOnClickListener(this);

        pass.setOnKeyListener(this);

        if (ParseUser.getCurrentUser() != null) {
            showUserList();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }


}


/*//To log out
        ParseUser.logOut();

        //check for current user
        if (ParseUser.getCurrentUser() != null) {
            Log.i("currentUser", "User: " + ParseUser.getCurrentUser().getUsername());
        } else {
            Log.i("currentUser", "User not logged in");
        }*/

        /*ParseUser.logInInBackground("nishant", "nahibtaunga", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.i("Log in", "Success");
                } else {
                    Log.i("Log in", "Fail " + e.toString());
                }
            }
        });*/


        /*ParseUser parseUser = new ParseUser();
        parseUser.setUsername("nishant");
        parseUser.setPassword("nahibtaunga");

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Sign up", "Success");
                } else {
                    Log.i("Sign up", "Fail");
                }
            }
        });*/


/*        ParseObject score = new ParseObject("Score");

        score.put("Username", "Nishant");
        score.put("Score", 99);
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("bg", "success");
                } else {
                    Log.i("bg", "fail error: " + e.toString());
                }
            }
        });*/


        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.getInBackground("OsBdd11jDs", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {

                    object.put("Score", 200);
                    object.saveInBackground();

                    Log.i("ObjectValue", object.getString("Username"));
                    Log.i("ObjectValue", Integer.toString(object.getInt("Score")));
                }
            }
        });*/

        /*ParseObject tweet = new ParseObject("Tweet");

        tweet.put("Username","NSTG");
        tweet.put("Tweet","Hi! sssuuppp");

        tweet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i("bg","success");
                }
                else{
                    Log.i("bg","fail");
                }
            }
        });*/

        /*ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Tweet");
        query.getInBackground("FldolXJ7I7", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    object.put("Tweet","Bye");
                    object.saveInBackground();
                    Log.i("ObjectValue", object.getString("Username"));
                    Log.i("ObjectValueTweet", object.getString("Tweet"));

                }
            }
        });*/


//To find the specific query
        /*query.whereEqualTo("Username","Nishant");
        query.setLimit(1);*/

//To increment the number by 50
        /*query.whereGreaterThanOrEqualTo("Score", 200);
                query.findInBackground(new FindCallback<ParseObject>() {
@Override
public void done(List<ParseObject> objects, ParseException e) {
        if (e == null && objects != null) {
        for (ParseObject object : objects) {
        object.put("Score", object.getInt("Score") + 50);
        object.saveInBackground();
        }
        }
        }
        });*/

        /*query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("findInBackground", "Retrieved " + objects.size() + " objects");
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            //Log.i("findInBackgroundResult", object.getString("Username")+":"+Integer.toString(object.getInt("Score")));
                            //Log.i("findInBg",Integer.toString(object.getInt("Score")));
                            *//*int temp = object.getInt("Score");

//To increment the score by 50
                            if (temp >= 200) {
                                object.put("Score", temp + 50);
                                object.saveInBackground();
                            }
                            Log.i("findInBackgroundResult", object.getString("Username") + ":" + Integer.toString(object.getInt("Score")));*//*

                        }
                    }
                }
            }
        });*/