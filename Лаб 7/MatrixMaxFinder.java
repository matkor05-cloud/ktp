public class MatrixMaxFinder {
    public static void main(String[] args) {
        int[][] matrix = {
            {3, 5, 1, 8},
            {9, 2, 7, 4},
            {6, 0, 5, 3}
        };
        
        int rows = matrix.length;
        int[] rowMax = new int[rows];
        Thread[] threads = new Thread[rows];
        
        for (int i = 0; i < rows; i++) {
            final int rowIndex = i;
            threads[i] = new Thread(() -> {
                int max = matrix[rowIndex][0];
                for (int j = 1; j < matrix[rowIndex].length; j++) {
                    if (matrix[rowIndex][j] > max) {
                        max = matrix[rowIndex][j];
                    }
                }
                rowMax[rowIndex] = max;
            });
            threads[i].start();
        }
        
        for (int i = 0; i < rows; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        int globalMax = rowMax[0];
        for (int i = 1; i < rows; i++) {
            if (rowMax[i] > globalMax) {
                globalMax = rowMax[i];
            }
        }
        
        System.out.println("Наибольший элемент в матрице: " + globalMax);
    }
}