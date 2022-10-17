package be.vdab.prularia.repositories;

import be.vdab.prularia.domain.Bestelling;
import be.vdab.prularia.domain.MagazijnPlaats;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
}
