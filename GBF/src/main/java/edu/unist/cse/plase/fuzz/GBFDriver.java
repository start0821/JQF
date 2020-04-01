package edu.unist.cse.plase.fuzz;

import java.util.Random;
import java.lang.reflect.Method;
import java.io.File;
import java.lang.ClassLoader;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import edu.berkeley.cs.jqf.fuzz.guidance.StreamBackedRandom;
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.NonTrackingGenerationStatus;
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.FastSourceOfRandomness;
import edu.berkeley.cs.jqf.fuzz.ei.ZestGuidance;

/**
 * custom generator and get return value.
 *  
 */
public class GBFDriver 
{
    public static void main( String[] args )
    {
        String generatorClassName  = args[1];

        try{
            System.out.print(generatorClassName+"\n");

            Class<?> generatorClass =
                java.lang.Class.forName(generatorClassName, true, ClassLoader.getSystemClassLoader());
            String outputDirectoryName = args.length > 2 ? args[2] : "fuzz-results";
            File outputDirectory = new File(outputDirectoryName);
    
            ZestGuidance guidance = new ZestGuidance(generatorClassName,null,outputDirectory);
    
            // GBFGuidance guidance = new GBFGuidance();
            StreamBackedRandom randomFile = new StreamBackedRandom(guidance.getInput(), Long.BYTES);
            SourceOfRandomness random = new FastSourceOfRandomness(randomFile);
            GenerationStatus genStatus = new NonTrackingGenerationStatus(random);
    
            Method method = generatorClass.getMethod("generate",SourceOfRandomness.class,GenerationStatus.class);
            
            for(int i=0;i<10;i++){
                Object o = generatorClass.newInstance();
                Object value = method.invoke(o,random,genStatus);
                System.out.print(value.toString()+"\n");
            }
        



        } catch (Throwable e){

        }




        // if (args.length < 1){
        //     System.err.println("Usage: java " + GBFDriver.class + " GENERATOR_CLASS [OUTPUT_DIR [SEED_DIR | SEED_FILES...]]");
        //     System.exit(1);
        // }
        // String generatorClassName  = args[0];
        // String outputDirectoryName = args.length > 1 ? args[1] : "fuzz-results";
        // File outputDirectory = new File(outputDirectoryName);
        // File[] seedFiles = null;
        // if (args.length > 2) {
        //     seedFiles = new File[args.length-2];
        //     for (int i = 2; i < args.length; i++) {
        //         seedFiles[i-2] = new File(args[i]);
        //     }
        // }

        // try {

        //     GBFGuidance guidance = null;
        //     if (seedFiles == null) {
        //         guidance = new GBFGuidance(generatorClassName, null, outputDirectory);
        //     } else if (seedFiles.length == 1 && seedFiles[0].isDirectory()) {
        //         guidance = new GBFGuidance(generatorClassName, null, outputDirectory, seedFiles[0]);
        //     } else {
        //         guidance = new GBFGuidance(generatorClassName, null, outputDirectory, seedFiles);
        //     }


        // }
    }
}
