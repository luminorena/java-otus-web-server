package ru.otus.luminorena.web.server.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Storage {
    private static List<Item> items;
    private static final Logger logger = LogManager.getLogger(Storage.class.getName());

    public static void init() {
        logger.debug("Storage is initialized");
        items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            items.add(new Item("item " + i, 100 + (int) (Math.random() * 1000)));
        }
    }

    public static List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public static void save(Item item)  {
        item.setId(UUID.randomUUID());
        items.add(item);
    }

    public static void update(Item item) {
        boolean found = false;
        for (Item item1 : items) {
            if (item1.getId().equals(item.getId())) {
                item1.setTitle(item.getTitle());
                item1.setPrice(item.getPrice());
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException();
        }
    }

}



