package org.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastServer {

    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        IMap<Integer, Person> map = hz.getMap("persons");

        for (int i = 0; i < 10_000; i++) {
            map.put(i, new Person("Person " + i));
        }
        System.out.println("10,000 Person objects put into the map.");

        for (int i = 0; i < 10_000; i++) {
            Person p = map.get(i);
            if (i % 2000 == 0) {
                System.out.println("Key " + i + ": " + p);
            }
        }

        hz.shutdown();
    }
}
