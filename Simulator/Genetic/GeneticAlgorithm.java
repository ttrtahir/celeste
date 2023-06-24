package Simulator.Genetic;

import Simulator.SolarSystem;
import Simulator.State;
import Simulator.Vector3;

import java.util.Random;

public class GeneticAlgorithm {
    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.initPopulation();
        ga.run();
        findBestInitialVelocities(ga.population);
        System.out.println(ga.found);
    }

    // Coordinate of this vector: x = 46.523072208288795, y= -3.493557729103768, z=
    // -2.3419267033312465

    public static void findBestInitialVelocities(Individual[] population) {
        System.out.println("");
        System.out.println("Best velocities are :");
        for (var indi : population) {
            if (indi.fitness >= 1)
                System.out.println(indi.getVelocity().toString());
        }
        System.out.println("nb: if nothing is printed increase the generation and population amount");
    }

    private double mutationRate = 0.1;
    private int populationAmount = 50;
    private int generation = 100;
    private Individual[] population;
    private boolean found = false;

    private double desiredDistanceMax = 300000; // 300k km
    private double velocityMax = 60;

    public void run() {
        for (int gen = 0; gen < generation; gen++) {
            System.out.println("Generation : " + gen + " ---------------------------------- ");
            for (Individual individual : population) {
                individual.sol.initialProcess(individual.getVelocity());
                // Vector3 temp = (Vector3) individual.sol.getStates()[0].state[11][1];
                // System.out.println(individual.id + " " + temp.getX() + " " + temp.getY() + "
                // " + temp.getZ());
            }
            evaluateFitness();
            Individual[] offsprings = reproduce(population);
            mutate(offsprings);
            population = offsprings;
        }
        // After all generations, print the final results
        // System.out.println("Final population:");
        // for (Individual individual : population) {
        // System.out.println(individual.toString());
        // }
    }

    private void initPopulation() {
        this.population = new Individual[this.populationAmount];
        for (int i = 0; i < this.populationAmount; i++) {
            Vector3 velocity;
            do {
                double randomX = Math.random() * velocityMax * 1; // positive
                double randomY = Math.random() * velocityMax * -1; // negative
                double randomZ = Math.random() * velocityMax * -1; // negative < 10
                velocity = new Vector3(randomX, randomY, randomZ);
            } while (velocity.getMagnitude() > velocityMax);

            this.population[i] = new Individual(velocity);
        }
    }

    private void evaluateFitness() {
        for (Individual individual : population) {
            individual.evaluateFitness();
        }
    }

    private Individual[] selectParents(Individual[] population) {
        // Perform roulette wheel selection to choose parents
        Individual[] parents = new Individual[populationAmount];
        double[] cumulativeFitness = new double[populationAmount];
        double totalFitness = 0;

        // Calculate the total fitness of the population
        for (int i = 0; i < populationAmount; i++) {
            double fitness = population[i].getFitness();
            totalFitness += fitness;
            cumulativeFitness[i] = totalFitness;
        }

        // Select parents based on their fitness probabilities
        for (int j = 0; j < populationAmount; j++) {
            Random r = new Random();
            double luckyNumber = r.nextDouble() * totalFitness;

            // Find the parent whose cumulative fitness exceeds the lucky number
            int selectedParentIndex = 0;
            for (int i = 0; i < populationAmount; i++) {
                if (luckyNumber <= cumulativeFitness[i]) {
                    selectedParentIndex = i;
                    break;
                }
            }
            parents[j] = population[selectedParentIndex];
        }

        return parents;
    }

    private Individual[] reproduce(Individual[] population) {
        Individual[] offsprings = new Individual[populationAmount];

        for (int i = 0; i < populationAmount; i++) {
            Individual[] parents = selectParents(population);

            // Perform single-point crossover to produce offspring
            Vector3 parent1Velocity = parents[i].getVelocity();
            Vector3 parent2Velocity = parents[(i + 1) % (populationAmount)].getVelocity();

            // Create offspring velocity by combining the velocities of parents
            Vector3 offspringVelocity1 = (Vector3) (parent1Velocity.add(parent2Velocity).multiply(0.5));

            System.out.println(offspringVelocity1.toString());

            offsprings[i] = new Individual(offspringVelocity1);
        }

        return offsprings;
    }

    private void mutate(Individual[] offspring) {
        // Perform mutation on the offspring
        for (Individual individual : offspring) {
            if (Math.random() < mutationRate) {
                double randomX = Math.random() * velocityMax * (Math.random() < 0.5 ? -1 : 1);
                double randomY = Math.random() * velocityMax * (Math.random() < 0.5 ? -1 : 1);
                double randomZ = Math.random() * velocityMax * (Math.random() < 0.5 ? -1 : 1);
                Vector3 randomVector = new Vector3(randomX, randomY, randomZ);
                individual.setVelocity((Vector3) individual.getVelocity().addmultiply(mutationRate, randomVector));
            }
        }
    }

    class Individual {
        private Vector3 velocity;
        private double fitness = 0;
        private double minDistanceBetweenTitanAndSpaceProbe = 1e30;
        SolarSystem sol = new SolarSystem();

        Individual(Vector3 velocity) {
            this.velocity = velocity;
            this.sol = new SolarSystem();
        }

        public Vector3 getVelocity() {
            return velocity;
        }

        public void setVelocity(Vector3 velocity) {
            this.velocity = velocity;
            this.sol.getStates()[0].state[11][1] = velocity;
        }

        public double getFitness() {
            return fitness;
        }

        public void evaluateFitness() {
            getMinState();
            this.fitness = desiredDistanceMax / this.minDistanceBetweenTitanAndSpaceProbe;
            if (this.fitness >= 1) {
                found = true;
                System.out.println(this.getVelocity().toString());
            }
        }

        public State getMinState() {
            State[] states = sol.getStates();
            int lengthOfStates = states.length;
            State minState = new State();

            for (int i = 0; i < lengthOfStates; i++) {
                Vector3 posTitan = (Vector3) states[i].state[8][0];
                Vector3 posProbe = (Vector3) states[i].state[11][0];
                double distanceEuclidean = posProbe.euclideanDist(posTitan);
                if (distanceEuclidean < this.minDistanceBetweenTitanAndSpaceProbe) {
                    this.minDistanceBetweenTitanAndSpaceProbe = distanceEuclidean;
                    minState = states[i];
                }
            }
            return minState;
        }

        public String toString() {
            return "x: " + velocity.getX() + " y: " + velocity.getY() + " z: " + velocity.getZ();
        }
    }
}
