package com.jd.ihosp.common.strategy.flow.pipeline;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Title: 使用LinkedHashMap实现LRU算法
 * Description: LinkedHashMap基于HashMap,Entry除了存放hashCode,key,value,next，还有一个before\after节点(Entry)
 * 由accessOrder控制按遍历顺序读、插入顺序读 accessOrder为boolean值类型，true为便利顺序，false为插入顺序 默认false
 * 注意这里的recordAccess方法，如果链表中元素的排序规则是按照插入的先后顺序排序的话，该方法什么也不做（accessOrder为false）；
 * 如果链表中元素的排序规则是按照访问的先后顺序排序的话，则将e移到链表的末尾处（accessOrder为true）
 *
 * @author :cmj
 */
public class LRU<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {

    private static final long serialVersionUID = 1L;

    public LRU(int initialCapacity,
               float loadFactor,
               boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * @param eldest
     * @return
     * @description 重写LinkedHashMap中的removeEldestEntry方法，当LRU中元素多余6个时，
     * 删除最不经常使用的元素
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        // TODO Auto-generated method stub
        if (size() > 6) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        LRU<Character, Integer> lru = new LRU<>(16, 0.75f, true);

        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        //get()会把 h 放置链表尾端
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        //所以这里打印的最后是 h
        System.out.println("LRU ：" + lru);
    }
}
