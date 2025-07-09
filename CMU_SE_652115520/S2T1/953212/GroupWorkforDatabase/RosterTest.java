package cn.assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
/**YIHAO WANG 652115520*/
public class RosterTest {
    
public static void main(String[] args) {
	Vector<Roster> students = new Vector<Roster>();
	//pay attention to the address of the file
	/*"D:\\Onedrive\\Common20230824\\OneDrive - Chiang Mai University\\历史文件 不要乱删！！\\Desktop\\student.csv - 快捷方式.lnk"
	 * "D:/Dounload/student.csv" ok,"student.csv" is wrong.
	 * */
	File f = new File("D:/Dounload/student.csv");//"D:/953102_701000.csv" is ok,but could't operate the number,firstName,lastName
	try {
		Scanner stu = new Scanner(f);
		for (int i = 0; i < 7; i++) {
			stu.nextLine();
		}
		
		/**important*/
		while (stu.hasNextLine()) {
			String str = (String) stu.nextLine();
			String[] dataPairs = str.split(",");
			if (dataPairs.length >= 4) {
				String number = dataPairs[1].trim();
				String firstNumber = dataPairs[2].trim();
				String lastNumber = dataPairs[3].trim();
				students.add(new Roster(number, firstNumber, lastNumber));
			}
		}
		sortTheValue(students);
		stu.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		System.out.println("文件未找到: " + e.getMessage());
	}
}

public static void sortTheValue(Vector<Roster> students) {
    Scanner input = new Scanner(System.in);
    String command;
    do {
        System.out.println("Usage: java RosterTest -[n|f|l|q] [file path]");
        System.out.println("Options:");
        System.out.println("  -n  Sort by student number");
        System.out.println("  -f  Sort by first name");
        System.out.println("  -l  Sort by last name");
        System.out.println("  -q  Quit");
        
        System.out.print("Enter command: ");
        command = input.nextLine();
        
        switch (command) {
            case "-n":
                Collections.sort(students, Comparator.comparing(Roster::getNumber));
                students.forEach(System.out::println);
                break;
            case "-f":
                Collections.sort(students, Comparator.comparing(Roster::getFirstName));
                students.forEach(System.out::println);
                break;
            case "-l":
                Collections.sort(students, Comparator.comparing(Roster::getLastName));
                students.forEach(System.out::println);
                break;
            case "-q":
                System.out.println("Exiting program.");
                break;
            default:
                System.out.println("Invalid command! Try again.");
                break;
        }
    } while (!command.equals("-q"));
    
    input.close();
}
}