package cn.zhaojishun;

class luoxuan {
    public static void main(String[] args) {
        int a[][] = {{1, 2, 3, 4},
                    {12, 13, 14, 5},
                    {11, 16, 15, 6}, 
                    {10, 9, 8, 7}};
        printArray p = new printArray(a);
    }
}

class printArray {
    public printArray(int[][] arr) {
        int i, j;
        int sum = 0, start = 0, end = arr.length;
        while (sum < arr.length * arr.length) {
            i = start;
            for (j = start; j < end; j++) {
                if (arr[i][j] != 0) {
                    System.out.print(arr[i][j] + "\t");
                    arr[i][j] = 0;
                }
                j = end - start - 1;
                for (i = start; i < end - start; i++) {
                    if (arr[i][j] != 0) {
                        System.out.print(arr[i][j] + "\t");
                        arr[i][j] = 0;
                    }
                    i = end - start - 1;
                    for (j = end - start - 1; j >= start; j--) {
                        if (arr[i][j] != 0) {
                            System.out.print(arr[i][j] + "\t");
                            arr[i][j] = 0;
                        }
                        j = start;
                        for (i = end - start - 1; i >= start; i--) {
                            if (arr[i][j] != 0) {
                                System.out.print(arr[i][j] + "\t");
                                arr[i][j] = 0;
                            }
                            start++;
                            end--;
                        }
                    }
                }
            }
        }
    }
}