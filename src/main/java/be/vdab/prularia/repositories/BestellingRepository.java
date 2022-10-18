package be.vdab.prularia.repositories;

import be.vdab.prularia.DTO.TVOverZichtDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BestellingRepository {
    private final JdbcTemplate template;

    public BestellingRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<TVOverZichtDTO> findEersteVijfBestellingen() {
        var sql = """
                SELECT Bestellingen.bestelId, sum(Bestellijnen.aantalBesteld) as totaalAantal, sum(Artikelen.gewichtInGram) as totaalGewicht
                FROM Bestellingen
                left outer join Bestellijnen on Bestellijnen.bestelId = Bestellingen.bestelId
                left outer join Artikelen on Bestellijnen.artikelId = Artikelen.artikelId
                where Bestellingen.bestellingsStatusId = 2 
                group by Bestellingen.bestelId
                ORDER BY besteldatum Limit 5;
                """;
        RowMapper<TVOverZichtDTO> mapper = (result, rowNum) ->
                new TVOverZichtDTO(result.getLong("bestelId"), result.getInt("totaalAantal"),
                        result.getDouble("totaalGewicht"));
        return template.query(sql, mapper);
    }

    public int aantalBestellingen() {
        var sql = """
                select count(*) from Bestellingen
                where bestellingsStatusId = 2
                """;

        return template.queryForObject(sql,Integer.class);
    }
}
