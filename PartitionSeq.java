import java.util.List;
import java.util.LinkedList;
import edu.rit.util.Random;

public class PartitionSeq
{
	static int N;
	static int M;
	static int s;

	static Random prng;
	static Boolean complete;

	static int[] numbers; // Set of numbers to be partitioned
	static int[] current_arrangement; // Each index corresponds to an index of the numbers array, the value represents the set in which the number is placed

	public static void main(String[] args)
	{
		if (args.length != 3) {
			usage();
			return;
		}

		N = args[0];
		M = args[1];
		s = args[2];

		if (N > M) {
			System.err.printf("N must be less than or equal to M\n");
			return;
		}

		prng = Random.getInstance(s);

		complete = false;

		numbers = new int[N];
		current_arrangement = new int[N];

		// Generate numbers to operate on and initialize first arrangement
		for (int i = 0; i < N; ++i) {
			numbers[i] = prng.nextInt(M);
			current_arrangement[i] = 0;
		}

		while (!complete) {
			// Generate next arrangement
			//TODO: How to easily generate all permutations without storing all of them?

			// Score arrangement
			//TODO: Need strategy to determine score of arrangement
			// Score = difference of min and max of partitions?
			int[] scores = new int[M];
			int min_index = 0;
			int max_index = 0;
			for (int i = 0; i < M; ++i) {
				scores[current_arrangement[i]] += numbers[i];
				if (score < scores[min_index])
					min_index = i;
				if (score > scores[max_index])
					max_index = i;
			}

			// Until arrangement generation is complete, just operate on a single arrangement
			complete = true;
		}
	}

	public static void usage() {
		System.err.println("Usage: PartitionSeq <num integers> <num partitions> <seed>");
	}
}
