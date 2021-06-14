package BlueJ;
import edu.duke.*;
import org.apache.commons.csv.*;
public class export {
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
       // System.out.println("number of exports of gold ="+numberOfExporters(parser,"gold"));
        //System.out.println("CountryInfo:");
        //System.out.println(countryInfo(parser,"Germany"));
        //listExportersTwoProducts(parser,"gold","diamonds");
        bigExporters(parser,"$999,999,999");
    }
    public String countryInfo(CSVParser parser,String country)
    {
        for(CSVRecord record:parser)
        {
            String coun=record.get("Country");
            if(coun.equals(country))
            {
                String exports=record.get("Exports");
                String value=record.get("Value (dollars)");
                return coun+": "+exports+": "+value;
            }
        }
       return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2)
    {
        for(CSVRecord record:parser)
        {
            String exports=record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                String country=record.get("Country");
                System.out.println(country);
            }
        }
    }
    public static int numberOfExporters(CSVParser parser,String exportItem)
    {
        int n=0;
        for(CSVRecord record:parser)
        {
            String exports=record.get("Exports");
            if(exports.contains(exportItem))
            {
                n=n+1;
            }
        }
        return n;
    }
    public void bigExporters(CSVParser parser,String amount)
    {
        for(CSVRecord record:parser)
        {
            String value=record.get("Value (dollars)");
            if(value.length()>amount.length())
            {
                String country=record.get("Country");
                System.out.println(country+" "+value);
            }
        }
    }
    public static void main(String args[])
    {
        export ex=new export();
        ex.tester();

    }
}
