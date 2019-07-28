package com.example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Created by DINGJUN on 2019.07.28.
 */
public class JsonExample {

    @Test
    public void fastJsonTest1() {
        String js2 = "{\"name\":[{\"a\":\"b\",\"c\":\"d\"}],\"sex\":\"femel\",\"age\":25}";
        JSONObject parse = (JSONObject) JSONObject.parse(js2);
        System.out.println(parse);
        Object name = parse.get("name");
        System.out.println(name);
        JSONArray name1 = parse.getJSONArray("name");
        System.out.println(name1);
    }

    @Test // jackson translate json and object
    public void jacksonTest1() throws IOException {
        Person person = new Person("lin da", "man", 25);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println(jsonString);

        ObjectMapper objectMapper1 = new ObjectMapper();
        String js = "{\"name\":\"alen\",\"sex\":\"femel\",\"age\":25}";
        Person person1 = objectMapper1.readValue(js, Person.class);
        System.out.println(person1);
    }

    @Test
    public void gsonTest1() {
        Person person = new Person("lin da", "man", 25);
        Gson gson = new Gson();
        String s = gson.toJson(person);
        System.out.println(s);

        String js = "{\"name\":\"alen\",\"sex\":\"femel\",\"age\":25}";
        Gson gson1 = new Gson();
        Person person1 = gson1.fromJson(js, person.getClass());
        System.out.println(person1);

        String js2 = "{\"name\":[{\"a\":\"b\",\"c\":\"d\"}],\"sex\":\"femel\",\"age\":25}";
        JsonObject jsonObject = gson.fromJson(js2, JsonObject.class);
        System.out.println(jsonObject);
        JsonElement name = jsonObject.get("name");
        System.out.println(name);


    }
}

class Person {
    private String name;
    private String sex;
    private int age;

    public Person() {}

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + '\'' + ", sex='" + sex + '\'' + ", age=" + age + '}';
    }
}





















































