import javax.swing.*;

class ExecApplication {
    public static boolean adminLoggedIn;
    public static boolean collegeLoggedIn;
    public static boolean userLoggedIn;
    public static String collegeEmail;
    public static String userEmail;
    public static boolean userSignedUp;
    public static boolean marksEntered;
    public static boolean isDev;

    public static void main(String[] args) {
        adminLoggedIn = false;
        collegeLoggedIn = false;
        userLoggedIn = false;
        userSignedUp = false;
        marksEntered = false;
        isDev = false;
        new AdminLogin("mayank@gmail.com", "password");
        MainFrame clg = new MainFrame();
        clg.jfrmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        clg.jfrmMain.setVisible(true);
        clg.setImage();

    }
}
