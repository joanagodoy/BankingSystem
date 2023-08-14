package banking;

/**
 * The concrete Account holder of Person type.
 */
public class Person extends AccountHolder{
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, int idNumber) {
        super(idNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        return this.getIdNumber() == other.getIdNumber()
                && this.getFirstName().equals(other.getFirstName())
                && this.getLastName().equals(other.getLastName());
    }
}
