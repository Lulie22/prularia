package be.vdab.prularia.dto;

public class MagazijnplaatsIdEnStatus {
    private final long magazijnplaatsId;
    private final boolean status;

    public MagazijnplaatsIdEnStatus(long magazijnplaatsId, boolean status) {
        this.magazijnplaatsId = magazijnplaatsId;
        this.status = status;
    }

    public long getMagazijnplaatsId() {
        return magazijnplaatsId;
    }

    public boolean isStatus() {
        return status;
    }
}
