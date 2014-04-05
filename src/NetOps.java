import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;

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

        neuralNetwork = new MultiLayerPerceptron(35, 2, 2);
        //neurons = new Neuron[rawData.length*rawData[0].length];
    }

    public DataSet createDataset(int[][] rawData, char target){
        double[] data1D = new double[rawData[0].length*rawData.length];
        int counter = 0;
        for(int i=0; i<rawData.length; i++){
            for(int j=0; j<rawData[0].length; j++){
                data1D[counter] = rawData[i][j];
                counter++;
            }//end for
        }//end for


        trainingSet.addRow(new DataSetRow(data1D, new double[]{(double)target}));
        return trainingSet;
    }//end createDataSet

    public void Learn(){
        neuralNetwork.learn(trainingSet);
    }
}
