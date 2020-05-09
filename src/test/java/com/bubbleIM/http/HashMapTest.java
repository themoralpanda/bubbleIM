package com.bubbleIM.http;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class HashMapTest {

  @Test
  public void testHashMap() {
    HashMap<String, String> testMap = new HashMap<>();
    testMap.put("k1", "v1");
    testMap.put("k2", "v2");
    assertEquals(testMap.get("k1"), "v1");
    assertEquals(testMap.get("k2"), "v2");
    assertNull(testMap.get("k3"));
  }

  @Test
  public void testHashMap2() {
    class Student {
      public String name;
      public int age;

      public Student(String name, int age) {
        this.name = name;
        this.age = age;
      }
    }

    class StudentKey {
      public String key;

      public StudentKey(String key) {
        this.key = key;
      }

      @Override
      public int hashCode() {
        return 0;
      }
    }
    HashMap<StudentKey, Student> testMap = new HashMap<>();

    StudentKey s1, s2, s3;
    s1 = new StudentKey("vig");
    s2 = new StudentKey("ram");
    s3 = new StudentKey("tam");

    testMap.put(s1, new Student("vig", 27));
    testMap.put(s2, new Student("ram", 28));
    testMap.put(s3, new Student("tam", 29));

    assertEquals(27, testMap.get(s1).age);
    assertEquals(28, testMap.get(s2).age);
    assertEquals(29, testMap.get(s3).age);
  }
}