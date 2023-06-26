package celeste.Simulator.Genetic;

import celeste.Simulator.SolarSystem;
import celeste.Simulator.State;
import celeste.Simulator.Vector3;

import java.util.Random;

/**
 * The GeneticAlgorithm class represents a genetic algorithm used to find the best initial velocities
 * for a simulation. It utilizes a population of individuals and evolves them over multiple generations
 * to find the optimal solution.
 */
public class GeneticAlgorithm {

    /**
     * The main method of the GeneticAlgorithm class.
     */
    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.initPopulation();
        ga.run();
        findBestInitialVelocities(ga.population);
        System.out.println(ga.found);
    }

    /**
     * Finds the best initial velocities from the given population of individuals.
     *
     * @param population The array of individuals.
     */
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

    /**
     * Runs the genetic algorithm for the specified number of generations.
     */
    public void run() {
        for (int gen = 0; gen < generation; gen++) {
            System.out.println("Generation : " + gen + " ---------------------------------- ");
            for (Individual individual : population) {
                individual.sol.initialProcess(individual.getVelocity());
            }
            evaluateFitness();
            Individual[] offsprings = reproduce(population);
            mutate(offsprings);
            population = offsprings;
        }
    }

    /**
     * Initializes the population with random velocities.
     */
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

    /**
     * Evaluates the fitness of each individual in the population.
     */
    private void evaluateFitness() {
        for (Individual individual : population) {
            individual.evaluateFitness();
        }
    }

    /**
     * Selects parents from the population based on their fitness using roulette wheel selection.
     *
     * @param population The array of individuals.
     * @return An array of selected parents.
     */
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

    /**
     * Reproduces the population by performing single-point crossover between selected parents.
     *
     * @param population The array of individuals.
     * @return An array of offspring individuals.
     */
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

    /**
     * Mutates the offspring individuals based on the mutation rate.
     *
     * @param offspring The array of offspring individuals.
     */
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

    /**
     * The Individual class represents an individual in the genetic algorithm.
     */
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

        /**
         * Evaluates the fitness of the individual by calculating the minimum distance between Titan and the space probe.
         */
        public void evaluateFitness() {
            getMinState();
            this.fitness = desiredDistanceMax / this.minDistanceBetweenTitanAndSpaceProbe;
            if (this.fitness >= 1) {
                found = true;
                System.out.println(this.getVelocity().toString());
            }
        }

        /**
         * Finds the state with the minimum distance between Titan and the space probe.
         * The last state most of the time does not have the least distance.
         *
         * @return The state with the minimum distance.
         */
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
