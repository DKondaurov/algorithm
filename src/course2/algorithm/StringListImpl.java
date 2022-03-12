package course2.algorithm;

import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList{
    private String[] stringList;
    private int size;

    public StringListImpl() {
        this.stringList = new String[4];
    }

    @Override
    public String add(String item) {
        if (size >= stringList.length) {
            stringList = Arrays.copyOf(stringList, (stringList.length + (stringList.length + 4)));
        }
        stringList[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index <= size - 1 && index >= 0) {
            System.arraycopy(stringList, index, stringList, index + 1, size - index);
            stringList[index] = item;
            size++;
            return item;
        }
        throw new NotFoundException();
    }

    @Override
    public String set(int index, String item) {
        if (index <= size - 1 && index >= 0) {
            stringList[index] = item;
            return item;
        }
        throw new NotFoundException();
    }

    @Override
    public String remove(String item) {
        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i].equals(item)) {
                stringList[i] = null;
                if (i != stringList.length - 1) {
                    System.arraycopy(stringList, i + 1, stringList, i, size - 1);
                }
                size--;
                return item;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public String remove(int index) {
        String removeItem = stringList[index];
        stringList[index] = null;
        if (index != stringList.length - 1) {
            System.arraycopy(stringList, index + 1, stringList, index, size - index);
            size--;
            return removeItem;
        }
        throw new NotFoundException();
    }

    @Override
    public boolean contains(String item) {
        for (int i = 0; i < size; i++) {
            if (stringList[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index <= size && index>=0) {
            return stringList[index];
        }
        throw new NotFoundException();
    }

    @Override
    public boolean equals(StringList otherList) {
        return stringList.equals(otherList);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return stringList.length == 1 && stringList[0] == null;
    }

    @Override
    public void clear() {
        stringList = new String[4];
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] arr = new String[size];
        System.arraycopy(stringList, 0, arr, 0, size);
        return arr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringListImpl that = (StringListImpl) o;
        return size == that.size && Arrays.equals(stringList, that.stringList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(stringList);
        return result;
    }
}
