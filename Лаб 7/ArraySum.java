public class ArraySum {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] partialSums = new int[2];
        
        Thread thread1 = new Thread(new HalfSummer(array, 0, array.length / 2, partialSums, 0));
        Thread thread2 = new Thread(new HalfSummer(array, array.length / 2, array.length, partialSums, 1));
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        int totalSum = partialSums[0] + partialSums[1];
        System.out.println("Сумма элементов массива: " + totalSum);
    }
}

class HalfSummer implements Runnable {
    private int[] array;
    private int start;
    private int end;
    private int[] partialSums;
    private int index;
    
    public HalfSummer(int[] array, int start, int end, int[] partialSums, int index) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.partialSums = partialSums;
        this.index = index;
    }
    
    @Override
    public void run() {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        partialSums[index] = sum;
    }
}