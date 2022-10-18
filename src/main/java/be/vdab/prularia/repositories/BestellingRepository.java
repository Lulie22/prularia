package be.vdab.prularia.repositories;

import be.vdab.prularia.domain.Artikel;
import be.vdab.prularia.domain.Bestelling;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BestellingRepository {
    private final JdbcTemplate template;

    public BestellingRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Bestelling> bestellingRowMapper =
            (result, rowNum) ->
                    new Bestelling(result.getLong("bestelId"), result.getTimestamp("besteldatum").toLocalDateTime(),
                            result.getLong("klantId"), result.getBoolean("betaald"),
                            result.getString("betalingscode"), result.getLong("betaalwijzeId"),
                            result.getBoolean("annulatie"), result.getDate("annulatiedatum").toLocalDate(),
                            result.getString("terugbetalingscode"), result.getLong("bestellingsStatusId"),
                            result.getBoolean("actiecodeGebruikt"), result.getString("bedrijfsnaam"),
                            result.getString("btwNummer"), result.getString("voornaam"), result.getString("familienaam"),
                            result.getLong("facturatieAdresId"), result.getLong("leveringsAdresId"));

    public Optional<Bestelling> vindVolgendeBestelling() {
        try {
            var sql = """
                    SELECT bestelId, besteldatum, klantId, betaald, betalingscode, betaalwijzeId,
                    annulatie, annulatiedatum, terugbetalingscode, bestellingsStatusId, actiecodeGebruikt,
                    bedrijfsnaam, btwNummer, voornaam, familienaam, facturatieAdresId, leveringsAdresId
                    FROM bestellingen
                    WHERE bestellingsstatusId = 2
                    ORDER BY besteldatum
                    LIMIT 1
                    """;
            return Optional.of(template.queryForObject(sql, bestellingRowMapper));
        } catch (
                IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Optional<Long> vindIdVolgendeBestelling() {
        try {
            var sql = """
                SELECT bestelId 
                FROM bestellingen
                WHERE bestellingsStatusId = 2
                ORDER BY besteldatum
                LIMIT 1
                """;
            return Optional.of(template.queryForObject(sql, Long.class));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}
