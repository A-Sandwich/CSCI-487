import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.*;
import org.neuroph.core.learning.*;

import java.util.Arrays;
import java.util.Vector;
import org.neuroph.util.TransferFunctionType;
/**
 * Created by kb on 4/1/14.
 */
public class NetOps {
    private MultiLayerPerceptron neuralNetwork;
    private Neuron[] neurons;
    private DataSet trainingSet;
    public NetOps(){
        //CONSTRUCTOR

        trainingSet = new DataSet(35,1);

        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 35, 2, 2);
        //neurons = new Neuron[rawData.length*rawData[0].length];
    }

    public DataSet createDataset(int[][] rawData, char target){
        System.out.println("creating dataset");
        double[] data1D = new double[rawData[0].length*rawData.length];
        int counter = 0;

        data1D = convertArrays(rawData);

        trainingSet.addRow(new DataSetRow(data1D, new double[]{(double)target}));
        System.out.println("done");
        return trainingSet;
    }//end createDataSet

    public void learn(){
        neuralNetwork.learn(trainingSet);
    }

    public void test(int[][] rawData, char target){
        double[] data1d;
        data1d = convertArrays(rawData);
        DataSet test = new DataSet(35, 1);
        test.addRow(new DataSetRow(data1d, new double[]{(double)target}));
        testNeuralNetwork(neuralNetwork, test);
    }

    public void load(){
        neuralNetwork = (MultiLayerPerceptron)NeuralNetwork.load("hwrec.nnet");
    }

    public void save(){
        neuralNetwork.save("hwrec.nnet");
    }

    public double[] convertArrays(int[][] oldArray){
        double[] newArray = new double[oldArray.length*oldArray[0].length];


        int counter = 0;
        for(int i=0; i<oldArray.length; i++){
            for(int j=0; j<oldArray[0].length; j++){
                newArray[counter] = oldArray[i][j];
                counter++;
            }//end for
        }//end for

        return newArray;
    }

    public static void testNeuralNetwork(NeuralNetwork nnet, DataSet tset) {

        for(DataSetRow dataRow : tset.getRows()) {

            nnet.setInput(dataRow.getInput());
            nnet.calculate();
            double[ ] networkOutput = nnet.getOutput();
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );

        }

    }
}
