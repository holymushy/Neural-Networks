import java.util.ArrayList;
import java.util.Random;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Formula Equations Reference Understanding
 * Slopes of error function(for minimizing errors and descending to it via gradientDescent to update weights) =
 * -(targetData - actually error) + sigmoid(front propagation of using dot product of weights and given inputs)
 * + sigmoid(back propagation of again using dot product and using weights and hidden errors to distribute errors)
 * multuply by input signals
 */

/**
 * @author Oscar Leung 
 *
 */
public class neuralNetwork{
	private static double inodes,hnodes,onodes,learningRates;//number of nodes and learning rate
	private static ArrayList<Double> weights = new ArrayList<Double>();
	private static ArrayList<Double> inputs = new ArrayList<Double>();
	private static ArrayList<Double> hidden = new ArrayList<Double>();
	private static ArrayList<Double> outputs = new ArrayList<Double>();
	private static ArrayList<Double> errorHidden = new ArrayList<Double>(); // error that backpropogates
	private static ArrayList<Double> errorOutput = new ArrayList<Double>();
	private static double error,trainingData, actualOutput;
	private static double sumOfInputAndHiddenLayers;
	private static double sumOfHiddenAndOutputLayers;
	private static double outputOfInputAndHidderLayerAfterSigmoid;
	private static double outputOfHiddenAndOutputLayersAfterSigmoid;
	private boolean activateNeuron;
	private static final double slope = 0;//for now
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
		System.out.println("The learning rate is " + learningRates);
		System.out.print("The three starting weights are ");
		for (int counter = 0; counter < weights.size(); counter++) { 		      
			System.out.print(weights.get(counter) + " ");}  
		System.out.println(" ");
		System.out.print("The three initialized inputs are ");
		for (int counter = 0; counter < inputs.size(); counter++) {
			System.out.print(inputs.get(counter) + " ");}   
		System.out.println("");

		query();
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
		
		train();
		//testing area
		
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
		learningRates = learningRate;
		// random weights - can be adjusted
		initializeWeights(3);
		// starting inputs - can be adjusted
		initializeInputs(.9,.1,.8);



	}
	private static void initializeWeights(int w) {
		for(int i = 0; i < w;i++){
			double random = new Random().nextDouble();
			weights.add((-weightRange + (random * (weightRange - -(weightRange)))));
		}
		//Default numbers from slides gives 1.16 and .76133 respective, I have trouble w/ using arrays and arraylists
		//		weights.add(.9);
		//		weights.add(.3);
		//		weights.add(.4);
		//
		//		weights.add(.2);
		//		weights.add(.8);
		//		weights.add(.2);
		//		
		//		weights.add(.1);
		//		weights.add(.5);
		//		weights.add(.6);
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
		backPropagation();
		gradientDescent();
		//updateWeights();
	}
	/*
	 * Errors back propagation back based on weights
	 * Where the error in the first hidden node is the sum of the split errors in all the links
connecting forward from same node
	 */
	public static void backPropagation() {
		error = trainingData - actualOutput;
		//transpose(weights);// I had trouble w/ arrays and list again
		//sum of errorHidden
		double sumOfErrorHidden = dotProduct(weights, errorOutput);
		
	}
	/*
	 * If the complex difficult function is the error of the network, then going downhill to
find the minimum means we’re minimising the error
	 */
	public static void gradientDescent() {
	}
	/*
	 * Learning rate determines the power of updated weights
	 */
	public static void updateWeights(ArrayList<Double> weights) {
		double updatedWeights = 0;
		updatedWeights = weights.get(0) - learningRates * slope;
		weights.add(updatedWeights);
	}
	/*
	 * Transposing the weights to calculate for error back propogation
	 */
	public static ArrayList<Double> transpose(ArrayList<ArrayList<Double>> weights) {
		ArrayList<Double> matrixOut = new ArrayList<Double>();
		if (!weights.isEmpty()) {
			int noOfElementsInList = weights.get(0).size();
			for (int i = 0; i < noOfElementsInList; i++) {
				ArrayList<Double> col = new ArrayList<Double>();
				for (ArrayList<Double> row : weights) {
					col.add(row.get(i));}
				matrixOut.addAll(col);}}
		return matrixOut;
	}
	/**	
	 * Query the Neural NetWork
	 */
	public static void query() {
		//for(int i = 0; i < inputs.size();i++)
		sumOfInputAndHiddenLayers = dotProduct(inputs, weights);
		hidden.add(sumOfInputAndHiddenLayers);
		outputOfInputAndHidderLayerAfterSigmoid = sigmoid(sumOfInputAndHiddenLayers);	

		//	repeat for next layers since I could not generate the rest of the hidden outputs for output layer to use,
		// 	I will use other numbers to fill in the blanks to keep the code going w/ the dot product
		outputs.add(2.0);
		outputs.add(4.0);
		outputs.add(6.0);
		sumOfHiddenAndOutputLayers = dotProduct(hidden, outputs);
		outputs.add(sumOfHiddenAndOutputLayers);
		outputOfHiddenAndOutputLayersAfterSigmoid = sigmoid(sumOfHiddenAndOutputLayers);
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
