import java.util.*;

public class Main {

    /*
    returns average speed to sort arrNum arrays of size arrSize
    for each sort method
    */
    public static long[] average(int arrSize, int arrNum) {
        SortingComparison sort = new SortingComparison();
        long insertion = 0, merge = 0, quick = 0;
        for (int i = 0; i < arrNum; i++) {
            long start, finish;
            int sum = 0;

            //initialize two copies of random array
            int[] arr = sort.randomArray(arrSize);
            int[] arrCopy = arr;

            start = System.nanoTime();
            sort.insertionSort(arr);
            finish = System.nanoTime();
            insertion += finish - start;
            arr = arrCopy;

            start = System.nanoTime();
            sort.mergeSort(arr, 0, arrSize - 1);
            finish = System.nanoTime();
            merge += finish - start;
            arr = arrCopy;

            start = System.nanoTime();
            sort.quickSort(arr, 0, arrSize - 1);
            finish = System.nanoTime();
            quick += finish - start;
        }
        long[] results = {insertion / arrNum, merge / arrNum, quick / arrNum};
        return results;
    }

    public static void main(String[] args) {
        SortingComparison sort = new SortingComparison();
        Scanner userInput = new Scanner(System.in);
        boolean x = true;
        while (x) {
            System.out.println("Choose the size of array to sort (10 - 100,000)");
            System.out.print("Enter size: ");
            int size = Integer.parseInt(userInput.nextLine());

            System.out.print("How many different arrays to sort (returns avg time): ");
            int nArr = Integer.parseInt(userInput.nextLine());
            if (size < 10 || size > 100000)
                continue;
            long[] avgs = average(size, nArr);

            System.out.println("\nElapsed time");
            System.out.println("Insertion Sort average: " + avgs[0] + " nanoseconds");
            System.out.println("Merge Sort average: " + avgs[1] + " nanoseconds");
            System.out.println("Quick Sort average: " + avgs[2] + " nanoseconds\n");

        }

    }
}