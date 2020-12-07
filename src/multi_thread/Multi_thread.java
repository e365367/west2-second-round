package multi_thread;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Multi_thread {

	public static long ans = 0;

	public static void main(String[] args) {

		ExecutorService es = Executors.newFixedThreadPool(10);
		Scanner scanner = new Scanner(System.in);
		int i;
		int x = scanner.nextInt();
		scanner.close();
		long startTime = System.currentTimeMillis();
		for (i = 0; i < 10; i++) {
			es.submit(new Task(i, x));
		}
		es.shutdown();
		while (true) {
			if (es.isTerminated()) {
				System.out.println(ans);
				long endTime = System.currentTimeMillis();
				System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
				break;
			}
		}

	}

	public static boolean contain(long num, int x) {
		return String.valueOf(num).contains(String.valueOf(x));
	}
}
