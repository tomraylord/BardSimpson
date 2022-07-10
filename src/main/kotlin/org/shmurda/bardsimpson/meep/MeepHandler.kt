package org.shmurda.bardsimpson.meep

import net.dv8tion.jda.api.JDA
import org.shmurda.bardsimpson.Bard

class MeepHandler(private val jda: JDA) {

    init {

    }

    companion object {
        var meeps: HashMap<String, Meep> = HashMap()
        var collection = Bard.bard.mongo.getCollection("meeps")
    }

}