package problems.p0100_0200.p0175_combine_two_tables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.zapodot.junit.db.annotations.EmbeddedDatabase;
import org.zapodot.junit.db.annotations.EmbeddedDatabaseTest;
import org.zapodot.junit.db.common.CompatibilityMode;
import problems.utils.WithQuery;

@EmbeddedDatabaseTest(
    compatibilityMode = CompatibilityMode.MySQL,
    initialSqlResources = "src/main/java/problems/p0100_0200/p0175_combine_two_tables/sql-schema.sql")
@Execution(ExecutionMode.SAME_THREAD)
class QueryTest {

    @Tag("Database")
    @Test
    @SneakyThrows
    void testScript1(final @EmbeddedDatabase Connection connection,
                     final @WithQuery String script) {

        try (final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(script)) {
            assertTrue(resultSet.next());
            assertEquals("Allen", resultSet.getString("FIRSTNAME"));
            assertEquals("Wang", resultSet.getString("LASTNAME"));
            assertNull(resultSet.getString("CITY"));
            assertNull(resultSet.getString("STATE"));
            assertTrue(resultSet.next());
            assertEquals("Bob", resultSet.getString("FIRSTNAME"));
            assertEquals("Alice", resultSet.getString("LASTNAME"));
            assertEquals("New York City", resultSet.getString("CITY"));
            assertEquals("New York", resultSet.getString("STATE"));
            assertFalse(resultSet.next());
        }
    }

}
