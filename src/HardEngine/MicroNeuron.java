package HardEngine;

public class MicroNeuron {
	
	private float[] weights;
	
	public MicroNeuron(float[] initialWeights) {
        this.weights = initialWeights;
    }
    
    public float predict(float[] inputs) {
        float sum = 0;
        for(int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return Math.max(0, Math.min(1, sum));
    }
    
    public void learn(float[] inputs, float target, float learningRate) {
        float prediction = predict(inputs);
        float error = target - prediction;
        for(int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * learningRate;
        }
    }
}