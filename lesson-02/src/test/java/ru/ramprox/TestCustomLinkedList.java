package ru.ramprox;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TestCustomLinkedList extends TestCustomList {

    @Override
    public CustomList<Integer> getEmptyTestingList() {
        return new CustomLinkedList<>();
    }

    @Override
    public <T> boolean isContentsEquals(List<T> expectedList, CustomList<T> testingList) {
        Iterator<T> expectedListIterator = expectedList.iterator();
        Iterator<T> testingListIterator = testingList.iterator();
        while(expectedListIterator.hasNext()) {
            T expectedItem = expectedListIterator.next();
            T testingItem = testingListIterator.next();
            if(!expectedItem.equals(testingItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CustomList<Integer> getTestingList(Collection<Integer> collection) {
        return new CustomLinkedList<>(collection);
    }
}
