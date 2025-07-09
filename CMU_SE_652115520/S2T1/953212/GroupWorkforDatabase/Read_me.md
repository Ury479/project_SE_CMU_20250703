# Read_me

## 652115520 YIHAO WANG

# Student Roster Sorting Application

## Description

This Java application reads a CSV file containing student information and allows the user to sort the list of students by their student number, first name, or last name. It is a command-line program designed as part of the coursework for cn.assignment3.

## Features

- Read student data from a CSV file.
- Sort students by number, first name, or last name.
- Command-line based user interaction.

## The result

```java
Usage: java RosterTest -[n|f|l|q] [file path]
Options:
  -n  Sort by student number
  -f  Sort by first name
  -l  Sort by last name
  -q  Quit
Enter command: -n
Roster [number=652115019, firstName=THANAPHAT FIRST, lastName=SANNGOEN]
Roster [number=652115027, firstName=PHORNSAWAN, lastName=SRIJANPANG]
Roster [number=652115043, firstName=WANIKKASIT, lastName=NOPTHIRAITTHIKUN]
Roster [number=652115050, firstName=SIRAPHOP, lastName=GUNTIYA]
Roster [number=652115059, firstName=XIAOYOU, lastName=FUNG]
Roster [number=652115520, firstName=YIHAO, lastName=WANG]
Roster [number=662115001, firstName=KIRANA, lastName=PRITSANAPANURUNGSEE]
Roster [number=662115003, firstName=CHATURAPHAT, lastName=SIRIRAK]
Roster [number=662115004, firstName=JIRAPAT, lastName=SAREERAT]
Roster [number=662115006, firstName=CHANAGUN, lastName=KHUNPHET]
Roster [number=662115007, firstName=CHONCHANUN, lastName=KHACHONPHURITHANAKUL]
Roster [number=662115008, firstName=CHAYODOM, lastName=JAISODSAI]
Roster [number=662115009, firstName=CHONTICHA, lastName=KUMMAYOM]
Roster [number=662115010, firstName=CHAWAPHAT, lastName=AKARAPHAT]
Roster [number=662115011, firstName=CHATCHAPONG, lastName=THANONGDEE]
Roster [number=662115012, firstName=CHINDANAI, lastName=JAIMAN]
Roster [number=662115013, firstName=TITIPON, lastName=TAWONG]
Roster [number=662115014, firstName=NATHAKORN, lastName=ARIYAJAK]
Roster [number=662115015, firstName=NATTHAPHONG, lastName=KANGKANTAM]
Roster [number=662115016, firstName=NATTHAPHUM, lastName=CHAIKHAN]
Roster [number=662115017, firstName=NICHAKAN, lastName=KAMJAITHIANG]
Roster [number=662115019, firstName=THIPPHARAKE, lastName=NA CHIENGMAI]
Roster [number=662115020, firstName=THANACHAI, lastName=NAKSOMBOON]
Roster [number=662115021, firstName=THANATHORN, lastName=TEEKAWONG]
Roster [number=662115023, firstName=THANATHIP, lastName=SUJITTHANANON]
Roster [number=662115024, firstName=TEERADA, lastName=BUN-IN]
Roster [number=662115025, firstName=TEERANATE, lastName=THOTHARAT]
Roster [number=662115052, firstName=SAENGCHAI, lastName=FUNGMONGKOLSATIAN]
Roster [number=662115053, firstName=MUANPEE, lastName=THISING]
Roster [number=662115054, firstName=ATIWIT TIN, lastName=INTASARN]
Roster [number=662115056, firstName=APHICHAYA, lastName=SUPPAKITKUMJORN]
Roster [number=662115057, firstName=APISIT, lastName=LIESMANN]
Roster [number=662115058, firstName=EAKKASIT, lastName=NIMANUSSORNKUL]
Roster [number=662115060, firstName=AILADA, lastName=RINRIT]
Roster [number=662115061, firstName=HASHIR, lastName=SUWANMALEE]
Roster [number=662115501, firstName=CHARISSE AREEYA, lastName=BARNETT]
Usage: java RosterTest -[n|f|l|q] [file path]
Options:
  -n  Sort by student number
  -f  Sort by first name
  -l  Sort by last name
  -q  Quit
Enter command: 
```

## Usage

To run the program, navigate to the directory containing the compiled `.class` files and execute the following command:

```
shCopy code
java RosterTest
```

Once the program starts, follow the on-screen instructions. You will be prompted with:

```
plaintextCopy codeUsage: java RosterTest -[n|f|l|q] [file path]
Options:
  -n  Sort by student number
  -f  Sort by first name
  -l  Sort by last name
  -q  Quit
Enter command:
```

Choose an option by typing `-n`, `-f`, `-l`, or `-q` and then pressing `Enter`.

## File Structure

- `RosterTest.java`: Main application file containing the `main` method and sorting logic.
- `Roster.java`: Class file defining the `Roster` object structure.
- `student.csv`: Example CSV file containing student data.

## Contributing

Feel free to fork the repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

## Contact

For any additional questions or comments, please email yihao_w@cmu.ac.th