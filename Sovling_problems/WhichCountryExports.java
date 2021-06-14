package BlueJ;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountryExports{
    public void listExporters(CSVParser parser, String exportOfInterest)
    {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportOfInterest)) {

                String country = record.get("Country");
                System.out.println(country);
            }

        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    public static void main(String args[])
    {
        WhichCountryExports country=new WhichCountryExports();
        country.whoExportsCoffee();
    }

}