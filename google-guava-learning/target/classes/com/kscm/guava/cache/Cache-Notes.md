# Guava Cache

The Guava Cache is an incremental cache, in the sense that when you request an object from the cache, it checks to see if it already has the corresponding value for the supplied key. If it does, it simply returns it
(assuming it hasn’t expired). If it doesn’t already have the value, it uses a CacheLoader to fetch the value and then it stores the value in the cache and returns it. This way, the cache is growing as new values are
requested.

The only thing you need to provide is the implementation for the abstract load method of the CacheLoader class. This is the method that is used to fetch the value to be cached. You can do this as an anonymous class, but creating a new class that extends CacheLoader is the way I prefer to do it (it’s cleaner and helps when unit testing/mocking). Please note that this method is expected to never return null, or there is no way for the Cache to tell whether this record has not been fetched yet or if it’s value is just empty.

[Baeldung Tutorial on Guava Cache](https://www.baeldung.com/guava-cache)