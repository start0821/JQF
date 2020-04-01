import java.util.Arrays;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.FuzzStatement;
import org.apache.commons.lang.ArrayUtils;

public class LinuxCommandOptionsGenerator extends Generator<String> {
    private static final int MAX_OPTIONS = 10;
    private static final char[] alphanumber = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static final Character[] AlphabeticNumeric =ArrayUtils.toObject(alphanumber);
    private static final char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final Character[] Alphabetic = ArrayUtils.toObject(alpha);
    private static final String SHORT_DASH = "-";
    private static final String LONG_DASH = "--";
    private GenerationStatus status;

    public LinuxCommandOptionsGenerator() {
        super(String.class); // Register the type of objects that we can create
    }

    // This method is invoked to generate a single test case
    @Override
    public String generate(SourceOfRandomness random, GenerationStatus __ignore__) {
        this.status = status;

        // Initialize a calendar object
        String options = new String();

        // Randomly pick number of options
        int num = random.nextInt(MAX_OPTIONS);
        for(int i=0;i<num;i++){
            options += generateOption(random)+" ";
        }

        return options;
    }

    private String generateOption(SourceOfRandomness random){
        boolean long_option = random.nextBoolean();
        boolean argu = random.nextBoolean();
        String result = new String();
        if(long_option){
            result = LONG_DASH+generateString(random);
            if(argu){
                result += "="+generateString(random);
            }

        }else{
            result = SHORT_DASH+generateNumberAlphabet(random);
            if(argu){
                result += " " + generateString(random);
            }
        }
        return result;
    }

    private String generateString(SourceOfRandomness random){
        String result = new String();
        int num = random.nextInt(127);
        for(int i=0;i<num;i++){
            result += generateNumberAlphabet(random);
        }
        return result;
    }

    private char generateAlphabet(SourceOfRandomness random){
        return random.choose(AlphabeticNumeric);
    }

    private char generateNumberAlphabet(SourceOfRandomness random){
        return random.choose(AlphabeticNumeric);
    }
}
