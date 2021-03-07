import java.util.Optional;

public class Document {
    private int id;
    private Person owner;
    private String content;
    private Person signer = null;

    static final int AGE17 = 17;

    public Document(int id, Person owner, String content) {
        this.id = id;
        this.owner = owner;
        this.content = content;
    }

    public boolean sign(Person signer) {
        if (!signer.equals(owner) && signer.getAge() > AGE17 && signer.isLicenceActive() && this.signer == null) {
            this.signer = signer;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public Optional<Person> getSigner() {
        if (signer != null) {
            return Optional.of(signer);
        }
        return Optional.empty();
    }
}
