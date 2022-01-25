package ru.ramprox.list_tasks;

public class Main {
    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        for(int i = 1; i <= 10; i++) {
            list.add(i);
        }

        for (Integer item : list) {
            System.out.println(item);
        }

        System.out.println();

        list.reverse();

        for (Integer item : list) {
            System.out.println(item);
        }

        list.add(11);

        System.out.println();
        for (Integer item : list) {
            System.out.println(item);
        }
    }
}
