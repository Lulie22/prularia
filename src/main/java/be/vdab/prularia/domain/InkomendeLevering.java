package be.vdab.prularia.domain;

import java.time.LocalDate;

public class InkomendeLevering {
    private final long inkomendeLeveringsId;
    private final long leveranciersId;
    private final String leveringsbonNummer;
    private final LocalDate leveringsbonddatum;
    private final LocalDate leverDatum;
    private final long ontvangerPersoneelslidId;

    public InkomendeLevering(long inkomendeLeveringsId, long leveranciersId, String leveringsbonNummer,
                             LocalDate leveringsbonddatum, LocalDate leverDatum, long ontvangerPersoneelslidId) {
        this.inkomendeLeveringsId = inkomendeLeveringsId;
        this.leveranciersId = leveranciersId;
        this.leveringsbonNummer = leveringsbonNummer;
        this.leveringsbonddatum = leveringsbonddatum;
        this.leverDatum = leverDatum;
        this.ontvangerPersoneelslidId = ontvangerPersoneelslidId;
    }

    public long getInkomendeLeveringsId() {
        return inkomendeLeveringsId;
    }

    public long getLeveranciersId() {
        return leveranciersId;
    }

    public String getLeveringsbonNummer() {
        return leveringsbonNummer;
    }

    public LocalDate getLeveringsbonddatum() {
        return leveringsbonddatum;
    }

    public LocalDate getLeverDatum() {
        return leverDatum;
    }

    public long getOntvangerPersoneelslidId() {
        return ontvangerPersoneelslidId;
    }
}
