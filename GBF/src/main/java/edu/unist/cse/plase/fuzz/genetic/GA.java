package edu.unist.cse.plase.fuzz.genetic;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.unist.cse.plase.fuzz.genetic.Chromosome;


public abstract class GA<T>{
    public class ExtendedChromosome extends Chromosome<T> {
        public Object generatedInput;

        public ExtendedChromosome(){
            super();
        }
        
        public ExtendedChromosome(T t, int fitness){
            super(t,fitness);
        }

        public ExtendedChromosome(T t, int fitness, int age){
            super(t,fitness,age);
        }

        public ExtendedChromosome(ExtendedChromosome ech){
            super((Chromosome) ech);
        }
    }

    // virtual function.
    // you should @Override the function to use the class.
    abstract public ExtendedChromosome generate_parent();

    // virtual function.
    // you should @Override the function to use the class.
    abstract public int get_fitness(ExtendedChromosome gene);

    // virtual function.
    // you should @Override the function to use the class.
    abstract public ExtendedChromosome mutate(ExtendedChromosome parent);

    public ExtendedChromosome get_best(int optimalFitness){
        return get_best(optimalFitness,null);
    }

    public ExtendedChromosome get_best(int optimalFitness, Integer maxAge){
        ExtendedChromosome parent, bestParent;
        parent = bestParent = generate_parent();
        display(bestParent);

        List<Integer> historicalFitness = new ArrayList<Integer>();
        historicalFitness.add(bestParent.fitness);

        while(true){
            ExtendedChromosome child = mutate(parent);
            if(parent.fitness > child.fitness){
                if(maxAge == null){
                    continue;
                }

                parent.age++;
                if(maxAge > parent.age){
                    continue;
                }
                int index = Collections.binarySearch(historicalFitness,child.fitness);
                if(index < 0){
                    index = -index-1;
                }
                Double proportionSimilar = index / new Double(historicalFitness.size());

                if(Math.random() < Math.exp(-proportionSimilar)){
                    parent = child;
                    continue;
                }
                bestParent.age = 0;
                parent = bestParent;
                continue;
            }
            // if child and parent has same fitness
            if(! (child.fitness > parent.fitness)){
                child.age = parent.age+1;
                parent = child;
                continue;
            }
            // if child fitness is larger than parent fitness
            child.age = 0;
            parent = child;
            if(child.fitness > bestParent.fitness){
                bestParent = child;
                display(bestParent);
                historicalFitness.add(bestParent.fitness);
            }
        }
    }

    public void display(ExtendedChromosome chr){
        System.out.print(chr.genes.toString() + " : " + Integer.toString(chr.fitness));
    }
}