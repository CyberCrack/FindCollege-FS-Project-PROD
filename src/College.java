class College {
    private int college_id;
    private String contact;
    private String email;
    private int fees;
    private String location;
    private String name;
    private String password;
    private float percentage;
    private String website;


    public College() {
    }

    public College(String name, float percentage, int fees, String location, String contact, String email, String website, String password) {
        this.name = name;
        this.percentage = percentage;
        this.fees = fees;
        this.location = location;
        this.contact = contact;
        this.email = email;
        this.website = website;
        this.password = password;
    }

    public void setCollege_id(int college_id) {
        this.college_id = college_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDetailsAsString() {
        return college_id + ":" + name + ":" + percentage + ":" + fees + ":" + location + ":" + contact + ":" + email + ":" + website;
    }
}
