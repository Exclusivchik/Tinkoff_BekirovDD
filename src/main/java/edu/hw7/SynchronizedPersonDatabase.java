package edu.hw7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private static final Object OBJECT = new Object();
    private static final Map<Integer, Person> ID_MAP = new HashMap<>();
    private static final Map<String, List<Person>> NAME_MAP = new HashMap<>();
    private static final Map<String, List<Person>> ADDRESS_MAP = new HashMap<>();
    private static final Map<String, List<Person>> PHONE_NUMBER_MAP = new HashMap<>();

    @Override
    public void add(Person person) {
        synchronized (OBJECT) {
            ID_MAP.put(person.id(), person);

            NAME_MAP.putIfAbsent(person.name(), new ArrayList<>());
            var nameList = NAME_MAP.get(person.name());
            nameList.add(person);
            NAME_MAP.put(person.name(), nameList);

            ADDRESS_MAP.putIfAbsent(person.address(), new ArrayList<>());
            var addressList = ADDRESS_MAP.get(person.address());
            nameList.add(person);
            ADDRESS_MAP.put(person.address(), addressList);

            PHONE_NUMBER_MAP.putIfAbsent(person.phoneNumber(), new ArrayList<>());
            var phoneNumberList = PHONE_NUMBER_MAP.get(person.phoneNumber());
            phoneNumberList.add(person);
            PHONE_NUMBER_MAP.put(person.phoneNumber(), phoneNumberList);
        }
    }

    @Override
    public void delete(int id) {
        Person person = ID_MAP.get(id);
        synchronized (OBJECT) {
            if (ID_MAP.containsKey(person.id())) {
                ID_MAP.remove(person.id());
            } else {
                throw new RuntimeException();
            }
            NAME_MAP.put(person.name(), NAME_MAP.get(person.name())
                .stream().filter(person1 -> person1.id() != id).toList());
            ADDRESS_MAP.put(person.address(), ADDRESS_MAP.get(person.address())
                .stream().filter(person1 -> person1.id() != id).toList());
            PHONE_NUMBER_MAP.put(person.phoneNumber(), PHONE_NUMBER_MAP.get(person.phoneNumber())
                .stream().filter(person1 -> person1.id() != id).toList());
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (OBJECT) {
            if (NAME_MAP.get(name) == null) {
                return new ArrayList<>();
            }
            return NAME_MAP.get(name);
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        synchronized (OBJECT) {
            if (ADDRESS_MAP.get(address) == null) {
                return new ArrayList<>();
            }
            return ADDRESS_MAP.get(address);
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        synchronized (OBJECT) {
            if (PHONE_NUMBER_MAP.get(phone) == null) {
                return new ArrayList<>();
            }
            return PHONE_NUMBER_MAP.get(phone);
        }
    }
}
