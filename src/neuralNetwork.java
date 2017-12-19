import java.util.ArrayList;
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
	private static ArrayList<Double> weights = new ArrayList<Double>(); 
	private static ArrayList<Double> inputs = new ArrayList<Double>();
	private static ArrayList<Double> hidden = new ArrayList<Double>();
	private static ArrayList<Double> outputs = new ArrayList<Double>();
	private static double error,desiredTarget, actualOutput;
	private static double sumOfInputAndHiddenLayers;
	private static double sumOfHiddenAndOutputLayers;
	private static double outputOfInputAndHidderLayerAfterSigmoid;
	private static double outputOfHiddenAndOutputLayersAfterSigmoid;
	// The weightRange is determine by the number of links into a node
	final static double weightRange = 1/Math.sqrt(3); //.577 

	/**
	 * 
	 */
	public neuralNetwork() {
		weights = new ArrayList<>(100);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initialize(3,3,3,.5);
		//testing area
		System.out.println("The number of input nodes are " + inodes);
		System.out.println("The number of hidden nodes are " + hnodes);
		System.out.println("The number of output nodes are " + onodes);
		System.out.println("The learning rate is " + lr);
		System.out.print("The three starting weights are ");
		for (int counter = 0; counter < weights.size(); counter++) { 		      
			System.out.print(weights.get(counter) + " ");}  
		System.out.println(" ");
		System.out.print("The three initialized inputs are ");
		for (int counter = 0; counter < inputs.size(); counter++) {
			System.out.print(inputs.get(counter) + " ");}   
		System.out.println("");
		
		train();
		//testing area
		System.out.print("The three hidden outputs are ");
		for (int counter = 0; counter < hidden.size(); counter++) {
			System.out.print(hidden.get(counter) + " ");}   
		System.out.println("");
		
		System.out.print("The three hidden outputs after sigmoid are ");
		System.out.println(outputOfInputAndHidderLayerAfterSigmoid);
		
		System.out.print("The three outputs after sigmoid are ");
		for (int counter = 0; counter < outputs.size(); counter++) {
			System.out.print(outputs.get(counter) + " ");}   
		System.out.println("");
	}

	/**
	 * Initialize the Neural Network
	 */
	public static void initialize(int inputNodes, int hiddenNodes, int outputNodes, double learningRate){
		// Set the # of nodes in each input, hidden, and output layers in an arrayList
		inodes = inputNodes;
		inputs = new ArrayList<>(inputNodes);
		hnodes = hiddenNodes;
		hidden = new ArrayList<>(hiddenNodes);
		onodes = outputNodes;
		outputs = new ArrayList<>(outputNodes);
		// learning rate
		lr = learningRate;
		// random weights - can be adjusted
		initializeWeights(9);
		// starting inputs - can be adjusted
		initializeInputs(.9,.1,.8);



	}
	private static void initializeWeights(int w) {
//		for(int i = 0; i < w;i++){
//			double random = new Random().nextDouble();
//			weights.add((-weightRange + (random * (weightRange - -(weightRange)))));
//		}
		//Default numbers from slides
		weights.add(.9);
		weights.add(.3);
		weights.add(.4);

		weights.add(.2);
		weights.add(.8);
		weights.add(.2);
		
		weights.add(.1);
		weights.add(.5);
		weights.add(.6);
	}
	private static void initializeInputs(double d, double e, double f) {
		inputs.add(d);
		inputs.add(e);
		inputs.add(f);
	}


/**
 * Trains the Neural Network
 */
public static void train() {
	error = desiredTarget - actualOutput;
//	error = weights / (weights.get(index) + weights.get(index));
	//for(int i = 0; i < inputs.size();i++)
	sumOfInputAndHiddenLayers = dotProduct(inputs, weights);
	hidden.add(sumOfInputAndHiddenLayers);
	outputOfInputAndHidderLayerAfterSigmoid = sigmoid(sumOfInputAndHiddenLayers);	

//repeat for next layers
//	sumOfHiddenAndOutputLayers = dotProduct(hidden, outputs);
//	outputs.add(sumOfHiddenAndOutputLayers);
//	outputOfHiddenAndOutputLayersAfterSigmoid = sigmoid(sumOfHiddenAndOutputLayers);

}

/**	
 * Query the Neural NetWork
 */
public static void query() {

}

public static double dotProduct(ArrayList<Double> a, ArrayList<Double> b) {
	double sum = 0;
	for (int i = 0; i < a.size(); i++) {
		sum += a.get(i) * b.get(i);}
	return sum;
}
public static double sigmoid(double sumOfInputAndHiddenLayers) {
	return 1/(1+Math.exp(-sumOfInputAndHiddenLayers));
}
}
