package com.example.demoproject.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PersonAdapter extends TypeAdapter<Person> {
    final Gson embedded = new Gson();
    public PersonAdapter() {
        super();
    }
    @Override
    public Person read(JsonReader reader) throws IOException {
        throw new UnsupportedOperationException();
    }
    @Override
    public void write(JsonWriter writer, Person person) throws IOException {
        writer.beginObject();

        writer.name("first");
        embedded.toJson(embedded.toJsonTree(person.getFirst()), writer);

        writer.endObject();
    }
}
