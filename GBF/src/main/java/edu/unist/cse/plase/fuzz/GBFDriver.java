package edu.unist.cse.plase.fuzz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.ClassLoader;
import java.lang.InterruptedException;
import java.lang.Math;
import java.lang.StringBuffer;
import java.lang.reflect.Method;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import edu.berkeley.cs.jqf.fuzz.guidance.StreamBackedRandom;
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.FastSourceOfRandomness;
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.NonTrackingGenerationStatus;

import org.apache.commons.lang3.exception.ExceptionUtils;

import edu.unist.cse.plase.fuzz.GBFGuidance;

/**
 * custom generator and get return value.
 *  
 */
public class GBFDriver 
{


    public static void main( String[] args )
    {
        String generatorClassName  = args[1];
        String programLocation  = args[2];

        try{
            Scanner input = new Scanner(new File(programLocation));

            Class<?> generatorClass =
                java.lang.Class.forName(generatorClassName, true, ClassLoader.getSystemClassLoader());
            String outputDirectoryName = args.length > 3 ? args[3] : "fuzz-results";
            File outputDirectory = new File(outputDirectoryName);
    
            Method method = generatorClass.getMethod("generate",SourceOfRandomness.class,GenerationStatus.class);
            Object o = generatorClass.newInstance();
            
            GBFGuidance guidance = new GBFGuidance(generatorClassName,null,outputDirectory,method,o,programLocation);
    
        



        } catch (Throwable e){
            ExceptionUtils.getStackTrace(e);

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
