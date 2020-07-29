import java.util.ArrayList;

class Student_Main {
    static ArrayList<Student> studs = new ArrayList<>();

    public static void addStuds(Student stud) {
        studs.add(stud);
    }

    public static ArrayList<Student> getStuds() {
        return studs;
    }

    public static void setStuds(ArrayList<Student> studs) {
        Student_Main.studs = studs;
    }

}
