// TODO : porting from tmp.py
package edu.unist.cse.plase.fuzz.genetic;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.unist.cse.plase.fuzz.genetic.Chromosome;


public abstract class GA{
    private List<Object> geneSet;
    public class ObjChromosome extends Chromosome<Object> {}

    // virtual function.
    // you should @Override the function to use the class.
    public ObjChromosome generate_parent(int length);

    // virtual function.
    // you should @Override the function to use the class.
    public Integer get_fitness(ObjChromosome gene);

    // virtual function.
    // you should @Override the function to use the class.
    public ObjChromosome mutate(ObjChromosome parent);

    public ObjChromosome get_best(int optimalFitness){
        return get_best(optimalFitness,null);
    }

    public ObjChromosome get_best(int optimalFitness, Integer maxAge){
        ObjChromosome parent, bestParent;
        parent = bestParent = generate_parent();
        display(bestParent);

        List<Integer> historicalFitness = new ArrayList<Integer>();
        historicalFitness.add(bestParent);

        while(true){
            ObjChromosome child = mutate(parent);
            if(parent.fitness > child.fitness){
                if(maxAge == null){
                    continue;
                }

                parent.age++;
                if(maxAge > parent.age){
                    continue;
                }
                Integer index = Collections.binarySearch(historicalFitness,child.fitness);
                if(index < 0){
                    index = -index-1;
                }
                Double proportionSimilar = index / new Double(historicalFitness.length());

                if(Math.random() < Math.exp(-proportionSimilar)){
                    parent = child;
                    continue;
                }
                bestParent.age = 0;
                parent = bestParent;
                continue;
            }
            // if child and parent has same fitness
            if(! child.fitness > parent.fitness){
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

    public void display(ObjChromosome chr){
        System.out.print(chr.getGenes().toString() + " : " + chr.fitness.toString());
    }
}