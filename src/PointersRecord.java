import java.io.*;
import java.util.Arrays;

public class PointersRecord {

    void createPointerRecords() throws IOException {
        PointersRecord pointersRecord = new PointersRecord();
        pointersRecord.createWithID();
        pointersRecord.createWithKey();
        pointersRecord.CreateWithName();
    }

    void createWithID() throws IOException {
        String FS_FolderName = "FS-Database\\";
        PointersRecord pointersRecord = new PointersRecord();
        File file1 = new File(FS_FolderName + "Colleges.txt");
        File file2 = new File(FS_FolderName + "Login_Colleges.txt");
        File file3 = new File(FS_FolderName + "College_IDS.txt");
        pointersRecord.createPointersTxt("CollegesPointers.txt", FS_FolderName, file1, file2, file3);
        file1 = new File(FS_FolderName + "Students.txt");
        file2 = new File(FS_FolderName + "Login_Students.txt");
        file3 = new File(FS_FolderName + "Student_IDs.txt");
        File file4 = new File(FS_FolderName + "Marks.txt");
        pointersRecord.createPointersTxt("StudentsPointers.txt", FS_FolderName, file1, file2, file3, file4);

    }

    void createWithKey() throws IOException {
        String FS_FolderName = "FS-Database\\";
        PointersRecord pointersRecord = new PointersRecord();
        File file1 = new File(FS_FolderName + "Colleges.txt");
        File file2 = new File(FS_FolderName + "Login_Colleges.txt");
        pointersRecord.createPointersTxtWithKey("CollegesPointerEmail.txt", FS_FolderName, new int[]{6, 1}, file1, file2);
        file1 = new File(FS_FolderName + "Students.txt");
        file2 = new File(FS_FolderName + "Login_Students.txt");
        pointersRecord.createPointersTxtWithKey("StudentsPointerEmail.txt", FS_FolderName, new int[]{8, 1}, file1, file2);

    }

    void CreateWithName() throws IOException {
        String FS_FolderName = "FS-Database\\";
        PointersRecord pointersRecord = new PointersRecord();
        File file1 = new File(FS_FolderName + "Colleges.txt");
        pointersRecord.createPointersTxtWithKey("CollegesPointerName.txt", FS_FolderName, new int[]{1}, file1);

    }

    void createPointersTxt(String filename, String dest, File... files) throws IOException {
        File pointerTxt = new File(dest + filename);
        pointerTxt.delete();
        pointerTxt.createNewFile();
        RandomAccessFile[] randomAccessFiles = new RandomAccessFile[files.length];
        for (int i = 0; i < files.length; i++) {
            randomAccessFiles[i] = new RandomAccessFile(files[i], "r");
        }
        String[] fileLines = new String[files.length];
        int[] ids = new int[files.length];
        long[] pos = new long[files.length];
        while (randomAccessFiles[0].getFilePointer() < randomAccessFiles[0].length()) {
            for (int i = 0; i < files.length; i++) {
                pos[i] = randomAccessFiles[i].getFilePointer();
                fileLines[i] = randomAccessFiles[i].readLine();
                ids[i] = Integer.parseInt(fileLines[i].split("\\|")[0]);
                if (i != 0) {
                    while (ids[i] != ids[0]) {
                        pos[i] = randomAccessFiles[i].getFilePointer();
                        fileLines[i] = randomAccessFiles[i].readLine();
                        ids[i] = Integer.parseInt(fileLines[i].split("\\|")[0]);
                    }
                }
            }
            StringBuilder lineToWrite = new StringBuilder(String.valueOf(ids[0]));
            for (int i = 0; i < files.length; i++) {
                lineToWrite.append("|").append(pos[i]);
            }
            try (FileWriter f = new FileWriter(pointerTxt, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
                p.println(lineToWrite);

            } catch (IOException i) {
                i.printStackTrace();
            }
        }
        FS_Execute.sort(pointerTxt, 0);
    }

    void createPointersTxtWithKey(String filename, String dest, int[] indexKeys, File... files) throws IOException {
        if (indexKeys.length != files.length) {
            System.out.println("Length of keys and files differ");
            return;
        }
        File pointerTxt = new File(dest + filename);
        pointerTxt.delete();
        pointerTxt.createNewFile();
        RandomAccessFile[] randomAccessFiles = new RandomAccessFile[files.length];
        for (int i = 0; i < files.length; i++) {
            randomAccessFiles[i] = new RandomAccessFile(files[i], "r");
        }
        String[] fileLines = new String[files.length];
        String[] keys = new String[files.length];
        long[] pos = new long[files.length];
        while (randomAccessFiles[0].getFilePointer() < randomAccessFiles[0].length()) {
            for (int i = 0; i < files.length; i++) {
                pos[i] = randomAccessFiles[i].getFilePointer();
                fileLines[i] = randomAccessFiles[i].readLine();
                keys[i] = fileLines[i].split("\\|")[indexKeys[i]];
                if (i != 0) {
                    while (!keys[i].equals(keys[0])) {
                        pos[i] = randomAccessFiles[i].getFilePointer();
                        fileLines[i] = randomAccessFiles[i].readLine();
                        keys[i] = fileLines[i].split("\\|")[indexKeys[i]];
                    }
                }
            }
            StringBuilder lineToWrite = new StringBuilder(String.valueOf(keys[0]));
            for (int i = 0; i < files.length; i++) {
                lineToWrite.append("|").append(pos[i]);
            }
            try (FileWriter f = new FileWriter(pointerTxt, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b)) {
                p.println(lineToWrite);

            } catch (IOException i) {
                i.printStackTrace();
            }
        }
        FS_Execute.sort(pointerTxt, 0);
    }
}
