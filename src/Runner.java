import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

// Первая строка содержит число 10^51≤n≤10^5, вторая — массив A[1…n], содержащий натуральные числа, не превосходящие 10^9.
// Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j]. (Такая пара элементов называется инверсией
// массива. Количество инверсий в массиве является в некотором смысле его мерой неупорядоченности: например, в
// упорядоченном по неубыванию массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию, инверсию образуют
// каждые два элемента.)


//TestInput:
//5
//2 3 9 2 9
//Output: 2

class Runner {
    public static long counter = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        //Итеративная сортировка слиянием, как оказалось, делает инверсий меньше, но отсортированный массив выдаёт корректный)
        //iterativeMergeSort(A);
        mergeSort(A);
        System.out.println(counter);
    }

    public static int[] iterativeMergeSort(int[] A){
        Queue<int[]> Q = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            int[] current = {A[i]};
            Q.add(current);
        }
        while (Q.size() > 1){
            int[] currentTest = merge(Q.poll(), Q.poll());
            System.out.println(Arrays.toString(currentTest));
            Q.add(currentTest);
        }
        return Q.poll();
    }

    public static int[] mergeSort(int[] A){
        if (A.length == 1){
            return A;
        }
        int m = A.length/2;
        return merge(mergeSort(Arrays.copyOfRange(A, 0 , m)), mergeSort(Arrays.copyOfRange(A, m , A.length)));
    }

    public static int[] merge(int[] firstArray, int[] secondArray){
        int f = 0;
        int s = 0;
        int[] result = new int[firstArray.length + secondArray.length];

        int i = 0;
        while (f < firstArray.length && s < secondArray.length){
            if (firstArray[f] > secondArray[s]){
                counter += firstArray.length - f;
                result[i] = secondArray[s];
                s++;
            } else {
                result[i] = firstArray[f];
                f++;
            }
            i++;
        }

        if (f != firstArray.length){
            System.arraycopy(firstArray, f, result, i, firstArray.length-f);
        } else if (s != secondArray.length){
            System.arraycopy(secondArray, s, result, i, secondArray.length-s);
        }
        return result;
    }
}