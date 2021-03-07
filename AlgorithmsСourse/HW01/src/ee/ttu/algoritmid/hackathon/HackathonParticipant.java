/**
 * Hackathon participant interface with getters.
 */
public interface HackathonParticipant {

    /**
     * Roles of participants.
     */
    enum Role {
        /**
         * Developer role.
         */
        DEVELOPER,
        /**
         * Business analyst role.
         */
        BUSINESS_ANALYST
    }

    /**
     * @return participant name.
     */
    String getName();

    /**
     * @return participant role.
     */
    Role getRole();

    /**
     * @return participant extroversion level.
     */
    double getExtroversionLevel();
}
