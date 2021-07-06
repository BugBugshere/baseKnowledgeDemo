package com.bk.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheV2<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCacheV2(int capacity) {
        super(capacity, .75f, true);
        this.capacity = capacity;
    }

    /**
     * 如果map中的数据量大于设定的最大容量，返回true，再新加入对象时删除最老的数据
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当 map中的数据量大于指定的缓存个数的时候，自动移除最老的数据
        return size() > capacity;
    }
}
