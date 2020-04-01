import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import edu.berkeley.cs.jqf.fuzz.junit.quickcheck.FuzzStatement;

public class LinuxCommandOptionsGenerator extends Generator<String> {
    private static final int MAX_OPTIONS = 10;
    private static final String SHORT_DASH = "-";
    private static final String LONG_DASH = "--";

    public LinuxCommandOptionsGenerator() {
        super(String.class); // Register the type of objects that we can create
    }

    // This method is invoked to generate a single test case
    @Override
    public String generate(SourceOfRandomness random, GenerationStatus __ignore__) {
        this.status = status;
        private GenerationStatus status;

        // Initialize a calendar object
        String options = new String();

        // Randomly pick number of options
        int num = random.nextInt(MAXOPTIONS);
        for(int i=0;i<num;i++){
            options += generateOption(SourceOfRandomness random);
        }

        return options;

        // Let's set a timezone
        // First, get supported timezone IDs (e.g. "America/Los_Angeles")
        String[] allTzIds = TimeZone.getAvailableIDs();

        // Next, choose one randomly from the array
        String tzId = random.choose(allTzIds);
        TimeZone tz = TimeZone.getTimeZone(tzId);

    }

    private String generateOption(SourceOfRandomness random){
        boolean long_option = random.nextBoolean(random));
        if(long_option){

        }else{

        }
    }
}
