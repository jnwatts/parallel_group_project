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
	static LinkedList<int>[] current_solution;

	static int[] numbers;

	public static void main(String[] args)
	{
		if (args.length != 3) {
			usage();
			return;
		}

		N = args[0];
		M = args[1];
		s = args[2];

		prng = Random.getInstance(s);

		complete = false;

		while (!complete) {
			// Generate next solution
			//TODO: How to easily generate all permutations without storing all of them?
			{
				// For now, generate a random solution
				current_solution = new LinkedList<int>[M];
				for (int i = 0; i < N; ++i) {
					current_solution[prng.nextInt(M)].add(prng.nextInt(N));
				}
			}

			// Score solution
			//TODO: Need strategy to determine score of solution
			// Score = difference of min and max of partitions?
			int[] scores = new int[M];
			int min_index = 0;
			int max_index = 0;
			for (int i = 0; i < M; ++i) {
				Iterator<int> iter = current_solution[i].iterator();
				int score = 0;
				while (iter.hasNext()) {
					score += iter.next();
				}
				scores[i] = score;
				if (score < scores[min_index])
					min_index = i;
				if (score > scores[max_index])
					max_index = i;
			}

			// Until solution generation is complete, just operate on a single solution
			complete = true;
		}
	}

	public static void usage() {
		System.err.println("Usage: PartitionSeq <num integers> <num partitions> <seed>");
	}
}
