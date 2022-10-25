package be.vdab.prularia.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public record InkomendeLeveringsLijnForm(@NotNull @NotBlank String ean, @NotNull @Positive int aantal,
                                         @NotNull @PositiveOrZero int aantalGoedgekeurd, @NotNull @PositiveOrZero int aantalAfgekeurd) {
}
