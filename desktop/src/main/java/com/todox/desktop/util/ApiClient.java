package com.todox.desktop.util;

import com.google.gson.Gson;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

import java.net.URI;

public class ApiClient {
    public static <T> T getObject(String url, Class<T> aClass) {
        try (Client client = ClientBuilder.newClient()) {
            URI uri = URI.create(url);
            Response response = client.target(uri).request().get();
            return response.readEntity(aClass);
        }

    }

    public static <T> void saveObject(String url, T object) {
        try (Client client = ClientBuilder.newClient()) {
            URI uri = URI.create(url);
            try (Response response = client.target(uri).request().post(Entity.json(new Gson().toJson(object)))) {
                response.getStatus();
            }
        }
    }

    public static <T> void updateObject(String url, T object) {
        try (Client client = ClientBuilder.newClient()) {
            URI uri = URI.create(url);
            String obj = new Gson().toJson(object);
            try (Response response = client.target(uri).request().put(Entity.json(obj))) {
                response.getStatus();
            }
        }
    }

    public static void removeObject(String url) {
        try (Client client = ClientBuilder.newClient()) {
            URI uri = URI.create(url);
            try (Response response = client.target(uri).request().delete()) {
                response.getStatus();
            }
        }
    }
}
