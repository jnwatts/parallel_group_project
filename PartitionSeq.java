import java.lang.Math;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import edu.rit.util.Random;

public class PartitionSeq
{
	static int N;
	static int M;
	static int s;

	static Random prng;

	static int[] numbers; // Set of numbers to be partitioned
	static int[] current_arrangement; // Each index corresponds to an index of the numbers array, the value represents the set in which the number is placed
	static long arrangements_remaining;
	
	static int min_score;
	static LinkedList<int[]> min_arrangements;

	public static void main(String[] args)
	{
		if (args.length != 3) {
			usage();
			return;
		}

		N = Integer.parseInt(args[0]);
		M = Integer.parseInt(args[1]);
		s = Integer.parseInt(args[2]);

		if (M > N) {
			System.err.printf("M must be less than or equal to N\n");
			return;
		}

		prng = Random.getInstance(s);

		numbers = new int[N];
		current_arrangement = new int[N];

		arrangements_remaining = 1L;
		for (int i = 0; i < N; ++i) {
			arrangements_remaining *= M;
		}

		min_score = Integer.MAX_VALUE;
		min_arrangements = new LinkedList<int[]>();

		// Generate numbers to operate on and initialize first arrangement
		for (int i = 0; i < N; ++i) {
			numbers[i] = i; //prng.nextInt(N);
			current_arrangement[i] = 0;
		}

		while (arrangements_remaining > 0) {
			// Score arrangement
			{
				int[] sums = new int[M];
				int score;
				int min_index = 0;
				int max_index = 0;
				for (int i = 0; i < N; ++i) {
					int index = current_arrangement[i];
					int num = numbers[i];
					sums[index] += num;
				}
				for (int i = 0; i < M; ++i) {
					int sum = sums[current_arrangement[i]];
					if (sum < sums[min_index])
						min_index = i;
					if (sum > sums[max_index])
						max_index = i;
				}
				score = sums[max_index] - sums[min_index];

				if (score <= min_score) {
					if (score < min_score) {
						min_arrangements.clear();
						min_score = score;
					}
					min_arrangements.add((int[])current_arrangement.clone());
				}
			}

			// Generate next arrangement
			for (int i = 0; i < N; ++i) {
				current_arrangement[i]++;
				if (current_arrangement[i] < M) {
					break;
				} else {
					current_arrangement[i] = 0;
				}
			}

			--arrangements_remaining;
		}

		System.out.printf("numbers:");
		for (int i = 0; i < N; ++i) {
			System.out.printf("%d,", numbers[i]);
		}
		System.out.printf("\n");
		System.out.printf("min_score: %d\n", min_score);
		System.out.printf("arrangements:\n");
		Iterator<int[]> iter = min_arrangements.iterator();
		while (iter.hasNext()) {
			int[] arrangement = iter.next();
			for (int i = 0; i < N; ++i) {
				System.out.printf("%d,", arrangement[i]);
			}
			System.out.printf("\n");
		}
		
	}

	public static void usage() {
		System.err.println("Usage: PartitionSeq <N numbers> <M partitions> <seed>");
	}
}
