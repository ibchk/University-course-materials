public interface Trampoline {

    enum Type {
        NORMAL,
        WITH_FINE
    }

    int getJumpForce();

    Type getType();

}