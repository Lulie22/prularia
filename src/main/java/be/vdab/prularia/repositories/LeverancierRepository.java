package be.vdab.prularia.repositories;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LeverancierRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public LeverancierRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("leveranciers");
    }

    public Optional<Long> vindIdByNaam(String naam) {
        try {
            var sql = """
                SELECT leveranciersId
                FROM leveranciers
                WHERE naam = ?
                """;
            return Optional.of(template.queryForObject(sql, Long.class, naam));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}
