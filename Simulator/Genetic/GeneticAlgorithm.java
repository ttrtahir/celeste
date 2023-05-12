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
        
        for(){
            run calculations 
        }
    }

    
    class Individual{
        // for each individual initial
        private Vector3 position = new Vector3(1,1,1);
        private int velocity = 1;
        private double fitness = 0;
        
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