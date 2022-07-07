package org.shmurda.bardsimpson.command

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Cmd(
    val name: String,
    val description: String
)
