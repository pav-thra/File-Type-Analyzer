public class Pattern {
    private final int id;
    private final String signature;
    private final String description;

    public Pattern(int id, String signature, String description) {
        this.id = id;
        this.signature = signature;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public byte[] getSignature() {
        return signature.getBytes();
    }

    public String getDescription() {
        return description;
    }
}

