import java.util.Scanner;

public class StudentMemoryDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Marks: ");
        int marks = sc.nextInt();
        System.out.println();

        Student s = new Student(age);  // 's' (reference) -> stack of main(); object -> heap
        s.display(marks);

        sc.close();
    }
}

// Heap object: each Student object (with its 'age' field) lives on the heap.
class Student {
    int age;                       // instance variable -> stored inside the object on the heap

    Student(int age) {
        this.age = age;
    }

    void display(int marksInput) {
        int marks = marksInput;    // local variable -> lives in display()'s stack frame

        System.out.println("Student Object Created");
        System.out.println("Object Identity Hash Code: " + System.identityHashCode(this));
        System.out.println("Instance Variable (age): " + age);
        System.out.println("Local Variable (marks): " + marks);
    }
}
