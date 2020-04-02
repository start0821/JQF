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

    public Chromosome (T genes,int fitness,int age){
        this.genes = genes;
        this.fitness = fitness;
        this.age=age;
    }

    public Chromosome (){}

    public Chromosome (Chromosome<T> chr){
        this.genes = chr.genes;
        this.fitness = chr.fitness;
        this.age = chr.age;
    }
}