package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lastcruise.Main;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AllSounds {

    public static final Map<String, String> ALL_SOUNDS = generateSounds();

    private static Map<String, String> generateSounds() {

        Map<String, String> soundsMap = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try (InputStream jsonMusic = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("json/music.json")) {
            soundsMap = mapper.readValue(jsonMusic,
                new TypeReference<Map<String, String>>() {
                });
        } catch (Exception e) {
            System.out.println(e);
        }
        return soundsMap;
    }
}
