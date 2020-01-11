package net.tiny.feature.matching;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import net.tiny.feature.demo.Users;
import net.tiny.feature.matching.api.Entry;

public class EntryGenerator {

	private final Users users;
	private final List<Integer> ids;
	private final AtomicInteger index;

	public EntryGenerator(Users users) {
		this.users = users;
		this.ids = users.keys();
		Collections.shuffle(ids);
		this.index = new AtomicInteger();
	}

	public Entry create() {
    	int size = ThreadLocalRandom.current().nextInt(30, 64);
    	int id = ids.get(index.getAndIncrement() % ids.size());
    	Entry entry = new Entry();
    	entry.width = size;
    	entry.height = size;
    	entry.url = entry.url + id;
    	entry.tooltip = users.find(id).name;
    	return entry;
    }
}
