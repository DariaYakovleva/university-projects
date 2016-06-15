package ru.ifmo.md.colloquium2;

/**
 * Created by Daria on 11.11.2014.
 */
public class Person {
    public String name = null;
    public int cnt = 0;

    protected Person(String name, int cnt) {
        this.name = name;
        this.cnt = cnt;
    }
}
