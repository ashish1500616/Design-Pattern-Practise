package strategy;

public class BubbleSortStrategy implements SortStrategy {

    @Override
    public int[] sort(int[] dataset) {
        System.out.println("Sorting using bubble sort");
        int n = dataset.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (dataset[j] > dataset[j + 1]) {
                    // Swap elements
                    int temp = dataset[j];
                    dataset[j] = dataset[j + 1];
                    dataset[j + 1] = temp;
                }
            }
        }
        
        return dataset;
    }
}
