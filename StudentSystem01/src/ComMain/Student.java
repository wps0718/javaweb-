package ComMain;

public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String grade;
    private String class_;
    private String college;
    private String studentId;
    private String subject;
    private double score;

    // 构造函数
    public Student(int id, String name, int age, String gender, String grade, String class_, String college, String studentId, String subject, double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.class_ = class_;
        this.college = college;
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
    }

    // 无参构造函数
    public Student() {
    }

    // Getter 和 Setter 方法
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}



