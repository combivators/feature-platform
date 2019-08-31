package net.tiny.feature.demo;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Users {

	public static class User {
		public int id;
		public String name;
		public String username;
		public String email;
		public Address address;
		public String phone;
		public String website;
		public Company company;

	}

	public static class Address {
		public String street;
		public String suite;
		public String city;
		public String zipcode;
		public Geo geo;
	}

	public static class Geo {
		public double lat;
		public double lng;
	}

	public static class Company {
		public String name;
		public String catchPhrase;
		public String bs;
	}

	public ConcurrentMap<Integer, User> users = new ConcurrentHashMap<>();

	public Users addAll(Collection<User> list) {
		list.stream().forEach(u -> users.put(u.id, u));
		return this;
	}

	public User find(Integer id) {
		return users.get(id);
	}

	public List<Integer> keys() {
		return users.keySet()
				.stream()
				.collect(Collectors.toList());
	}

	public int size() {
		return users.size();
	}

	public boolean isEmpty() {
		return users.isEmpty();
	}

	Supplier<Users> supplier;
}
