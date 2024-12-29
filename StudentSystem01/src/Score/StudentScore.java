package Score;
public class StudentScore {
    private int id;
    private String name;
    private String studentId;
    private int age;
    private String gender;
    private double score;
    private String subject;
    private String grade;
    private String className;

    // Constructors
    public StudentScore(int id, String name, String studentId, int age, String gender, String grade, String className, String subject, double score) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.age = age;
        this.gender = gender;
        this.grade = grade; // 新增字段
        this.className = className; // 新增字段
        this.subject = subject;
        this.score = score;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String Grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
