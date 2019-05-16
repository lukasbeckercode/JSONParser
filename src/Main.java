/*
This project is able to read data from a Json File
It needs a shitton of external Libs:
    gson
    okhttp
    okio
    kotlin-stdlib

The second Class(myPojo) is auto-generated using this website:
http://pojo.sodhanalibrary.com/

The YouTube Video this Project is based on can be found here:
https://www.youtube.com/watch?v=Vqgghm9pWe0

The Json-File used here is created using a nodeJS app
It runs on my personal machine and can be accessed as follows:
open cmd
cd C:\Users\Lukas\source\repos\NodeTestApp1\NodeTestApp1
node app.js

Exit: Strg+C
*/

//get all the imports
import com.google.gson.*;
import okhttp3.*;
import parser.json.MyPojo;

import java.io.IOException;


class Main {


    private static final OkHttpClient client = new OkHttpClient(); //create a new client, call it "client"!

    public static void main(String[] args) {
        for(String str :getQuotes()) //print the entire array
        {
            System.out.println(str);
        }
    }

    //USE THIS ENTIRE METHOD; DO NOT CHANGE:
    private static  String getJson(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            //noinspection ConstantConditions
            return response.body().string();
        }
    }

    //This is the fun part:
    private static String[] getQuotes(){ //create a String Array Method
        String json = null; //empty string
        String url = "http:192.168.1.114:8080/api/quote" ; //Address where the Json is,FORMAT: http:localhost:8080/api/quote
        try {
            json = getJson(url);
        } catch (Exception e){
            e.printStackTrace();
        }
        Gson gson  = new Gson(); // create this thing
        MyPojo myPojo = gson.fromJson(json,MyPojo.class); //MyPojo is the second class, we are accessing it here
        return new String[]{ //return Statement
                "Quote: " + myPojo.getQoute(), //these methods are created automatically, see link in project description
                "By: " + myPojo.getBy()
        };
    }
}
