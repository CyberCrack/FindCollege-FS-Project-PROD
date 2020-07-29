class Student {
    private int age;
    private String contact;
    private String dob;
    private String email;
    private String fname;
    private String gender;
    private String lname;
    private String location;
    private String mname;
    private String password;
    private int student_id;

    public Student(String fname, String mname, String lname, String dob, String gender, String contact, String email, String location, String password) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.location = location;
        this.password = password;
    }

    public Student(int student_id, String fname, String lname, String dob, String gender, String email) {
        this.student_id = student_id;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
    }

    public Student(int student_id, String fname, String mname, String lname, String dob, String gender, int age, String email, String location) {
        this.student_id = student_id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
