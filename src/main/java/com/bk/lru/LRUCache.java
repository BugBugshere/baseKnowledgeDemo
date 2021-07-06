package com.bk.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node() {
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private int capacity;
    private Map<Integer, Node> cache;

    //虚拟头结点
    private Node head;
    //虚拟尾节点
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * •若不存在，返回 -1。
     * •若存在，则 key 对应的节点 node 是最近使用的节点。将该节点移动到双向链表的头部，最后返回该节点的值即可。
     *
     * @param key
     * @return
     */
    public int get(int key) {
        //拿到结果，并把节点放到链表头
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        //将最近访问的节点移到头部
        this.movetoHead(node);
        return key;
    }

    private void movetoHead(Node node) {
        //先将node从原位置移除
        this.removeNode(node);
        //添加到头部
        this.addToHead(node);

    }

    private void addToHead(Node node) {
        //当前node的指针指向原来的头
        node.next = head.next;
        //虚拟头重新指向新头
        head.next = node;
        //node 新的next的prev指针指向node本身
        node.next.prev = node;
        //处理node的prev向
        node.prev = head;
    }

    private void removeNode(Node node) {
        //处理node的后部分
        node.prev.next = node.next;
        //处理node的前部分
        node.next.prev = node.prev;
    }

    /**
     * •若不存在，则创建一个新的 node 节点，放入哈希表中。然后在双向链表的头部添加该节点。接着判断双向链表节点数是否超过 capacity。若超过，则删除双向链表的尾部节点，并且删除哈希表中对应的项。
     * •若存在，则更新 node 节点的值，然后该节点移动到双向链表的头部。
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        //判断是否已经存在
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            // 将最近新增的数据放到头部
            this.addToHead(newNode);
            ++size;
            // 若数据量超过设定的最大容量，移除尾部（最不常访问）的节点数据
            if (size > capacity) {
                Node tail = this.removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            //更新数据
            node.value = value;
            //置顶
            this.addToHead(node);
        }
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }


}
