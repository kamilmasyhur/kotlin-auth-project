package id.jasoet.auth.module

import org.koin.dsl.module
import id.jasoet.auth.service.UserService

val service = module {
    single {
        UserService(get())
    }
}