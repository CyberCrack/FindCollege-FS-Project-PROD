import java.util.ArrayList;

class College_Main {
    static ArrayList<College> clgs = new ArrayList<>();


    public static void addClg(College clg) {
        clgs.add(clg);
    }

    public static ArrayList<College> getClgs() {
        return clgs;
    }

    public static void setClgs(ArrayList<College> clgs) {
        College_Main.clgs = clgs;
    }

    public static ArrayList<College> searchCollegs() {
        return clgs;
    }
    /*public  static ArrayList<College> getClgs()
    {
        return clgs;
    }*/
    /*public static void setClgs(ArrayList<C>)
    {
        clgs =
    }*/

    /*static
    {
        clgs.add(new College("CLG-0001", "Atria Institute of Technology", 50));
        clgs.add(new College("CLG-0002", "Ramaiah Institute of Technology", 85));
        clgs.add(new College("CLG-0003", "BMS Institute of Technology and Management", 75));
        clgs.add(new College("CLG-0004", "RNS Institute of Technology", 60));
        clgs.add(new College("CLG-0005", "Ambedkar Institute of Technology", 80));
        clgs.add(new College("CLG-0006", "REVA", 65));
        clgs.add(new College("CLG-0007", "Dayananda Sagar College of Engineering", 70));
        clgs.add(new College("CLG-0008", "Bangalore Institute of Technology", 80));
        clgs.add(new College("CLG-0009", "BMS College of Engineering", 75));
        clgs.add(new College("CLG-0010", "Sapthagiri College of Engineering", 65));
        clgs.add(new College("CLG-0011", "East Point College of Engineering And Technology", 55));
        clgs.add(new College("CLG-0012", "Sir M. Visvesvaraya Institute of Technology", 87));
        clgs.add(new College("CLG-0013", "R. V. College of Engineering", 90));
        clgs.add(new College("CLG-0014", "PES University", 85));
        clgs.add(new College("CLG-0015", "New Horizon College of Engineering", 60));
        clgs.add(new College("CLG-0016", "Nitte Meenakshi Institute of Technology", 80));
        clgs.add(new College("CLG-0017", "CMR University", 60));
        clgs.add(new College("CLG-0018", "HKBK College of Engineering", 75));
        clgs.add(new College("CLG-0019", "Cambridge Institute of Technology", 70));
        clgs.add(new College("CLG-0020", "Rajiv Gandhi Institute of Technology", 65));
    }*/
}