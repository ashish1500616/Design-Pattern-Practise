package sorter;

import strategy.SortStrategy;

public class Sorter {
    private final SortStrategy sorterSmall;
    private final SortStrategy sorterBig;

    public Sorter(SortStrategy sorterSmall, SortStrategy sorterBig) {
        this.sorterSmall = sorterSmall;
        this.sorterBig = sorterBig;
    }

    public void sort(int[] dataset) {
        if (dataset.length > 5) {
            sorterBig.sort(dataset);
        } else {
            sorterSmall.sort(dataset);
        }
    }
}
