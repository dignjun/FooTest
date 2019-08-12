package example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.IOException;

public class JsonParseTest {
    @Test
    public void fastJsonTest1() {
        String js2 = "{\"name\":[{\"a\":\"b\",\"c\":\"d\"}],\"sex\":\"femel\",\"age\":25}";
        JSONObject parse = (JSONObject) JSONObject.parse(js2);
        System.out.println(parse);
        Object name = parse.get("name");
        System.out.println(name);
        JSONArray name1 = parse.getJSONArray("name");
        System.out.println(name1);

        String js3 = "[{\"a\":\"b\",\"c\":\"d\"}]";
        JSONArray jsonArray = JSONObject.parseArray(js3);
        System.out.println(jsonArray);

        System.out.println("----------");
        long before = System.currentTimeMillis();
        for(int i = 0; i < getLoopCount(); i ++) {
            JSONObject.parseArray(getJsonString());
        }
        System.out.println("timeTotal: " + (System.currentTimeMillis() - before));
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

        String js2 = "[{\"a\":\"b\",\"c\":\"d\"}]";
        JsonNode jsonNode = objectMapper.readValue(js2, JsonNode.class);
        System.out.println(jsonNode);

        System.out.println("----------");
        long before = System.currentTimeMillis();
        for(int i = 0; i < getLoopCount(); i ++) {
            objectMapper.readValue(getJsonString(), JsonNode.class);
        }
        System.out.println("timeTotal: " + (System.currentTimeMillis() - before));
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

        String js3 = "[{\"a\":\"b\",\"c\":\"d\"}]";
        JsonArray jsonArray = gson.fromJson(js3, JsonArray.class);
        System.out.println(jsonArray);

        System.out.println("----------");
        long before = System.currentTimeMillis();
        for(int i = 0; i < getLoopCount(); i ++) {
            JsonArray jsonArray1 = gson.fromJson(getJsonString(), JsonArray.class);
            if(i == 500) {
                System.out.println(jsonArray1);
            }
        }
        System.out.println("timeTotal: " + (System.currentTimeMillis() - before));

    }

    public String getJsonString(){
        String str = "[{\"a\":\"b\",\"c\":\"d\"},{\"1\":\"2\",\"3\":4,\"5\":true}]";
        return str;
    }

    public int getLoopCount(){
        return 1000;
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
