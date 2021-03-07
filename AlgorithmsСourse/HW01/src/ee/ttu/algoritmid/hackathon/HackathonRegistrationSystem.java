import java.util.List;

/**
 * Hackathon registration system interface.
 */
public interface HackathonRegistrationSystem {

    /**
     * @param participant participant to register.
     * @return New team.
     * @throws IllegalArgumentException if wrong participant given.
     */
    HackathonTeam registerToHackaton(HackathonParticipant participant)
            throws IllegalArgumentException;

    /**
     * @return list with participants without team.
     */
    List<HackathonParticipant> participantsWithoutTeam();
}
