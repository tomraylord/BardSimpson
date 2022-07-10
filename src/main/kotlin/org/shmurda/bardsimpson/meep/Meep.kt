package org.shmurda.bardsimpson.meep

import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import net.dv8tion.jda.api.entities.User
import org.bson.Document
import org.shmurda.bardsimpson.Bard

class Meep(private val discordId: String, private var meepId: Int?) {

    constructor(discordId: String) : this(discordId, null)

    init {
        MeepHandler.meeps[discordId] = this
    }

    private fun getUser(): User? {
        return Bard.bard.jda.retrieveUserById(discordId).complete()
    }

    fun save() {
        val document = Document()
        document["discordId"] = discordId
        document["meepId"] = meepId

        MeepHandler.collection.replaceOne(Filters.eq("discordId", discordId), document, ReplaceOptions().upsert(true))
    }

    fun load() {
        val document: Document? = MeepHandler.collection.find(Filters.eq("discordId", discordId)).first()
        if (document != null) {
            meepId = document.getInteger("meepId")
        }
    }

}