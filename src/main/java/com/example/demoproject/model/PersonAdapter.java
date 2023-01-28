package com.example.demoproject.model;

import com.example.demoproject.util.DateUtil;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PersonAdapter extends TypeAdapter<Person> {
    public PersonAdapter() {
        super();
    }
    @Override
    public Person read(JsonReader reader) throws IOException {
        Person person = new Person();

        reader.beginObject();
        if ("firstName".equals(reader.nextName())) person.setFirstName(reader.nextString());
        if ("lastName".equals(reader.nextName())) person.setLastName(reader.nextString());
        if ("street".equals(reader.nextName())) person.setStreet(reader.nextString());
        if ("postalCode".equals(reader.nextName())) person.setPostalCode(reader.nextInt());
        if ("city".equals(reader.nextName())) person.setCity(reader.nextString());
        if ("birthday".equals(reader.nextName())) person.setBirthday(DateUtil.parse(reader.nextString()));
        reader.endObject();

        return person;
    }
    @Override
    public void write(JsonWriter writer, Person person) throws IOException {
        writer.beginObject();
        writer.name("firstName").value(person.getFirstName());
        writer.name("lastName").value(person.getLastName());
        writer.name("street").value(person.getStreet());
        writer.name("postalCode").value(person.getPostalCode());
        writer.name("city").value(person.getCity());
        writer.name("birthday").value(DateUtil.format(person.getBirthday()));
        writer.endObject();
    }
}
