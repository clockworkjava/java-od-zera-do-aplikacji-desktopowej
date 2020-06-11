public class Guest {

    String firstName;
    String lastName;
    int age;

    public Guest() {
        System.out.println("Stworzono nowy obiekt klasy Guest");
    }

    public Guest(String newFirstName, String newLastName, int newAge) {
        firstName = newFirstName;
        lastName = newLastName;
        age = newAge;
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(age);
    }
}
