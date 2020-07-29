import java.util.ArrayList;

class Marks_Main {
    private static ArrayList<Marks> marks = new ArrayList<>();

    public static void addStuds(Marks marks1) {
        marks.add(marks1);
    }

    public static ArrayList<Marks> getMarks() {
        return marks;
    }

    public static void setMarks(ArrayList<Marks> marks) {
        Marks_Main.marks = marks;
    }
}
