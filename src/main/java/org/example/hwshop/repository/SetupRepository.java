package org.example.hwshop.repository;

import org.example.hwshop.domain.Setup;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SetupRepository {

    private final AtomicLong counter = new AtomicLong(100);
    private final Map<Long, Setup> store = new ConcurrentHashMap<>();

    public Setup save(Setup setup) {
        long id = counter.incrementAndGet();
        setup.setId(id);
        store.put(id, setup);
        return setup;
    }

    public Optional<Setup> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void clear() {
        store.clear();
    }
}
