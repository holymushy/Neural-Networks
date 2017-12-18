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
	private static int error;
	private static double sumOfInputAndHiddenLayers = 0;
	private static double sumOfHiddenAndOutputLayers = 0;
	private static double weights[] = new double[3];
	private static double inputs[] = new double[3];
	// The weightRange is determine by the number of links into a node
	final static double weightRange = 1/Math.sqrt(3); //.577 

	/**
	 * 
	 */
	public neuralNetwork() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initialize(3,0,0,.5);
		//testing area
		System.out.println("The number of input nodes are " + inodes);
		System.out.println("The number of hidden nodes are " + hnodes);
		System.out.println("The number of output nodes are " + onodes);
		System.out.println("The learning rate is " + lr);
		System.out.print("The three starting weights are ");
		System.out.println(weights[0] + " " + weights[1] + " " + weights[2]);
		System.out.print("The three starting inputs are ");
		System.out.println(inputs[0] + " " + inputs[1] + " " + inputs[2]);
		
		train();
		//testing area
		System.out.println("The sum of input matrix and weight matrix " + sumOfInputAndHiddenLayers);
	}

	/**
	 * Initialize the Neural Network
	 */
	public static void initialize(int inputNodes, int hiddenNodes, int outputNodes, double learningRate)	{
		// Set # of nodes in each input, hidden, and output layers
		inodes = inputNodes;
		hnodes = hiddenNodes;
		onodes = outputNodes;
		// learning rate
		lr = learningRate;
		// random weights
		initializeWeights();
		// starting inputs
		initializeInputs();


	}
	private static void initializeWeights() {
		double random = new Random().nextDouble();
		double random1 = new Random().nextDouble();
		double random2 = new Random().nextDouble();
		// range of -.557 and +.577 for real application
		weights[0] = (-weightRange + (random * (weightRange - -(weightRange))));
		weights[1] = (-weightRange + (random1 * (weightRange - -(weightRange))));
		weights[2] = (-weightRange + (random2 * (weightRange - -(weightRange))));
		
		// Defaults using slides
	}
	private static void initializeInputs() {
		 inputs[0] = .9;
		 inputs[1] = .1;
		 inputs[2] = .8;
	}

	/**
	 * Trains the Neural Network
	 */
	public static void train() {
		sumOfInputAndHiddenLayers = dotProduct(inputs, weights);
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
	public static double sigmoid(int input) {
		return 1/(1+Math.exp(input));
	}
}
