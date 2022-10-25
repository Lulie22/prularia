package be.vdab.prularia.forms;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public record InkomendeLeveringForm(@NotNull @NotBlank String leverancierNaam, @NotNull @Positive long leveringsbonNummer,
                                    @NotNull @DateTimeFormat LocalDate leveringsbondatum, @NotNull @DateTimeFormat LocalDate leverdatum) {
}
