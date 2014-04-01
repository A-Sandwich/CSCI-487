import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.nnet.Kohonen;
import org.neuroph.nnet.Perceptron;

/**
 * Created by kb on 4/1/14.
 */
public class NetOps {
    private NeuralNetwork nueralNetwork;
    private Neuron[] neurons;
    public NetOps(int[][] rawData){
        //CONSTRUCTOR
        nueralNetwork = new Kohonen(35, 26);
        neurons = new Neuron[rawData.length*rawData[0].length];
    }
}
