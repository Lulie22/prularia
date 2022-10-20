package be.vdab.prularia.repositories;

import be.vdab.prularia.domain.Bestelling;
import be.vdab.prularia.domain.MagazijnPlaats;
import be.vdab.prularia.dto.OverzichtBesteldArtikel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MagazijnPlaatsRepository {
    private final JdbcTemplate template;

    public MagazijnPlaatsRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<MagazijnPlaats> magazijnPlaatsRowMapper =
            (result, rowNum) ->
                    new MagazijnPlaats(result.getLong("magazijnplaatsId"), result.getLong("artikelId"),
                            result.getString("rij"), result.getInt("rek"),
                            result.getInt("aantal"));

    public List<MagazijnPlaats> vindMagazijnPlaatsenByArtikelId(long artikelId) {
        var sql = """
                SELECT magazijnplaatsId, artikelId, rij, rek, aantal
                FROM magazijnplaatsen
                WHERE artikelId = ?
                """;
        return template.query(sql, magazijnPlaatsRowMapper, artikelId);
    }

    public List<OverzichtBesteldArtikel> vindBesteldeArtikels(HashMap<Long, Integer> magazijnplaatsIdEnAantal) {
        if (magazijnplaatsIdEnAantal.isEmpty()) {
            return List.of();
        }
        System.out.println(magazijnplaatsIdEnAantal.keySet().size());
        System.out.println(magazijnplaatsIdEnAantal.keySet());
        var sql = """
                SELECT m.magazijnplaatsId, m.artikelId, a.naam, m.rij, m.rek
                FROM magazijnplaatsen AS m
                LEFT JOIN artikelen AS a
                ON m.artikelId = a.artikelId
                WHERE magazijnplaatsId IN (
                """
                + "?,".repeat(magazijnplaatsIdEnAantal.keySet().size() - 1 )
                + "?) ORDER BY m.rij, m.rek";
        return template.query(sql,
                (result, rowNum) ->
                new OverzichtBesteldArtikel(result.getLong("artikelId"), result.getString("naam"), result.getString("rij").charAt(0),
                        result.getInt("rek"), magazijnplaatsIdEnAantal.get(result.getLong("magazijnplaatsId")), false));
    }
}
