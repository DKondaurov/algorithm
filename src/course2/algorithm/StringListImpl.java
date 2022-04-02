package course2.algorithm;

import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList {
    private String[] storage;
    private int size;

    public StringListImpl() {
        this.storage = new String[4];
    }

    @Override
    public String add(String item) {
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, (storage.length + (storage.length + 4)));
        }
        storage[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateIndex(index);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }


    @Override
    public String set(int index, String item) {
        validateIndex(index);
        storage[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (indexOf(item) != -1) {
            System.arraycopy(storage, indexOf(item) + 1, storage, indexOf(item), size - 1);
            size--;
            return item;
        }
        throw new NotFoundException();
    }

    @Override
    public String remove(int index) {
        String removeItem = storage[index];
        storage[index] = null;
        if (index != storage.length - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
            return removeItem;
        }
        throw new NotFoundException();
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(storage, otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        storage = new String[4];
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] arr = new String[size];
        System.arraycopy(storage, 0, arr, 0, size);
        return arr;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(storage);
        return result;
    }

    private void validateIndex(int index) {
        if (index >= size - 1 && index <= 0) {
            throw new NotFoundException();
        }
    }
}
