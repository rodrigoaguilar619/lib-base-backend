package lib.base.backend.utils;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

public class FlywayUtil {

	public Flyway migrate(DataSource dataSource, String... locations) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(locations)
                .baselineOnMigrate(true)
                .table("config_flyway_history")
                .load();
        flyway.migrate();
        return flyway;
    }
}
