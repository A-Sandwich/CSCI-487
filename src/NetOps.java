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
    //private Neuron[] neurons;
    private DataSet trainingSet;
    public NetOps(){
        //CONSTRUCTOR

        trainingSet = new DataSet(35,26);

        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 35, 100, 26);
        //neurons = new Neuron[rawData.length*rawData[0].length];
    }

    public DataSet createDataset(int[][] rawData, char target){
        double[] data1D;

        double[] target_array = getTargetArray(target);

        data1D = convertArrays(rawData);
        trainingSet.addRow(new DataSetRow(data1D, target_array));
        return trainingSet;
    }//end createDataSet

    public void learn(){
        neuralNetwork.learn(trainingSet);
    }

    public void test(int[][] rawData, char target){
        double[] data1d;
        double[] target_array = getTargetArray(target);
        data1d = convertArrays(rawData);
        DataSet test = new DataSet(35, 26);
        test.addRow(new DataSetRow(data1d, target_array));
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
            int max = 0, second_best = 0;

            for(DataSetRow dataRow : tset.getRows()) {

            nnet.setInput(dataRow.getInput());
            nnet.calculate();
            double[ ] networkOutput = nnet.getOutput();

            for(int i = 0; i < networkOutput.length; i++){
                if(networkOutput[i] > networkOutput[max]){
                    if(networkOutput[max] > networkOutput[second_best])
                        second_best = max;
                    if(networkOutput[i] > networkOutput[max])
                        max = i;
                }//end if

            }//end for

            System.out.println("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );
            System.out.println("Guess 1: "+(char)(max+65)+" (Probability: "+(int)(networkOutput[max] * 100)+"% )");
            System.out.println("Guess 2: "+(char)(second_best+65)+" (Probability: "+(int)(networkOutput[second_best] * 100)+"% )");

        }//end for

    }//end testNeuralNetwork

    public double[] getTargetArray(char target){
        double[] target_array = new double[26];

        for(int i = 0; i < target_array.length; i++){
            if(i == ((int)target)-65)
                target_array[i] = 1;
            else
                target_array[i] = 0;
        }//end for

        return target_array;
    }
}
