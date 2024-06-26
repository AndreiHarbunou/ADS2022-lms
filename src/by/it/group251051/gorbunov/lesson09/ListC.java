package by.it.group251051.gorbunov.lesson09;

import java.util.*;

public class ListC<E> implements List<E> {

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private static int DEFAULT_CAPACITY = 10;

    transient E[] elementData;

    private int size, capacity = DEFAULT_CAPACITY;

    private int newCapacity(int oldCapacity) {
        capacity = oldCapacity + (oldCapacity >> 1);
        return capacity;
    }

    private void grow(int oldCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity(oldCapacity));
    }

    public ListC() {
        elementData = (E[]) (new Object[capacity]);
        size = 0;
    }

    @Override
    public String toString() {
        String res = "[";

        if (!isEmpty()) {
            for (int i = 0; i < size - 1; i++)
                res += elementData[i].toString() + ", ";
            res += elementData[size - 1].toString();
        }
        return res + ']';
    }

    @Override
    public boolean add(E e) {
        if (size == capacity) {
            grow(size + 1);
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        E tmp = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, (size--) - index - 1);
        return tmp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (size == capacity) {
            grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1, (size++) - index);
        elementData[index] = element;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0)
            remove(index);
        return (index > 0);
    }

    @Override
    public E set(int index, E element) {
        E tmp = elementData[index];
        elementData[index] = element;
        return tmp;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i]))
                return i;
        }

        return -1;
    }

    @Override
    public E get(int index) {
        return elementData[index];
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i]))
                return true;
        }
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null)
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null)
                    return i;
            }
        else
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementData[i]))
                    return i;
            }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean res = true;
        for (Object obj :
                c.toArray())
            res = res && this.contains(obj);
        return res;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        while (c.size() + size > capacity) {
            grow(capacity);
        }
        System.arraycopy(c.toArray(), 0, elementData, size, c.size());
        size += c.size();
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        while (c.size() + size > capacity) {
            grow(capacity);
        }
        System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
        System.arraycopy(c.toArray(), 0, elementData, index, c.size());
        size += c.size();
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldSize = size;

        for (int i = 0; i < size; i++) {
            if (c.contains(elementData[i]))
                remove(i--);
        }
        return oldSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldSize = size;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elementData[i]))
                remove(i--);
        }
        return oldSize != size;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
