package com.yermoon.dao;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * To change this template use File | Settings | File Templates.
 *
 * @author wangqing
 * @since 14-4-25 上午9:52
 */
public class TestInnerInterface implements Cache {
    /**
     * Return the cache name.
     */
    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Return the the underlying native cache provider.
     */
    @Override
    public Object getNativeCache() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Return the value to which this cache maps the specified key. Returns
     * <code>null</code> if the cache contains no mapping for this key.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this cache maps the specified key,
     *         or <code>null</code> if the cache contains no mapping for this key
     */
    @Override
    public ValueWrapper get(Object key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Associate the specified value with the specified key in this cache.
     * <p>If the cache previously contained a mapping for this key, the old
     * value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void put(Object key, Object value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Evict the mapping for this key from this cache if it is present.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    @Override
    public void evict(Object key) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Remove all mappings from the cache.
     */
    @Override
    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        Cache dd=new TestInnerInterface();
        ValueWrapper ss = new SimpleValueWrapper("ss");
        dd.get("ss");
    }
}
