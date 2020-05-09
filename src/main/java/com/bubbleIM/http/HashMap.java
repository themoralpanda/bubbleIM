package com.bubbleIM.http;

import java.util.Iterator;
import java.util.LinkedList;

public class HashMap<k,v> {

  private class Node {
    public k key;
    public v val;

    public Node(k key, v val) {
      this.key = key;
      this.val = val;
    }
  }

  private int totalNoOfElements;
  private int bucketLength;
  private LinkedList<Node>[] buckets;

  public HashMap() {
    //Set initial bucket Length to 8
    bucketLength = 8;
    buckets = new LinkedList[bucketLength];
    System.out.println("The length of the HashMap");
  }

  public void put(k key, v val) {
    int hash = key.hashCode();
    int mappedIndex = hash % this.bucketLength;
    if (buckets[mappedIndex] == null) {
      buckets[mappedIndex] = new LinkedList<Node>();
      buckets[mappedIndex].add(new Node(key, val));
    } else {
      buckets[mappedIndex].add(new Node(key, val));
    }
    totalNoOfElements++;
  }

  public v get(k key) {
    int targetHash =  key.hashCode();
    int targetIndex = targetHash % this.bucketLength;
    if(buckets[targetIndex] != null) {
      Iterator itr = buckets[targetIndex].iterator();
      while(itr.hasNext()) {
        Node current = (Node)itr.next();
        if(current.key == key)
          return current.val;
      }
    }
    return null;
  }

  public int getTotalNoOfElements() {
    return this.totalNoOfElements;
  }
}
