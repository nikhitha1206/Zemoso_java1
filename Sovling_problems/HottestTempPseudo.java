package BlueJ;
/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 *
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar=null;
        for(CSVRecord record : parser) {
            if(largestSoFar==null) {
                largestSoFar = record;
            }
            else
            {
                double temp=Double.parseDouble(record.get("TemperatureF"));
                double largestvalue=Double.parseDouble(largestSoFar.get("TemperatureF"));
                if(temp>largestvalue) {
                    largestvalue=temp;
                    largestSoFar=record;
                }
            }
        }
        return largestSoFar;

    }

    public void testHottestInDay () {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
    }
    public static void main(String args[])
    {
        CSVMax max=new CSVMax();
        max.testHottestInDay();
    }
}
