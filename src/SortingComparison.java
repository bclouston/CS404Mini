import java.util.Random;

public class SortingComparison {

    /*
    Generates an array of size n containing random integers
    valued between 0 (inclusive) and n (exclusive)
    */
    public int[] randomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = rand.nextInt(n);
        return arr;
    }

    //swaps the index location of two elements within an array
    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /*
    Iterates through each element of an array and places it
    into the growing sorted list behind currently selected index
    */
    public void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int k = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > k) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = k;
        }
    }

    /*
    Three-way partitioning separating values into groups less than,
    equal to and greater than pivot.
    Returns a range of index locations equal to pivot value
    */
    int[] partition(int[] arr, int low, int high) {
        int l = low;
        int r = low;
        int u = high;
        int pivot = arr[(low + high) / 2];
        while (r <= u) {
            if (arr[r] < pivot) {
                swap(arr, l, r);
                l++;
                r++;
            }
            else if (arr[r] > pivot) {
                swap(arr, r, u);
                u--;
            }
            //element equal to pivot
            else
                r++;
        }
        //range of index's equal to pivot
        int[] lr = new int[2];
        lr[0] = l;
        lr[1] = r;
        return lr;
    }

    /*
    sorts an array by selecting a pivot element
    places pivot in its sorted index location and recursively sorts
    subarrays of elements less than and greater than pivot element
    */
    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            //partition array
            int[] lr = partition(arr, low, high);
            //sort partitions of smaller and larger elements
            quickSort(arr, low, lr[0] - 1);
            quickSort(arr, lr[1], high);
        }
    }

    void merge(int[] arr, int l, int m, int r) {
        //initialize temp arrays
        int nl = m - l + 1;
        int nr = r - m;
        int[] left = new int[nl];
        int[] right = new int[nr];
        //copy elements into temp arrays
        for (int i = 0; i < nl; i++)
            left[i] = arr[l + i];
        for (int j = 0; j < nr; j++)
            right[j] = arr[m + 1 + j];
        //merge temp arrays
        int i = 0, j = 0, k = l;
        while (i < nl && j < nr) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        //copy any remaining elements
        while (i < nl) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < nr) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    /*
    Repeatedly divides array in half until each subarray size = 1
    subarrays are then sorted and merged into one sorted array
    */
    void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    //print an array
    public void print(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");
    }
}

