package be.vdab.prularia.repositories;

import be.vdab.prularia.domain.Artikel;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ArtikelRepository {
    private final JdbcTemplate template;

    public ArtikelRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Artikel> artikelRowMapper =
            (result, rowNum) ->
                    new Artikel(result.getLong("artikelId"), result.getString("ean"),
                            result.getString("naam"), result.getString("beschrijving"),
                            result.getBigDecimal("prijs"), result.getInt("gewichtInGram"),
                            result.getInt("bestelpeil"), result.getInt("voorraad"),
                            result.getInt("minimumVoorraad"), result.getInt("maximumVoorraad"),
                            result.getInt("levertijd"), result.getInt("aantalBesteldLeverancier"),
                            result.getInt("maxAantalInMagazijnPlaats"));

    public Optional<Artikel> vindById(long artikelId) {
        try {
            var sql = """
                    SELECT artikelId, ean, naam, beschrijving, prijs, gewichtInGram, bestelpeil, voorraad,
                    minimumVoorraad, maximumVoorraad, levertijd, aantalBesteldLeverancier, maxAantalInMagazijnPlaats
                    FROM artikelen
                    WHERE artikelId = ?
                    """;
            return Optional.of(template.queryForObject(sql, artikelRowMapper, artikelId));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
    public int verlaagArtikelVoorraad(long artikelId, int aantal){
        var sql = """
                  update artikelen
                  set voorraad = voorraad - ?
                  where artikelId = ? and voorraad >= ?
                  """;
        return template.update(sql,aantal,artikelId,aantal);

    }
}
