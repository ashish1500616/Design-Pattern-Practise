### Interfaces and Classes:

- **SortStrategy**: Interface defining the contract for different sorting strategies.
- **BubbleSortStrategy, QuickSortStrategy**: Concrete classes implementing SortStrategy interface, providing specific
  sorting algorithms.

### Sorter Class:

- **Sorter**: Class responsible for sorting based on the size of the dataset. It has two SortStrategy instances:
  sorterSmall for small datasets and sorterBig for large datasets. The sort method checks the dataset size and delegates
  sorting to the appropriate strategy.

### Main Class:

- **Main**: Entry point of the application. It creates instances of datasets (smallDataset and bigDataset) and the
  Sorter class. It then calls the sort method of the Sorter instance to perform sorting using the appropriate strategy
  based on the dataset size.