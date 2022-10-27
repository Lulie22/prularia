package be.vdab.prularia.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UitgaandaLeveringsStatusRepository {
    private final JdbcTemplate template;

    public UitgaandaLeveringsStatusRepository(JdbcTemplate template) {
        this.template = template;
    }
    public Optional<Long> vindUitgaandeLeveringStatusId(){
        var sql = """
                  select uitgaandeLeveringsStatusId
                  from uitgaandeLeveringsStatussen
                  where naam = 'Onderweg';
                  """;
        return Optional.of(template.queryForObject(sql, Long.class));
    }
}
