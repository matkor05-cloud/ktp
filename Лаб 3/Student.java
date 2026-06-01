public class Student {
    private String name;
    private String surname;
    private int age;
    private double averageGrade;

    public Student(String name, String surname, int age, double averageGrade) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return surname + " " + name + ", возраст: " + age + ", средний балл: " + averageGrade;
    }
}