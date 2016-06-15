package ru.ifmo.md.colloquium3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DummyContent {
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    public static int sizeD = 3;
    static {
        addItem(new DummyItem("1", "USD"));
        addItem(new DummyItem("2", "EUR"));
        addItem(new DummyItem("3", "GBP"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public int size() {
        return ITEM_MAP.size();
    }
    public void changeItem(String id, String value) {
        ITEM_MAP.get(id).content = value;
    }

    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
