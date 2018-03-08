package com.abn.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.abn.pojo.Images;
import com.abn.pojo.State;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class JSONRead {

    public void readJSONFile(String fileNamePath) throws ParseException {

        JSONParser parser = new JSONParser();
        ObjectMapper mapper = new ObjectMapper();
        Object obj;
        State state = new State();

        try {
        	Images imagesObj =  mapper.readValue(new FileReader(fileNamePath),Images.class);
        	System.out.println(imagesObj);
        	System.out.println(imagesObj.getImage());
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
