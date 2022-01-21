package ru.ramprox;

import java.util.Collection;
import java.util.List;

public class TestCustomArrayList extends TestCustomList {
    @Override
    public CustomList<Integer> getEmptyTestingList() {
        return new CustomArrayList<>();
    }

    @Override
    public <T> boolean isContentsEquals(List<T> expectedList, CustomList<T> testingList) {
        for(int i = 0; i < expectedList.size(); i++) {
            if(!expectedList.get(i).equals(testingList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CustomList<Integer> getTestingList(Collection<Integer> collection) {
        return new CustomArrayList<>(collection);
    }
}
