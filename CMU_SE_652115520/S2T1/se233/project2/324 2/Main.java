import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.math.BigDecimal;

public class Main {
    public static <UniData> void main(String args[]) throws IOException {
        Vector<UniData> myUni = new Vector<UniData>();
        System.out.println("Program start!");
        Scanner sc = new Scanner(new File("C:\\Users\\10677\\Desktop\\biancc\\大三上\\database\\324\\QS-World-University-Rankings-2017.csv"));  
        sc.useDelimiter(",");  
        sc.nextLine();
        while (sc.hasNextLine()) {  
            UniData u = new UniData(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
            u.setUniName(u.getUniName().toLowerCase());

            if(u.getScore().isEmpty()){
                u.setScore("0");
            }

            BigDecimal score = new BigDecimal(u.getScore());
             u.setScore(score.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
  
            if(isNumeric(u.getRank())){
                myUni.add(u);
            }
            
            sc.nextLine();
        } 
        Collections.sort(myUni);
        String csvFilePath = "C:\\Users\\10677\\Desktop\\biancc\\大三上\\database\\324\\QS-World-University-Rankings-2017.csv";
        try(FileWriter fileWriter = new FileWriter(csvFilePath)){fileWriter.write("Year,Rank,University Name, Score,Link,Country,City,Logo\n");
            for(UniData uni : myUni){
                fileWriter.write(uni.getYear() + ", " + uni.getRank() + ", " + uni.getUniName() + ", " + uni.getScore() +
                " ," + uni.getLink() + ", " + uni.getCountry() + ", " + uni.getCity() + ", " + uni.getLogo() + "\n");
            }
            System.out.println("Data saved to " + csvFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (UniData uni: myUni) {
            System.out.println(uni.toString());
        }
        sc.close(); 

        UniData newUni = new UniData();
        newUni.setCity("Chiang Mai");
        myUni.add(newUni);
        System.out.println("Program terminate properly!");
       
    } 

    public static boolean isNumeric(String str) {
        try{
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e ){
            return false;
        }
    }
}


