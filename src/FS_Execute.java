import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FS_Execute {
    static final String FS_FolderName = "FS-Database\\";


    // Example of Write
    int InsertValues(College clg) throws Exception {
        String fileName = "Colleges.txt"; // This is the File Name of the table i.e. Colleges.txt
        File file = new File(FS_FolderName + fileName);
        ViewAllColleges();
        for (College college : College_Main.clgs) {
            if (college.getName().equals(clg.getName()) || college.getEmail().equals(clg.getEmail()) || college.getWebsite().equals(clg.getWebsite())) return 1;
        }
        ArrayList<String> fieldsToWrite = new ArrayList<>();
        fieldsToWrite.add(clg.getName());
        fieldsToWrite.add(String.valueOf(clg.getPercentage()));
        fieldsToWrite.add(String.valueOf(clg.getFees()));
        fieldsToWrite.add(clg.getLocation());
        fieldsToWrite.add(clg.getContact());
        fieldsToWrite.add(clg.getEmail());
        fieldsToWrite.add(clg.getWebsite());
        // NOTE: "College_IDs.txt" contains all the used IDs so after reading the last line we will generate the next number as the new ID.
        int newClgID = getNewClgID_O_1(); // Get new ID.
        writeTheseFields(file, fieldsToWrite, newClgID);
        String lineToWrite;
        fileName = "Login_Colleges.txt";
        file = new File(FS_FolderName + fileName);
        fieldsToWrite.add(String.valueOf(newClgID));
        fieldsToWrite.add(clg.getEmail());
        fieldsToWrite.add(clg.getPassword());
        lineToWrite = String.join("|", fieldsToWrite);
        writeTheseFields(file, fieldsToWrite, lineToWrite);
        fileName = "College_Name_ID.txt";
        file = new File(FS_FolderName + fileName);
        fieldsToWrite.add(clg.getName());
        fieldsToWrite.add(String.valueOf(newClgID));
        lineToWrite = String.join("|", fieldsToWrite);
        writeTheseFields(file, fieldsToWrite, lineToWrite);
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
        return 0;
    }

    void writeTheseFields(File file, ArrayList<String> fieldsToWrite, int newID) throws IOException {
        fieldsToWrite.add(0, String.valueOf(newID)); // Insert the ID at index 0 to follow the structure.
        writeTheseFields(file, fieldsToWrite);
    }

    // Examples of Read.
    void ViewAllColleges() throws IOException {
        String fileName = "Colleges.txt"; // This is the File Name of the table i.e. Colleges.txt
        File file = new File(FS_FolderName + fileName);
        Scanner scanner = new Scanner(file);
        String readLine; // This to read the line from the TXT file.
        String[] currentLine; // This line which was read before was of a STRING datatype we need to split each field and store it as ARRAY.
        College_Main.clgs.clear();
        while (scanner.hasNextLine()) {
            readLine = scanner.nextLine();
            currentLine = readLine.split("\\|");
            College college = new College();
            college.setCollege_id(Integer.parseInt(currentLine[0]));
            college.setName(currentLine[1]);
            college.setPercentage(Float.parseFloat(currentLine[2]));
            college.setFees(Integer.parseInt(currentLine[3]));
            college.setLocation(currentLine[4]);
            college.setContact(currentLine[5]);
            college.setEmail(currentLine[6]);
            college.setWebsite(currentLine[7]);
            College_Main.addClg(college);
        }
        scanner.close();
    }

    int getNewClgID_O_1() {
        String fileName = "College_IDs.txt";
        return getNewID(fileName);
    }

    int getNewID(String fileName) {
        File file = new File(FS_FolderName + fileName);
        int newID = Integer.parseInt(readLastLine(file));
        newID++;
        try (FileWriter f = new FileWriter(FS_FolderName + fileName, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
            p.println(newID); // Writes newClgID to the text file.

        } catch (IOException i) {
            i.printStackTrace();
        }
        return newID;
    }

    void InsertValues(Student student) throws Exception {
        String fileName = "Students.txt"; // This is the File Name of the table i.e. Students.txt
        File file = new File(FS_FolderName + fileName);
        ArrayList<String> fieldsToWrite = new ArrayList<>();
        fieldsToWrite.add(student.getFname());
        fieldsToWrite.add(student.getMname());
        fieldsToWrite.add(student.getLname());
        fieldsToWrite.add(student.getDob());
        fieldsToWrite.add(student.getGender());
        fieldsToWrite.add(String.valueOf(student.getAge()));
        fieldsToWrite.add(student.getContact());
        fieldsToWrite.add(student.getEmail());
        fieldsToWrite.add(student.getLocation());
        int newStudentID = getNewStudentID_O_1(); // Get new ID.
        writeTheseFields(file, fieldsToWrite, newStudentID);
        calculateAge();

        String lineToWrite;
        fileName = "Login_Students.txt";
        file = new File(FS_FolderName + fileName);
        fieldsToWrite.add(String.valueOf(newStudentID));
        fieldsToWrite.add(student.getEmail());
        fieldsToWrite.add(student.getPassword());
        lineToWrite = String.join("|", fieldsToWrite);
        writeTheseFields(file, fieldsToWrite, lineToWrite);
    }

    int getNewStudentID_O_1() throws IOException {
        String fileName = "Student_IDs.txt";
        return getNewID(fileName);
    }

    void InsertValues(Marks marks) throws Exception {
        String fileName = "Student_IDs.txt";
        File file = new File(FS_FolderName + fileName);
        int studentID = Integer.parseInt(readLastLine(file).split("\\|")[0]);
        fileName = "Marks.txt"; // This is the File Name of the table i.e. Students.txt
        file = new File(FS_FolderName + fileName);
        ArrayList<String> fieldsToWrite = new ArrayList<>();
        fieldsToWrite.add(String.valueOf(studentID));
        fieldsToWrite.add(String.valueOf(marks.getEnglish()));
        fieldsToWrite.add(String.valueOf(marks.getHindiKannada()));
        fieldsToWrite.add(String.valueOf(marks.getMaths()));
        fieldsToWrite.add(String.valueOf(marks.getPhysics()));
        fieldsToWrite.add(String.valueOf(marks.getChemistry()));
        fieldsToWrite.add(String.valueOf(marks.getComputerBiology()));
        fieldsToWrite.add(String.valueOf(marks.getTotalMarks()));
        fieldsToWrite.add(String.valueOf(marks.getPercentage()));
        writeTheseFields(file, fieldsToWrite);
        calculateTotalAvg();
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
    }


    String readLastLine(File file) {
        // COMMON FUNCTION
        // Return the last line of the given file as a <String> datatype.
        /*
            USAGE:
            File file = new File("C:\\FS-Database\Colleges.txt");
            String lastLine = readLastLine(file);
            // If you expect the last line to be <Integer> then convert it.
            int lastLineAsInt = Integer.parseInt(lastLine);
         */
        StringBuilder builder = new StringBuilder();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            long fileLength = file.length() - 1;
            randomAccessFile.seek(fileLength);
            if ((char) randomAccessFile.read() == '\n')
                fileLength--;
            for (long pointer = fileLength; pointer >= 0; pointer--) {
                randomAccessFile.seek(pointer);
                char c;
                c = (char) randomAccessFile.read();
                if (c == '\n') {
                    break;
                }
                builder.append(c);
            }
            builder.reverse();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString().strip();
    }

    void writeTheseFields(File file, ArrayList<String> fieldsToWrite) throws IOException {
        String lineToWrite = String.join("|", fieldsToWrite);
        writeTheseFields(file, fieldsToWrite, lineToWrite);
    }

    void calculateTotalAvg() throws IOException {
        String fileName = "Marks.txt";
        File file = new File(FS_FolderName + fileName);
        ArrayList<String> stringArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            stringArrayList.add(scanner.nextLine().strip()); // Reads each line of the file.
        }
        scanner.close();
        try (FileWriter f = new FileWriter(file, false); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
            for (String s : stringArrayList) {
                String[] marksLine = s.split("\\|");
                String[] marks = Arrays.copyOfRange(marksLine, 1, marksLine.length - 2);
                float sum = 0.0f;
                for (String mark : marks) {
                    sum += Integer.parseInt(mark);
                }
                float percentage = sum / marks.length;
                marksLine[7] = String.valueOf(sum);
                marksLine[8] = String.valueOf(percentage);
                String lineToWrite = String.join("|", marksLine);
                p.println(lineToWrite);
            }
        } catch (
                IOException i) {
            i.printStackTrace();
        }
    }

    void writeTheseFields(File file, ArrayList<String> fieldsToWrite, String lineToWrite) throws IOException {
        try (FileWriter f = new FileWriter(file, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
            p.println(lineToWrite);

        } catch (IOException i) {
            i.printStackTrace();
        }
        fieldsToWrite.clear();
        sort(file, 0);
    }

    static void sort(File file, int key) throws IOException {
        List<String> arrayList = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        String temp;
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = i + 1; j < arrayList.size(); j++) {
                if (arrayList.get(i).split("\\|")[key].compareTo(arrayList.get(j).split("\\|")[key]) > 0) {
                    temp = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, temp);
                }
            }
        }
        try (FileWriter f = new FileWriter(file, false); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
            for (String s : arrayList) {
                p.println(s); // Writes s to the text file.
            }

        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    void calculateAge() throws FileNotFoundException {
        String fileName = "Students.txt";
        File file = new File(FS_FolderName + fileName);
        ArrayList<String> stringArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            stringArrayList.add(scanner.nextLine().strip()); // Reads each line of the file.
        }
        scanner.close();
        try (FileWriter f = new FileWriter(file, false); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
            for (String s : stringArrayList) {
                String[] studentsInfo = s.split("\\|");
                String dob = studentsInfo[4];
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                LocalDate localDateDOB;
                try {
                    localDateDOB = LocalDate.parse(dob, formatter);
                } catch (DateTimeParseException e) {
                    String format;
                    String[] dobFields = dob.split("-");
                    if (dobFields[1].length() == 1 && dobFields[2].length() == 1)
                        format = "yyy-M-d";
                    else if (dobFields[1].length() == 2 && dobFields[2].length() == 1)
                        format = "yyy-MM-d";
                    else
                        format = "yyyy-M-dd";
                    formatter = DateTimeFormatter.ofPattern(format);
                    localDateDOB = LocalDate.parse(dob, formatter);

                }

                Period period = Period.between(localDateDOB, LocalDate.now());
                studentsInfo[6] = String.valueOf(period.getYears());
                String lineToWrite = String.join("|", studentsInfo);
                p.println(lineToWrite);
            }
        } catch (
                IOException i) {
            i.printStackTrace();
        }
    }

    int CollegeLogin(String email, String password) throws IOException {
        File file = new File(FS_FolderName + "CollegesPointerEmail.txt");
        String found = search(file, email);
        if (found.equals("false"))
            return 1;
        long pos = Long.parseLong(found.split("\\|")[2]);
        file = new File(FS_FolderName + "Login_Colleges.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        randomAccessFile.seek(pos);
        String[] emailPasswordLine = randomAccessFile.readLine().strip().split("\\|");
        if (password.equals(emailPasswordLine[2]))
            return 0;
        else
            return 1;
    }

    static String search(File file, String key) throws IOException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        ArrayList<Long> arrayListPos = new ArrayList<>();
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            arrayListPos.add(randomAccessFile.getFilePointer());
            stringArrayList.add(randomAccessFile.readLine().strip().split("\\|")[0]);
        }
        int l = 0, r = stringArrayList.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            int res = key.compareTo(stringArrayList.get(m));

            // Check if x is present at mid
            if (res == 0) {
                randomAccessFile.seek(arrayListPos.get(m));
                String found = randomAccessFile.readLine();
                randomAccessFile.close();
                return found;
            }

            // If x greater, ignore left half
            if (res > 0)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }
        randomAccessFile.close();
        return "false";
    }

    void CollegeUpdate(float percentage, String email) throws IOException {
        File file1 = new File(FS_FolderName + "CollegesPointerEmail.txt");
        File file2 = new File(FS_FolderName + "Colleges.txt");
        int[] keyFieldsPointers = new int[]{1};
        String updatedValue = String.valueOf(percentage);
        String oldValue = email;
        int[] fieldIndexesToChange = new int[]{2};
        UpdateWithNewValue(new File[]{file1, file2}, keyFieldsPointers, updatedValue, oldValue, fieldIndexesToChange);
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
    }

    void UpdateWithNewValue(File[] files, int[] keyFieldsPointers, String updatedValue, String oldValue, int[] fieldIndexesToChange) throws IOException {
        // 1st element of <files> should be a pointer.txt.
        if (files.length - 1 != fieldIndexesToChange.length) {
            System.out.println("Variable Lengths in the parameters.\nCannot do the update.");
        }
        String found = search(files[0], oldValue);
        long[] pos = new long[fieldIndexesToChange.length];
        RandomAccessFile[] randomAccessFiles = new RandomAccessFile[fieldIndexesToChange.length];
        for (int i = 0; i < fieldIndexesToChange.length; i++) {
            pos[i] = Long.parseLong(found.split("\\|")[keyFieldsPointers[i]]);
            randomAccessFiles[i] = new RandomAccessFile(files[i + 1], "r");
            randomAccessFiles[i].seek(pos[i]);
            String oldString = randomAccessFiles[i].readLine().strip();
            randomAccessFiles[i].close();
            String[] oldStringFields = oldString.split("\\|");
            oldStringFields[fieldIndexesToChange[i]] = updatedValue.strip();
            String updatedString = String.join("|", oldStringFields);
            update(files[i + 1], updatedString, pos[i]);
        }
    }

    static void update(File file, String updatedString, long pos) throws IOException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            if (randomAccessFile.getFilePointer() != pos) {
                stringArrayList.add(randomAccessFile.readLine());
            } else {
                stringArrayList.add(updatedString);
                randomAccessFile.readLine();
            }
        }
        randomAccessFile.close();
        try (FileWriter f = new FileWriter(file, false); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
            for (String s : stringArrayList) {
                p.println(s); // Writes s to the text file.
            }
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    void CollegeUpdate(int fees, String email) throws IOException {
        File file1 = new File(FS_FolderName + "CollegesPointerEmail.txt");
        File file2 = new File(FS_FolderName + "Colleges.txt");
        int[] keyFieldsPointers = new int[]{1};
        String updatedValue = String.valueOf(fees);
        String oldValue = email;
        int[] fieldIndexesToChange = new int[]{3};
        UpdateWithNewValue(new File[]{file1, file2}, keyFieldsPointers, updatedValue, oldValue, fieldIndexesToChange);
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
    }

    void CollegeUpdate(String contact, String email) throws IOException {
        File file1 = new File(FS_FolderName + "CollegesPointerEmail.txt");
        File file2 = new File(FS_FolderName + "Colleges.txt");
        int[] keyFieldsPointers = new int[]{1};
        String updatedValue = contact;
        String oldValue = email;
        int[] fieldIndexesToChange = new int[]{5};
        UpdateWithNewValue(new File[]{file1, file2}, keyFieldsPointers, updatedValue, oldValue, fieldIndexesToChange);
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
    }

    void DeleteCollege(String nameOrEmail) throws IOException {
        if (!nameOrEmail.contains("@")) {
            File file = new File(FS_FolderName + "CollegesPointerName.txt");
            String found = search(file, nameOrEmail);
            file = new File(FS_FolderName + "Colleges.txt");
            long pos = Long.parseLong(found.split("\\|")[1]);
            String deletedString = delete(file, pos);
            String key = deletedString.split("\\|")[0];
            file = new File(FS_FolderName + "CollegesPointers.txt");
            found = search(file, key);
            file = new File(FS_FolderName + "Login_Colleges.txt");
            pos = Long.parseLong(found.split("\\|")[2]);
            delete(file, pos);
        } else {
            File file = new File(FS_FolderName + "CollegesPointerEmail.txt");
            String found = search(file, nameOrEmail);
            file = new File(FS_FolderName + "Colleges.txt");
            long pos = Long.parseLong(found.split("\\|")[1]);
            delete(file, pos);
            file = new File(FS_FolderName + "Login_Colleges.txt");
            pos = Long.parseLong(found.split("\\|")[2]);
            delete(file, pos);
        }
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
        College_Main.clgs.clear();
    }

    static String delete(File file, long pos) throws IOException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        String deletedString = "";
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            if (randomAccessFile.getFilePointer() != pos) {
                stringArrayList.add(randomAccessFile.readLine().strip());
            } else deletedString = randomAccessFile.readLine().strip();
        }
        randomAccessFile.close();
        try (FileWriter f = new FileWriter(file, false); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
            for (String s : stringArrayList) {
                p.println(s); // Writes s to the text file.
            }

        } catch (IOException i) {
            i.printStackTrace();
        }
        return deletedString;
    }


    void StudentLoginUpdate(String newEmail, String email) throws IOException {
        File file1 = new File(FS_FolderName + "StudentsPointerEmail.txt");
        File file2 = new File(FS_FolderName + "Students.txt");
        File file3 = new File(FS_FolderName + "Login_Students.txt");
        int[] keyFieldsPointers = new int[]{1, 2};
        String updatedValue = newEmail;
        String oldValue = email;
        int[] fieldIndexesToChange = new int[]{8, 1};
        UpdateWithNewValue(new File[]{file1, file2, file3}, keyFieldsPointers, updatedValue, oldValue, fieldIndexesToChange);
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
    }

    void StudentContactUpdate(String contact, String email) throws IOException {
        File file1 = new File(FS_FolderName + "StudentsPointerEmail.txt");
        File file2 = new File(FS_FolderName + "Students.txt");
        int[] keyFieldsPointers = new int[]{1};
        String updatedValue = contact;
        String oldValue = email;
        int[] fieldIndexesToChange = new int[]{7};
        UpdateWithNewValue(new File[]{file1, file2}, keyFieldsPointers, updatedValue, oldValue, fieldIndexesToChange);
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
    }

    void StudentLocationUpdate(String location, String email) throws IOException {
        File file1 = new File(FS_FolderName + "StudentsPointerEmail.txt");
        File file2 = new File(FS_FolderName + "Students.txt");
        int[] keyFieldsPointers = new int[]{1};
        String updatedValue = location;
        String oldValue = email;
        int[] fieldIndexesToChange = new int[]{9};
        UpdateWithNewValue(new File[]{file1, file2}, keyFieldsPointers, updatedValue, oldValue, fieldIndexesToChange);
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
    }

    int StudentLogin(String email, String password) throws IOException {
        File file = new File(FS_FolderName + "StudentsPointerEmail.txt");
        String found = search(file, email);
        if (found.equals("false"))
            return 1;
        long pos = Long.parseLong(found.split("\\|")[2]);
        file = new File(FS_FolderName + "Login_Students.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        randomAccessFile.seek(pos);
        String[] emailPasswordLine = randomAccessFile.readLine().strip().split("\\|");
        if (password.equals(emailPasswordLine[2]))
            return 0;
        else
            return 1;
    }

    void StudentUpdate(String newValue, String email, int type) throws IOException {
        if (type == 1) StudentContactUpdate(newValue, email);
        else if (type == 2) {
            StudentLoginUpdate(newValue, email);
        } else if (type == 3) StudentLocationUpdate(newValue, email);

    }

    void DeleteStudent(String idOrEmail) throws IOException {
        if (!idOrEmail.contains("@")) {
            File file = new File(FS_FolderName + "StudentsPointers.txt");
            String found = search(file, idOrEmail);
            file = new File(FS_FolderName + "Students.txt");
            long pos = Long.parseLong(found.split("\\|")[1]);
            delete(file, pos);
            file = new File(FS_FolderName + "Login_Students.txt");
            pos = Long.parseLong(found.split("\\|")[2]);
            delete(file, pos);
            file = new File(FS_FolderName + "Marks.txt");
            pos = Long.parseLong(found.split("\\|")[4]);
            delete(file, pos);
        } else {
            File file = new File(FS_FolderName + "StudentsPointerEmail.txt");
            String found = search(file, idOrEmail);
            file = new File(FS_FolderName + "Students.txt");
            long pos = Long.parseLong(found.split("\\|")[1]);
            String deletedString = delete(file, pos);
            String studentID = deletedString.split("\\|")[0];
            file = new File(FS_FolderName + "Login_Students.txt");
            pos = Long.parseLong(found.split("\\|")[2]);
            delete(file, pos);
            file = new File(FS_FolderName + "StudentsPointers.txt");
            found = search(file, studentID);
            file = new File(FS_FolderName + "Marks.txt");
            pos = Long.parseLong(found.split("\\|")[4]);
            delete(file, pos);

        }
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createPointerRecords();
        Student_Main.studs.clear();
    }

    void GetColleges(String email) throws Exception {
        calculateTotalAvg();
        College_Main.clgs.clear();
        File file = new File(FS_FolderName + "StudentsPointerEmail.txt");
        String found = search(file, email);
        long pos = Long.parseLong(found.split("\\|")[1]);
        file = new File(FS_FolderName + "Students.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        randomAccessFile.seek(pos);
        String studentID = randomAccessFile.readLine().strip().split("\\|")[0];
        randomAccessFile.close();
        file = new File(FS_FolderName + "Marks.txt");
        found = search(file, studentID);
        String[] marks = found.split("\\|");
        float sum = 0.0f;
        float percentage;

        if (marks[8].equals("0") || marks[8].isEmpty()) {
            for (int i = 1; i < 7; i++) {
                sum += Integer.parseInt(marks[i]);
            }
            percentage = sum / 6;
        } else
            percentage = Float.parseFloat(marks[8]);
        file = new File(FS_FolderName + "Colleges.txt");
        Scanner scanner = new Scanner(file);
        String readLine;
        while (scanner.hasNextLine()) {
            readLine = scanner.nextLine().strip();
            String[] clgFields = readLine.split("\\|");
            if (percentage >= Float.parseFloat(clgFields[2])) {
                College college = new College();
                college.setCollege_id(Integer.parseInt(clgFields[0]));
                college.setName(clgFields[1]);
                college.setPercentage(Float.parseFloat(clgFields[2]));
                college.setFees(Integer.parseInt(clgFields[3]));
                college.setLocation(clgFields[4]);
                college.setContact(clgFields[5]);
                college.setEmail(clgFields[6]);
                college.setWebsite(clgFields[7]);
                College_Main.addClg(college);
            }
        }
    }

    String getStudentName(String email) throws IOException {
        File file = new File(FS_FolderName + "StudentsPointerEmail.txt");
        String found = search(file, email);
        long pos = Long.parseLong(found.split("\\|")[1]);
        file = new File(FS_FolderName + "Students.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        randomAccessFile.seek(pos);
        String lineRead = randomAccessFile.readLine().strip();
        return lineRead.split("\\|")[1];
    }
}
