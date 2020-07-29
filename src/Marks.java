class Marks {
    private int chemistry;
    private int computerBiology;
    private int english;
    private int hindiKannada;
    private int maths;
    private float percentage;
    private int physics;
    private int student_id;
    private int totalMarks;

    public Marks(int english, int hindiKannada, int maths, int physics, int chemistry, int computerBiology) {
        this.english = english;
        this.hindiKannada = hindiKannada;
        this.maths = maths;
        this.physics = physics;
        this.chemistry = chemistry;
        this.computerBiology = computerBiology;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getHindiKannada() {
        return hindiKannada;
    }

    public void setHindiKannada(int hindiKannada) {
        this.hindiKannada = hindiKannada;
    }

    public int getMaths() {
        return maths;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getChemistry() {
        return chemistry;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }


    public int getComputerBiology() {
        return computerBiology;
    }

    public void setComputerBiology(int computerBiology) {
        this.computerBiology = computerBiology;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
