package pl.clockworkjava;

public class GuestService {

    GuestRepository repository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {

        Gender gender = Gender.FEMALE;

        if(genderOption==1) {
            gender = Gender.MALE;
        } else if(genderOption==2) {
            gender = Gender.FEMALE;
        }

        return repository.createNewGuest(firstName, lastName, age, gender);
    }
}
