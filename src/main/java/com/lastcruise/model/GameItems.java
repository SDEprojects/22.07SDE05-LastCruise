package com.lastcruise.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GameItems {
    public static final Map<String, Item> GAME_ITEMS_HASHMAP = generateItems();

    private static Map<String, Item> generateItems() {
            ObjectMapper mapper = new ObjectMapper();
            File jsonItems = new File("src/main/java/com/lastcruise/model/items.json");

            Map<String, Item> mapOfAllItems = new HashMap<>();
            try {
                List<Item> itemsDecoded = mapper.readValue(jsonItems,
                    new TypeReference<List<Item>>(){});

                for (var item : itemsDecoded){
                    mapOfAllItems.put(item.getName(), item);
                }
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
            return mapOfAllItems;
        }
    }
