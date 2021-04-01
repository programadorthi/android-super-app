package dev.programadorthi.shared.database.fake

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import dev.programadorthi.shared.database.SuperApp

class SuperAppFake {
    private var driver: SqlDriver? = null
    private var superApp: SuperApp? = null

    val database: SuperApp
        get() = superApp
            ?: throw IllegalStateException("No database found. Have you called open function?")

    fun open() {
        val tempDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        superApp = SuperApp(tempDriver)
        SuperApp.Schema.create(tempDriver)
        driver = tempDriver
    }

    fun close() {
        driver?.close()
        driver = null
        superApp = null
    }
}
