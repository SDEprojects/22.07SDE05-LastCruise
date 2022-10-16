package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GameItems {

    public static final Map<String, Item> GAME_ITEMS_HASHMAP = generateItems();

    private static Map<String, Item> generateItems() {

        Map<String, Item> mapOfAllItems = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();

        // try with resources to automatically close the file
        try ( InputStream jsonItems = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("json/items.json")){
            List<Item> itemsDecoded = mapper.readValue(jsonItems,
                new TypeReference<List<Item>>() {
                });

            for (var item : itemsDecoded) {
                mapOfAllItems.put(item.getName(), item);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return mapOfAllItems;
    }
}
