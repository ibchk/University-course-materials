/**
 * Participant class.
 */
public class ParticipantImplementation implements HackathonParticipant {

    /**
     * Participant name.
     */
    private final String name;
    /**
     * Participant role.
     */
    private final Role role;
    /**
     * Participant extroversion level.
     */
    private final double extroversion;

    /**
     * @param newName         participant name.
     * @param newRole         participant role.
     * @param newExtroversion participant level.
     */
    public ParticipantImplementation(final String newName,
                                     final Role newRole,
                                     final double newExtroversion) {
        name = newName;
        role = newRole;
        extroversion = newExtroversion;
    }

    /**
     * @return participant name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return participant role.
     */
    @Override
    public Role getRole() {
        return role;
    }

    /**
     * @return extroversion level.
     */
    @Override
    public double getExtroversionLevel() {
        return extroversion;
    }
}
