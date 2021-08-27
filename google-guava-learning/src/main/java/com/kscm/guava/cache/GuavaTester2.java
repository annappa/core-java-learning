package com.kscm.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GuavaTester2 {
    /*
        1. How to Use Guava Cache....:
        Let's cache the uppercase form of String instances.
        First, we'll create the CacheLoader – used to compute the value stored in the cache. From this, we'll use the handy CacheBuilder to build our cache using the given specifications:

        Notice how there is no value in the cache for our “hello” key – and so the value is computed and cached.
        Also note that we're using the getUnchecked() operation – this computes and loads the value into the cache if it doesn't already exist.
     */
    @Test
    public void whenCacheMiss_thenValueIsComputed() {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().build(loader);

        assertEquals(0, cache.size());
        assertEquals("HELLO", cache.getUnchecked("hello"));
        assertEquals(1, cache.size());
    }


    /*
        2. Eviction Policies
            Every cache needs to remove values at some point. Let's discuss the mechanism of evicting values out of the cache – using different criteria.

        2.1. Eviction by Size
            We can limit the size of our cache using maximumSize(). If the cache reaches the limit, the oldest items will be evicted.
            In the following code, we limit the cache size to 3 records:
     */
    @Test
    public void whenCacheReachMaxSize_thenEviction() {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };
        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().maximumSize(3).build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("forth");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("FORTH", cache.getIfPresent("forth"));
    }

    /*
        2.2 Eviction by Weight
            We can also limit the cache size using a custom weight function. In the following code, we use the length as our custom weight function
            Note: The cache may remove more than one record to leave room for a new large one.
     */
    @Test
    public void whenCacheReachMaxWeight_thenEviction() {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        Weigher<String, String> weighByLength;
        weighByLength = new Weigher<String, String>() {
            @Override
            public int weigh(String key, String value) {
                return value.length();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .maximumWeight(16)
                .weigher(weighByLength)
                .build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last"); // To add this item, it may remove one or more record from the cache
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("LAST", cache.getIfPresent("last"));
    }


    /*
        2.3 Eviction by Time
            Beside using size to evict old records, we can use time. In the following example, we customize our cache to remove records that have been idle for 2ms:
     */
    @Test
    public void whenEntryIdle_thenEviction()
            throws InterruptedException {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.MILLISECONDS)
                .build(loader);

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());

        cache.getUnchecked("hello");
        Thread.sleep(300);

        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    /*
        We can also evict records based on their total live time. In the following example, the cache will remove the records after 2ms of being stored:
     */
    @Test
    public void whenEntryLiveTimeExpire_thenEviction()
            throws InterruptedException {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2,TimeUnit.MILLISECONDS)
                .build(loader);

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());
        Thread.sleep(300);
        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }
}
