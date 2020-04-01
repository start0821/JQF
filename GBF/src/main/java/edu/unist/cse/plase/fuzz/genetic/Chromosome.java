package edu.unist.cse.plase.fuzz.genetic;

public class Chromosome<T>{
    public T genes;
    public int fitness;
    public int age;

    public Chromosome (T genes,int fitness){
        this.genes = genes;
        this.fitness = fitness;
        this.age=0;
    }
}