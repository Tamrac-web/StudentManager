public class Student {

    private String id ;

    private String name ;

    private String grade ;



    public Student() {

    }

    public Student(String[] s) {

        this.id = s[0];

        this.name = s[1];

        this.grade = s[2];



    }

    public Student(String id, String name, String grade) {

        this.id = id;

        this.name = name;

        this.grade = grade;



    }

    public String getid() {

        return id;

    }

    public void setid(String id) {

        this.id = id;

    }

    public String getname() {

        return name;

    }

    public void setname(String name) {

        this.name = name;

    }

    public String getgrade() {

        return grade;

    }

    public void setgrade(String grade) {

        this.grade = grade;

    }


}
