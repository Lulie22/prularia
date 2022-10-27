package be.vdab.prularia.repositories;

import be.vdab.prularia.domain.UitgaandeLevering;
import be.vdab.prularia.sessions.MagazijnierSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Map;

@Repository
public class UitgaandeLeveringRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;


    public UitgaandeLeveringRepository(JdbcTemplate template) {
        this.template = template;
        insert = new SimpleJdbcInsert(template)
                .withTableName("UitgaandeLeveringen")
                .usingGeneratedKeyColumns("uitgaandeLeveringsId");


    }

    public long create(UitgaandeLevering uitgaandeLevering){
        return insert.executeAndReturnKey(
                Map.of("bestelId", uitgaandeLevering.getBestelId(),
                        "vertrekDatum", uitgaandeLevering.getVertrekDatum(),
                        "aankomstDatum", uitgaandeLevering.getAankomstDatum(),
                            //"trackingcode", uitgaandeLevering.getTrackingCode(),
                            "klantId", uitgaandeLevering.getKlantId(),
                       "uitgaandeLeveringsStatusId", uitgaandeLevering.getUitgaandeLeveringsStatusId())
        ).longValue();
    }




}
