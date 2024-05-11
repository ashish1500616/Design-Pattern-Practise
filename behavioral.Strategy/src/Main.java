import sorter.Sorter;
import strategy.BubbleSortStrategy;
import strategy.QuickSortStrategy;

public class Main {
    public static void main(String[] args) {
        int[] smallDataset = {1, 3, 4, 2};
        int[] bigDataset = {1, 4, 3, 2, 8, 10, 5, 6, 9, 7};
        Sorter sorter = new Sorter(new BubbleSortStrategy(), new QuickSortStrategy());
        sorter.sort(smallDataset);
        sorter.sort(bigDataset);
    }
}