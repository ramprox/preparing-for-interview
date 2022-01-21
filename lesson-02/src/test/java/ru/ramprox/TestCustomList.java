package ru.ramprox;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestCustomList {

    public abstract CustomList<Integer> getEmptyTestingList();

    public abstract CustomList<Integer> getTestingList(Collection<Integer> collection);

    public abstract <T> boolean isContentsEquals(List<T> expectedList, CustomList<T> testingList);

    @Test
    @DisplayName("Testing empty list after creating")
    @Order(1)
    public void testEmptyList() {
        CustomList<Integer> testingList = getEmptyTestingList();
        assertEquals(0, testingList.size());
    }

    @Test
    @DisplayName("Testing add item")
    @Order(2)
    public void testAdd() {
        ArrayList<Integer> expectedArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        CustomList<Integer> testingList = getEmptyTestingList();
        expectedArrayList.forEach(testingList::add);
        assertEquals(expectedArrayList.size(), testingList.size());
        assertTrue(isContentsEquals(expectedArrayList, testingList));
    }

    @Test
    @DisplayName("Testing size after adding")
    @Order(3)
    public void testSize() {
        CustomList<Integer> testingList = getEmptyTestingList();
        testingList.addAll(0, Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(5, testingList.size());
    }

    @ParameterizedTest
    @DisplayName("Testing add collection by index")
    @MethodSource("dataForAddAllCollectionByIndex")
    @Order(4)
    public void testAddAllByIndexCollection(CustomList<Integer> testingList, int index,
                                            List<Integer> addingCollection,
                                            List<Integer> expectedList) {
        testingList.addAll(index, addingCollection);
        assertEquals(expectedList.size(), testingList.size());
        assertTrue(isContentsEquals(expectedList, testingList));
    }

    public Stream<Arguments> dataForAddAllCollectionByIndex() {
        List<Arguments> out = new ArrayList<>();

        List<Integer> initList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> addingList = Arrays.asList(6, 7, 8, 9, 10);

        CustomList<Integer> testingList = getEmptyTestingList();
        List<Integer> expectedList = new ArrayList<>(addingList);
        out.add(Arguments.arguments(testingList, 0, addingList, expectedList));

        testingList = getEmptyTestingList();
        testingList.addAll(0, initList);
        expectedList = new ArrayList<>(initList);
        expectedList.addAll(0, addingList);
        out.add(Arguments.arguments(testingList, 0, addingList, expectedList));

        testingList = getEmptyTestingList();
        testingList.addAll(0, initList);
        expectedList = new ArrayList<>(initList);
        expectedList.addAll(4, addingList);
        out.add(Arguments.arguments(testingList, 4, addingList, expectedList));

        testingList = getEmptyTestingList();
        testingList.addAll(0, initList);
        expectedList = new ArrayList<>(initList);
        expectedList.addAll(2, addingList);
        out.add(Arguments.arguments(testingList, 2, addingList, expectedList));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForAddItemByIndex")
    @DisplayName("Testing adding item by index")
    public void testAddItemByIndex(CustomList<Integer> testingList, int index,
                                   Integer setItem, List<Integer> expectedList) {
        testingList.add(index, setItem);
        assertEquals(expectedList.size(), testingList.size());
        assertTrue(isContentsEquals(expectedList, testingList));
    }

    public Stream<Arguments> dataForAddItemByIndex() {
        List<Arguments> out = new ArrayList<>();

        List<Integer> initList = Arrays.asList(1, 2, 3, 4, 5);
        Integer addingItem = 100;

        CustomList<Integer> testingList = getTestingList(initList);
        List<Integer> expectedList = new ArrayList<>(initList);
        expectedList.add(0, addingItem);
        out.add(Arguments.arguments(testingList, 0, addingItem, expectedList));

        testingList = getTestingList(initList);
        expectedList = new ArrayList<>(initList);
        expectedList.add(4, addingItem);
        out.add(Arguments.arguments(testingList, 4, 100, expectedList));

        testingList = getTestingList(initList);
        expectedList = new ArrayList<>(initList);
        expectedList.add(2, addingItem);
        out.add(Arguments.arguments(testingList, 2, 100, expectedList));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForRemoveItem")
    @DisplayName("Testing remove item")
    public void testRemoveItem(CustomList<Integer> testingList, Integer removingItem,
                               List<Integer> expectedList) {
        testingList.remove(removingItem);
        assertEquals(expectedList.size(), testingList.size());
        assertTrue(isContentsEquals(expectedList, testingList));
    }

    public Stream<Arguments> dataForRemoveItem() {
        List<Arguments> out = new ArrayList<>();

        List<Integer> initList = Arrays.asList(1, 2, 3, 4, 5);

        CustomList<Integer> testingList = getTestingList(initList);
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        out.add(Arguments.arguments(testingList, 1, expectedList));

        testingList = getTestingList(initList);
        expectedList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        out.add(Arguments.arguments(testingList, 5, expectedList));

        testingList = getTestingList(initList);
        expectedList = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
        out.add(Arguments.arguments(testingList, 3, expectedList));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForRemoveRemoveByIndex")
    @DisplayName("Testing remove item by index")
    public void testRemoveByIndex(CustomList<Integer> testingList, int removingItemIndex,
                                  List<Integer> expectedList) {
        testingList.remove(removingItemIndex);
        assertEquals(expectedList.size(), testingList.size());
        assertTrue(isContentsEquals(expectedList, testingList));
    }

    public Stream<Arguments> dataForRemoveRemoveByIndex() {
        List<Arguments> out = new ArrayList<>();

        List<Integer> initList = Arrays.asList(1, 2, 3, 4, 5);

        CustomList<Integer> testingList = getTestingList(initList);
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        out.add(Arguments.arguments(testingList, 0, expectedList));

        testingList = getTestingList(initList);
        expectedList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        out.add(Arguments.arguments(testingList, 4, expectedList));

        testingList = getTestingList(initList);
        expectedList = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
        out.add(Arguments.arguments(testingList, 2, expectedList));
        return out.stream();
    }

    @Test
    @DisplayName("Testing get item by index")
    public void testGetByIndex() {
        CustomList<Integer> testingList = getEmptyTestingList();
        testingList.addAll(0, Arrays.asList(1, 2, 3, 4, 5));
        Integer receivedElement = testingList.get(0);
        assertEquals(5, testingList.size());
        assertEquals(1, receivedElement);
        receivedElement = testingList.get(4);
        assertEquals(5, testingList.size());
        assertEquals(5, receivedElement);
        receivedElement = testingList.get(2);
        assertEquals(5, testingList.size());
        assertEquals(3, receivedElement);
    }

    @Test
    @DisplayName("Testing set item by index")
    public void testSetByIndex() {
        CustomList<Integer> testingList = getEmptyTestingList();
        testingList.addAll(0, Arrays.asList(1, 2, 3, 4, 5));

        Integer previousItem = testingList.set(0, 100);
        assertEquals(5, testingList.size());
        assertEquals(1, previousItem);
        assertEquals(100, testingList.get(0));

        previousItem = testingList.set(4, 100);
        assertEquals(5, testingList.size());
        assertEquals(5, previousItem);
        assertEquals(100, testingList.get(4));

        previousItem = testingList.set(3, 100);
        assertEquals(5, testingList.size());
        assertEquals(4, previousItem);
        assertEquals(100, testingList.get(3));
    }
}
