package org.shmurda.bardsimpson.util

object TimeUtil {

    @JvmStatic
    fun elapsed(start: Long): Double {
        return (System.currentTimeMillis() - start) / 1000.0
    }

}