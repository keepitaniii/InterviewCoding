package interviewcoding;

public class HashMapImplementation<K, V> {
  private static final int INITIAL_CAPACITY = 1 << 4; // 2^4 = 16
  private static final int MAX_CAPACITY = 1 << 30;

  public Node[] hashTable;

  public HashMapImplementation() {
    this.hashTable = new Node[INITIAL_CAPACITY];
  }

  public HashMapImplementation(int cap) {
    int tableSize = tableSizeFor(cap);
    this.hashTable = new Node[tableSize];
  }

  final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    return (n < 0) ? 1 : (n >= MAX_CAPACITY) ? MAX_CAPACITY : n + 1;
  }

  class Node<K, V> {
    public K key;
    public V value;
    public Node next;

    Node(K k, V v) {
      key = k;
      value = v;
    }
  }

  public void put (K k, V v) {
    int hash = k.hashCode()%hashTable.length;
    Node node = hashTable[hash];
    Node prev = null;
    while (node != null) {
      if (node.key == k) {
        node.value = v;
        return;
      }
      prev = node;
      node = node.next;
    }

    if (prev == null) {
      hashTable[hash] = new Node(k, v);
    } else {
      prev.next = new Node(k, v);
    }
  }

  public V get(K k) {
    int hash = k.hashCode()%hashTable.length;
    Node node = hashTable[hash];
    while (node != null) {
      if (node.key.equals(k)) {
        return (V) node.value;
      }
      node = node.next;
    }
    return null;
  }

  public static void main(String args[]) {
    HashMapImplementation<Integer, String> map = new HashMapImplementation<>();
    map.put(1, "hi");
    map.put(2, "Aniket");

    System.out.println(map.get(2));
  }
}
