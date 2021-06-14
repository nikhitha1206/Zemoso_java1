package BlueJ;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class MiniProjectOnBabyNames {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank(int year,String name,String gender) {
        String s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob"+year+"short.csv";
        //String s = "C:\\Users\\DELL\\Desktop\\files\\babyNames\\data\\yob"+year+".csv";
        int m = 0;
        /*if (year == 2012)
            s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob2012short.csv";
        else if (year == 2013)
            s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob2013short.csv";
        else
            s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob2014short.csv";*/
        FileResource fr = new FileResource(s);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            String name1 = rec.get(0);
            String gender1 = rec.get(1);
            if (gender1.equals(gender))
                m=m+1;

            if (name1.equals(name) && gender1.equals(gender)) {
                return m;
            }
        }
            return -1;
    }
    public String getName(int year,int rank,String gender)
    {
        int m=0;
        String s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob"+year+"short.csv";
        //String s = "C:\\Users\\DELL\\Desktop\\files\\babyNames\\data\\yob"+year+".csv";
        /*if (year == 2012)
            s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob2012short.csv";
        else if (year == 2013)
            s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob2013short.csv";
        else
            s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob2014short.csv";*/
        FileResource fr = new FileResource(s);
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord rec:parser)
        {
            String name=rec.get(0);
            String gender1=rec.get(1);
            if(gender1.equals(gender))
                m=m+1;
            if(m==rank)
                return name;
        }
        return "NO NAME";
    }
    public void  whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int rank=getRank(year,name,gender);
        String name1=getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+name1+" if she was born in " +newYear+".");

    }
    public int  yearOfHighestRank(String name,String gender)
    {
        int year=0;
        int rank=-1;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()) {
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser(false);
            year = Integer.parseInt(f.getName().substring(3, 7));
            for(CSVRecord rec:parser) {
                int rank1=getRank(year,name,gender);
                if(rank==-1)
                    rank=rank1;
                else
                {
                    if(rank>rank1)
                        rank=rank1;
                }

            }
        }
        return rank;
    }
    public double getAverageRank(String name,String gender)
    {
        int n=0;
        double rank=-1.0;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser(false);
            int year = Integer.parseInt(f.getName().substring(3, 7));
            int rank1=getRank(year,name,gender);
            if(rank1!=-1)
            {
                n=n+1;
                if(rank==-1)
                    rank=rank1;
                else
                    rank+=rank1;
            }
        }
        if(rank==-1)
       return rank;
        return rank/n;
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        String s = "C:\\Users\\DELL\\Desktop\\files\\us_babynames_small\\testing\\yob"+year+"short.csv";
        //String s = "C:\\Users\\DELL\\Desktop\\files\\babyNames\\data\\yob"+year+".csv";
        int rank=getRank(year,name,gender);
        FileResource fr = new FileResource(s);
        CSVParser parser = fr.getCSVParser(false);
        int n=0;
        int m=0;
        for(CSVRecord rec:parser)
        {
            String gender1=rec.get(1);
            int number=Integer.parseInt(rec.get(2));
            if(gender.equals(gender1))
            {
                n=n+1;
                if(n<rank)
                {
                    m=m+number;
                }
            }
        }
        return m;
    }
    public static void main(String args[])
    {
        MiniProjectOnBabyNames project=new MiniProjectOnBabyNames();
        //System.out.println(project.getRank(1971,"Frank","M"));
        //System.out.println(project.getName(1982,450,"M"));
        //project.whatIsNameInYear("Owen",1974,2014,"M");
        //System.out.println(project.yearOfHighestRank("Genevieve","F"));
        //System.out.println(project.getAverageRank("mason","M"));
        System.out.println(project.getTotalBirthsRankedHigher(1990,"Emily","F"));
        //project.testTotalBirths ();
    }
}
