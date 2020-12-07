package multi_thread;

public class Task implements Runnable {
	private final int i;
	private final int x;
	public long temp = 0;

	public Task(int i, int x) {
		this.i = i;
		this.x = x;
	}

	@Override
	public void run() {
		for (int j = i * 100000000; j < (i + 1) * 100000000; j++) {
			if (Multi_thread.contain(j, x)) {
				temp += j;
			}
		}
		Multi_thread.ans += temp;
		if (i == 10) {

			System.out.println(Multi_thread.ans);
		}
	}
}
