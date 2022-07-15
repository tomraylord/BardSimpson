package org.shmurda.bardsimpson.meep

import com.mongodb.client.MongoCollection
import net.dv8tion.jda.api.JDA
import org.bson.Document
import org.shmurda.bardsimpson.Bard

class MeepHandler(private val jda: JDA) {

    init {
        loadAll()
    }

    private fun loadAll() {

    }

    companion object {
        var meeps: HashMap<String, Meep> = HashMap()
        var collection: MongoCollection<Document> = Bard.bard.mongo.getCollection("meeps")
    }

}