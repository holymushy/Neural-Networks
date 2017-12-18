import java.util.Random;

/**
 * 
 */

/**
 * @author Oscar Leung 
 *
 */
public class neuralNetwork{
	private static double inodes,hnodes,onodes,lr;
	// The weightRange is determine by the amount of nodes 
	final static double weightRange = 1/Math.sqrt(3); //.577 if you didn't know
	private static double weights;
	/**
	 * 
	 */
	public neuralNetwork() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initialize(0,0,0,0);
		System.out.println("The input nodes are " + inodes);
		System.out.println("The hidden nodes are " + hnodes);
		System.out.println("The output nodes are " + onodes);
		System.out.println("The learning rate is " + onodes);
		System.out.println("The random weights are " + weights);

	}

	/**
	 * Initialize the Neural Network
	 */
	public static void initialize(int inputNode, int hiddenNodes, int outputNodes, int learningRate)	{
		// Set # of nodes in each input, hidden, and output layers
		inodes = inputNode;
		hnodes = hiddenNodes;
		onodes = outputNodes;
		// learning rate
		lr = learningRate;
		// random weights
		Random r = new Random();
		double random = new Random().nextDouble();
		weights = -weightRange + (random * (weightRange - -(weightRange)));

	}
	/**
	 * Trains the Neural Network
	 */
	public static void train() {

	}
	/**	
	 * Query the Neural NetWork
	 */
	public static void query() {

	}

	public static double dotProduct(double[] a, double[] b) {
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i] * b[i];}
		return sum;
	}


}
