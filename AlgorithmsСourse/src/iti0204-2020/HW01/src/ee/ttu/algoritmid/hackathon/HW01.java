import java.util.List;

/**
 * Main class, which ables to register people to hackathon.
 */
public class HW01 implements HackathonRegistrationSystem {

    /**
     * A node with participants without teams.
     */
    private final HackathonQueue hackathonQueue = new HackathonQueue();

    /**
     * @param participant participant to register.
     * @return new team.
     * @throws IllegalArgumentException if wrong participant given.
     */
    @Override
    public HackathonTeam registerToHackaton(
            final HackathonParticipant participant)
            throws IllegalArgumentException {
        if (participant == null
                || participant.getName() == null
                || participant.getRole() == null
                || participant.getName().equals("")
                || participant.getExtroversionLevel() < 0) {
            throw new IllegalArgumentException();
        }
        HackathonParticipant teamMate = hackathonQueue.findPartner(participant);
        if (teamMate != null) {
            return new TeamImplementation(teamMate, participant);
        }
        hackathonQueue.addToNode(participant);
        return null;
    }

    /**
     * @return a list with participants without team.
     */
    @Override
    public List<HackathonParticipant> participantsWithoutTeam() {
        return hackathonQueue.participantsWithoutTeam();
    }
}
