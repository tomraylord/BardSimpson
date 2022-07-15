package org.shmurda.bardsimpson.util.version

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.InputStream
import java.io.InputStreamReader

object VersionUtil {

    @JvmStatic
    fun generateVersionInfo(): VersionInfo {
        val loader: ClassLoader = javaClass.classLoader
        val stream: InputStream = loader.getResourceAsStream("git.properties")

        val parser: JSONParser = JSONParser()
        val json: JSONObject = parser.parse(InputStreamReader(stream, "UTF-8")) as JSONObject

        return VersionInfo(json["git.branch"] as String,
            json["git.commit.id.abbrev"] as String,
            json["git.commit.user.name"] as String,
            json["git.commit.message.short"] as String,
            json["git.commit.time"] as String)
    }

}