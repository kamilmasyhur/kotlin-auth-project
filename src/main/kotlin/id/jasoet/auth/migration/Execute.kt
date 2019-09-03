package id.jasoet.auth.migration

import id.jasoet.auth.module.dataSourceModule
import org.flywaydb.core.Flyway
import org.koin.Logger.slf4jLogger
import org.koin.core.context.startKoin
import javax.sql.DataSource

fun main() {
    val koin = startKoin {
        slf4jLogger()
        modules(dataSourceModule)
    }.koin

    val dataSource = koin.get<DataSource>()
    dataSource.executeMigration("classpath:/dbmigration/postgresql")
}

fun DataSource.executeMigration(location: String) {
    val dataSource = this
    val flyway = Flyway
            .configure()
            .dataSource(dataSource)
            .locations(location)
            .load()
    flyway.migrate()
}