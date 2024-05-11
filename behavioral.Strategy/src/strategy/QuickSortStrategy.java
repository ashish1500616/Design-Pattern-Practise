package strategy;

public class QuickSortStrategy implements SortStrategy {
    @Override
    public int[] sort(int[] dataset) {
        System.out.println("Sorting using quick sort");
        return dataset;
    }
}
