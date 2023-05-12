package Simulator.Genetic;

import Simulator.Vector3;

public class GeneticAlgorithm{

    // to determine the initial velocity and position on the earth

    private double mutationRate = 0.1;
    private int populationAmount = 100;
    private int generation = 100;
    private Individual[] population;

    public void run(){

        initPopulation();
        
        for(;;){
            
        }
    }

    // TODO
    private void initPopulation() {
    }


    class Individual{
        // for each individual initial
        private Vector3 position = new Vector3(1,1,1);
        private Vector3 velocity = new Vector3(1,1,1);
        private double fitness = 0;
        private double velocityMax = 60;
        
        Individual(){

        }

        public void crossover(){

        }

        public void mutation(){


        }

        public void evaluateFitness(){

        }
    }


}