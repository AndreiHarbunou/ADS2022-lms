package by.it.group151051.goron.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {
    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w = scanner.nextInt();
        int n = scanner.nextInt();
        int[] gold = new int[n];
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }
        int[][] A = new int[n + 1][w + 1]; // Матрица максимальных весов комбинаций слитков в зависимости от числа слитков
                                           // и объема рюкзака
        for (int k = 0; k <= n; k++) {
            for (int s = 0; s <= w; s++) {
                if (k == 0 || s == 0) {    // Если k и s равны нулю, то
                    A[k][s] = 0;           // соответственно ничего в рюкзак не поместилось
                }
                else {
                    if (s >= gold[k-1]) {  // Если есть место в рюкзаке, то
                        // в него помещается или такая же комбинация слитков как если бы их было только k-1,
                        // или же помещается слиток весом gold[k-1], который не поместился в прошлый раз при меньшем размере рюкзака
                        A[k][s] = Math.max(A[k-1][s], A[k][s - gold[k-1]] + gold[k-1]);
                    }
                    else {
                        A[k][s] = A[k-1][s]; // Иначе, поместить можно только такую комбинацию, когда слитков было k-1
                    }
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return A[n][w];
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151051/goron/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
