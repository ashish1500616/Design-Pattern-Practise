import sorter.Sorter;
import strategy.BubbleSortStrategy;
import strategy.QuickSortStrategy;

public class Main {
    public static void main(String[] args) {
        // Create our strategy implementations
        BubbleSortStrategy bubbleSort = new BubbleSortStrategy();
        QuickSortStrategy quickSort = new QuickSortStrategy();
        
        // Create the context with our strategies
        Sorter sorter = new Sorter(bubbleSort, quickSort);
        
        // Test case 1: Small dataset (should use Bubble Sort)
        System.out.println("Test Case 1: Small Dataset");
        int[] smallDataset = {4, 1, 3, 2};
        sorter.sort(smallDataset);
        
        // Test case 2: Large dataset (should use Quick Sort)
        System.out.println("Test Case 2: Large Dataset");
        int[] bigDataset = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        sorter.sort(bigDataset);
        
        // Test case 3: Edge case - dataset size exactly 5
        System.out.println("Test Case 3: Edge Case (size 5)");
        int[] edgeDataset = {5, 4, 3, 2, 1};
        sorter.sort(edgeDataset);
    }
}