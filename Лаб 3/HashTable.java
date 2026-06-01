import java.util.LinkedList;

public class HashTable<K, V> {
    // Вспомогательный класс для хранения пары
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int size = 0;
    private static final int CAPACITY = 10; // Начальный размер массива

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[CAPACITY];
    }

    // Хэш-функция для получения индекса
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // Метод добавления
    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; // Если ключ уже есть, обновляем значение
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;
    }

    // Метод получения
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null; // Ключ не найден
    }

    // Метод удаления
    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Entry<K, V> toRemove = null;
            for (Entry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    toRemove = entry;
                    break;
                }
            }
            if (toRemove != null) {
                table[index].remove(toRemove);
                size--;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}