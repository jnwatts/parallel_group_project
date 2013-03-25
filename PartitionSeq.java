public class PartitionSeq
{
	public static void main(String[] args)
	{
		if (args.length != 2) {
			usage();
			return;
		}
	}

	public static void usage() {
		System.err.println("Usage: PartitionSeq <arg1> <arg2>");
	}
}
