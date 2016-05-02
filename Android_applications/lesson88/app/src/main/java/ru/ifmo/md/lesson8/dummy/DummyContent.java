package ru.ifmo.md.lesson8.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.ifmo.md.lesson8.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static List<Image> images = new ArrayList<>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    public static int sizeD = 6;
    static {
        addItem(new DummyItem("1", "2123260"));
        addItem(new DummyItem("2", "2122265"));
        addItem(new DummyItem("3", "2099280"));
        addItem(new DummyItem("4", "2459115"));
        addItem(new DummyItem("5", "2442047"));
        images.add(new Image("Sunny", R.drawable.sunny, R.drawable.sunny3));
        images.add(new Image("Snow", R.drawable.snow, R.drawable.snow3));
        images.add(new Image("Rain", R.drawable.rain, R.drawable.rain3));
        images.add(new Image("Fair", R.drawable.fair, R.drawable.fair3));
        images.add(new Image("Cloudy", R.drawable.cloudy, R.drawable.cloudy3));
        images.add(new Image("Mist", R.drawable.mist, R.drawable.mist3));
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

    /**
     * A dummy item representing a piece of content.
     */
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
    public static class Image {
        String cur_weather;
        int r_icon;
        int r_image;
        public Image(String a, int b, int c) {
            cur_weather = a;
            r_icon = b;
            r_image = c;
        }
        public String getWeather() {
            return cur_weather;
        }
        public int getIcon() {
            return r_icon;
        }
        public int getImage() {
            return r_image;
        }
    }
}
