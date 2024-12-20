package Utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;


    public class MyArrayList<T> implements MyList<T> ,Iterable<T>{
        private T[] array;

        private int cursor; // присвоено значение по умолчанию = 0;

        @SuppressWarnings("unchecked")
        public MyArrayList() {
            array = (T[]) new Object[10]; // Инициализация массива с размером 10
            cursor = 0; // Инициализация курсора
        }

        @SuppressWarnings("unchecked")
        public MyArrayList(T[] array) {
            if (array == null || array.length == 0) {
                this.array = (T[]) new Object[10];
            } else {
                this.array = (T[]) new Object[array.length * 2];
                addAll(array);
            }
        }

        // Добавление в массив одного элемента
        public void add(T value) {


            // Проверка. Есть ли вообще свободное место во внутреннем массиве
            // Если места нет - нужно добавить место
            if (cursor == array.length - 1) {
                // Расширить массив перед добавлением нового элемента
                expandArray();
            }

            array[cursor] = value;
            cursor++;
        }

        @Override
        public void addAll(T... numbers) {
            // с numbers я могу обращаться точно также, как со ссылкой на массив int
            // System.out.println("Приняли несколько интов. А именно: " + numbers.length);
            // System.out.println("Есть индекс у каждого инта, как в массиве. По индексом 0: " + numbers[0]);
            for (int i = 0; i < numbers.length; i++) {
                add(numbers[i]);
            }
        }


        // Динамическое расширение массива
        private void expandArray() {
           //8 System.out.println("Расширяем массив! Курсор = " + cursor);
        /*
        1. создать новый массив бОльшего размера (в 2 раза больше)
        2. Переписать в новый массив все значения из старого (до курсора)
        3. Перебросить ссылку
         */

            // 1
            T[] newArray = (T[]) new Object[array.length * 2];

            // 2
            for (int i = 0; i < cursor; i++) {
                newArray[i] = array[i]; // Переписываю все значения из старого массива в новый
            }

            // Перебрасываем ссылку. Переменная array хранит ссылку на "новый" массив
            array = newArray;
            //System.out.println("Расширение массива завершено");
        }

        // Возвращает строковое представление массива
        // [1, 14, 16]
        public String toString() {

            if (cursor == 0) return "[]";

            String result = "[";
            for (int i = 0; i < cursor; i++) {
                result = result + array[i] + (i < cursor - 1 ? ", " : "]"); //", " / "]"
            }

            return result;
        }


        // Текущее количество элементов в массиве
        public int size() {
            return cursor;
        }

        // Возвращает значение по индексу
        @Override
        public T get(int index) {
            if (index >= 0 && index < cursor) {
                return array[index];
            }
            // Написать код, если индекс "не корректный"
            return null; //

        }

        // Удаление элемента по индексу
        public T remove(int index) {
        /*
        1. Проверка индекса на валидность
        2. Удалить значение по индексу
        3. Передвинуть курсор (т.к. кол-во элементов уменьшилось)
        4. Вернуть старое значение
        */

            if (index >= 0 && index < cursor) {
                // Логика удаления
                T value = array[index]; // значение, которое я должен вернуть

                // Перебираем элементы начиная с индекса и перезаписываем значениями из соседней правой ячейки
                for (int i = index; i < cursor - 1; i++) { // граница перебора индексов?
                    array[i] = array[i + 1];
                }
                cursor--;

                return value; // возвращаем старое значение

            } else {
//           Индекс не валидный
                return null;
            }
        }

        // Удаление элемента по значению
        @Override
        public boolean remove(T value) {
        /*
        1. Есть ли элемент с таким значение в массиве - indexOf
        2. Если элемента нет - вернуть false
        3. Если элемент есть - удалить и вернуть true - вызвать удаление по индексу
         */
            int index = indexOf(value);
            if (index == -1) return false;

            remove(index);
            return true;
        }

        @Override
        public boolean contains(T value) {
            // 3 >= 0 -> true (элемент найден) | -1 >= 0 -> false (не содержится)
            return indexOf(value) >= 0;

//        int index = indexOf(value);
//        if (index >= 0) {
//            // Индекс, который вернулся больше нуля - элемент найден
//            return true;
//        }
//        else {
//            // index меньше нуля (минус 1), значит такое значение не найдено = не содержится в нашем массиве
//            return false;
//        }
        }

        // Перезаписывает значение по указанному индексу
        @Override
        public void set(int index, T value) {
            if (index >= 0 && index < cursor) {
                // Если индекс "правильный" присваиваем новое значение
                array[index] = value;
            }
            // Если нет, ничего не делаем
        }

        // Является ли коллекция пустой
        @Override
        public boolean isEmpty() {
            // Если курсор равен 0 - значит у нас нет элементов во внутреннем массиве
            return cursor == 0;
        }

        // Поиск по значению. Первое вхождение
        // {1, 100, 5, 5, 100} -> 100 метод вернет индекс первого найдено вхождения = 1
        public int indexOf(T value) {
            for (int i = 0; i < cursor; i++) {
                if (array[i].equals(value)) {
                    return i;
                }
            }
            return -1;
        }

        // Метод поиска по значению. Поиск последнего вхождения
        // Возвращает индекс последнего вхождения значения в массиве
        // {1, 100, 5, 5, 100} -> 100 метод вернет индекс последнего найдено вхождения = 4
        public int lastIndexOf(T value) {

            for (int i = cursor - 1; i >= 0; i--) {
                if (array[i].equals(value)) {
                    return i;
                }
            }

            return -1;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T[] toArray() {
            return Arrays.copyOf(array, cursor); // Возвращаем массив с текущими элементами
        }

        @Override
        public Iterator<T> iterator() {
            return new MyIterator(); // Возвращаем новый итератор
        }

        @Override
        public void sort(Comparator<T> comparator) {
            if (cursor <= 1) {
                return; // Ничего не делаем, если массив пуст или содержит один элемент
            }

            T[] sortedArray = (T[]) Arrays.copyOf(array, cursor); // Копируем массив
            Arrays.sort(sortedArray, comparator); // Сортируем копию
            System.arraycopy(sortedArray, 0, array, 0, cursor); // Копируем отсортированные элементы обратно
        }

          private class MyIterator implements Iterator<T>{

            int currentIndex = 0;
            //boolean hasNext(); to check if there is next element

            @Override
            public boolean hasNext() {
                return currentIndex < cursor;
            }

            @Override
            public T next() {
                T value = array[currentIndex];
                currentIndex++;
                return value;

                //return array[currentIndex];
            }
        }
    }

