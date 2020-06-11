public class Guest {

    String firstName;
    String lastName;
    int age;

    public Guest(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        System.out.println("Stworzono gościa: " + this.firstName + " " + this.lastName);
    }
}
