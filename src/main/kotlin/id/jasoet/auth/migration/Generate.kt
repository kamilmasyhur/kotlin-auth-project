package id.jasoet.auth.migration

import id.jasoet.auth.module.dataSourceModule
import io.ebean.annotation.Platform
import io.ebean.config.DbMigrationConfig
import io.ebean.config.ServerConfig
import io.ebean.dbmigration.DbMigration
import org.koin.Logger.slf4jLogger
import org.koin.core.context.startKoin
import javax.sql.DataSource

fun main() {
    val koin = startKoin {
        slf4jLogger()
        modules(dataSourceModule)
    }.koin

    val dataSource = koin.get<DataSource>()
    dataSource.generateMigrationFile(Platform.POSTGRES, "postgresql")
}

fun DataSource.generateMigrationFile(platform: Platform, prefix: String): String? {
    val dataSource = this
    val serverConfig = ServerConfig().apply {
        this.migrationConfig = DbMigrationConfig().apply {
            applyPrefix = "V"
        }
        this.dataSource = dataSource
        this.isDefaultServer = false
        this.name = "MigrationDataSource"
    }

    val dbMigration = DbMigration.create().apply {
        setServerConfig(serverConfig)
        addPlatform(platform, prefix)
    }
    return dbMigration.generateMigration()
}
