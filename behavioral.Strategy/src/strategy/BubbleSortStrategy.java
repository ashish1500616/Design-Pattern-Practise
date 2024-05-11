package strategy;

public class BubbleSortStrategy implements SortStrategy {

    @Override
    public int[] sort(int[] dataset) {
        System.out.println("Sorting using bubble.");
        return dataset;
    }
}
