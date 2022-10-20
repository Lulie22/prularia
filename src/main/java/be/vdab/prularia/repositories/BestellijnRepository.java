package be.vdab.prularia.repositories;

import be.vdab.prularia.domain.Bestellijn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BestellijnRepository {
    private final JdbcTemplate template;

    public BestellijnRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Bestellijn> bestellijnRowMapper =
            (result, rowNum) ->
                    new Bestellijn(result.getLong("bestellijnId"), result.getLong("bestelId"),
                            result.getLong("artikelId"), result.getInt("aantalBesteld"),
                            result.getInt("aantalGeannuleerd"));

    public List<Bestellijn> vindBestellijnenByBestelId(long bestelId) {
        var sql = """
                SELECT bestellijnId, bestelId, artikelId, aantalBesteld, aantalGeannuleerd
                FROM bestellijnen
                WHERE bestelId = ?
                """;
        return template.query(sql, bestellijnRowMapper, bestelId);
    }
}
