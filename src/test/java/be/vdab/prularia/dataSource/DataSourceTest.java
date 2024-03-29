package be.vdab.prularia.dataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class DataSourceTest {
    private final DataSource dataSource;

    public DataSourceTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Test
    void getConnection()throws SQLException {
        try(var connection = dataSource.getConnection()){
            assertThat(connection.getCatalog()).isEqualTo("prulariaCom");
        }
    }
}

