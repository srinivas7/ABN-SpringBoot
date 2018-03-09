package com.abn.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.abn.pojo.Image;
import com.abn.pojo.Images;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


public class JSONRead {
	ObjectMapper mapper = new ObjectMapper();
    Gson g = new Gson();
    ArrayList imagesString;
    List images = new ArrayList<Image>();
    HashMap<Long, Image> hmap = new HashMap<Long, Image>();
    Image image;
    ArrayList<Image> imagesArray = new ArrayList<Image>();
    public ArrayList<Image> readJSONFile(String fileNamePath) {


        try {
        	Images imagesObj =   mapper.readValue(new FileReader(fileNamePath),Images.class);
        	imagesString = imagesObj.getImages();
        	Iterator ite = imagesString.iterator();
        	
        	while(ite.hasNext()) {
        		Object element = ite.next();
        		JsonReader reader = new JsonReader(new StringReader(element.toString()));
        		reader.setLenient(true);
        		image = g.fromJson(reader, Image.class);
                String str = image.getUrl();
                str = str.replace(',', '?');
                str = str.replace("'", "/");
                str = str.replace(',', '?');
                image.setUrl(str);
                imagesArray.add(image);
                hmap.put(image.getId(),image);
        	}

        	//System.out.println(hmap.get((long)1000));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return imagesArray;

    }

}
