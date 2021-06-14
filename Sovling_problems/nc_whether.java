package BlueJ;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class nc_whether {
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldSoFar=null;
        for(CSVRecord record:parser)
        {
            if(coldSoFar==null)
            {
                coldSoFar=record;
            }
            else
            {
                double temp=Double.parseDouble(record.get("TemperatureF"));
                double coldtemp=Double.parseDouble(coldSoFar.get("TemperatureF"));
                if(coldtemp>temp)
                {
                    coldtemp=temp;
                    coldSoFar=record;
                }
            }

        }
        return coldSoFar;
    }
    public void testColdestHourInFile()
    {
        FileResource fr=new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest=coldestHourInFile(parser);
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));

    }
    public String fileWithColdestTemperature()
    {
        DirectoryResource dr=new DirectoryResource();
        File temp=null;
        double globalColdest=Double.MAX_VALUE;
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            if(temp==null)
                temp=f;
            else
            {
                String t=coldestHourInFile(parser).get("TemperatureF");
                double cold=Double.parseDouble(t);
                if(cold<globalColdest)
                {
                    globalColdest=cold;
                    temp=f;
                }
            }

        }

        return temp.getName();
    }
    public void testFileWithColdestTemperature() {
        System.out.println("file that contains coldest temperature is =" + fileWithColdestTemperature());
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord humidity=null;
        for(CSVRecord record:parser)
        {
            if(humidity==null)
            {
                humidity=record;
            }
            else
            {
                double hum=Double.parseDouble(record.get("Humidity"));
                double coldHumidity=Double.parseDouble(humidity.get("Humidity"));
                if(hum<coldHumidity)
                {
                    coldHumidity=hum;
                    humidity=record;
                }
            }
        }
        return humidity;
    }
    public void testLowestHumidityInFile()
    {
        FileResource fr=new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest=lowestHumidityInFile(parser);
        System.out.println("coldest Humidity was " + coldest.get("Humidity") + " at " + coldest.get("TimeEST"));

    }
    public CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr=new DirectoryResource();
        CSVRecord lowestHumid=null;
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            CSVRecord humidFile = lowestHumidityInFile(parser);
                if (lowestHumid == null)
                    lowestHumid = humidFile;
                else
                {
                        double humid = Double.parseDouble(humidFile.get("Humidity"));
                        double globalhumid = Double.parseDouble(lowestHumid.get("Humidity"));
                        if (humid < globalhumid) {
                            lowestHumid = humidFile;
                    }
                }
        }
        return lowestHumid;
    }
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord lowest=lowestHumidityInManyFiles();
        System.out.println("lowest humidity from selected files was " + lowest.get("Humidity") + " at " + lowest.get("TimeEST"));
    }
    public double averageTemperatureInFile(CSVParser parser)
    {
        double sum=0;
        int n=0;
        for(CSVRecord record:parser)
        {
            sum=sum+Double.parseDouble(record.get("Humidity"));
            n=n+1;
        }
        return sum/n;

    }
    public void testAverageTemperatureInFile()
    {
        FileResource fr=new FileResource();
        CSVParser parser = fr.getCSVParser();
        double AverageHumidity=averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+AverageHumidity);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {
        double sum=0;
        int tn=0;
        int n=0;
        for(CSVRecord record:parser)
        {
            int val=Integer.parseInt(record.get("Humidity"));
            if(val>=value) {
                sum = sum + val;
                n = n + 1;
            }
            tn=tn+1;
        }
        if(n==0)
            return 0;
        return sum/tn;
    }
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr=new FileResource();
        CSVParser parser = fr.getCSVParser();
        double AverageHumidity=averageTemperatureWithHighHumidityInFile(parser,80);
        if(AverageHumidity!=0)
        System.out.println("Average temperature in file is "+AverageHumidity);
        else
            System.out.println("No temperatures with that humidity");
    }
    public static void main(String args[])
    {
        nc_whether nc=new nc_whether();
        //nc.testColdestHourInFile();
        //nc.testFileWithColdestTemperature();
        nc.testLowestHumidityInFile();
        //nc.testLowestHumidityInManyFiles();
        //nc.testAverageTemperatureInFile();
        //nc.testAverageTemperatureWithHighHumidityInFile();
    }
}
