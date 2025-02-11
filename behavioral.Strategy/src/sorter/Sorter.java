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
        System.out.println("Original array: " + arrayToString(dataset));
        
        if (dataset.length > 5) {
            System.out.println("Dataset is large, using Quick Sort strategy");
            sorterBig.sort(dataset);
        } else {
            System.out.println("Dataset is small, using Bubble Sort strategy");
            sorterSmall.sort(dataset);
        }
        
        System.out.println("Sorted array: " + arrayToString(dataset));
        System.out.println("-----------------------------------");
    }
    
    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
