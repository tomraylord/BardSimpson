package org.shmurda.bardsimpson.util

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner

object TokenUtil {

    @JvmStatic
    fun getToken(): String {
        try {
            val file = Paths.get("token.txt").toFile()

            if (file.exists()) {
                return String(Files.readAllBytes(file.toPath()))
            } else {
                println("${ColourUtil.red}Failed to find token.txt, paste your bot token here: ${ColourUtil.reset}")

                val scanner = Scanner(System.`in`)
                val token: String = scanner.nextLine()
                scanner.close()

                println("${ColourUtil.green}Creating token.txt in the same directory...${ColourUtil.reset}")

                if (!file.createNewFile()) {
                    println("${ColourUtil.red}Failed to create token.txt, you will have to manually make this file.${ColourUtil.reset}")
                } else {
                    Files.write(file.toPath(), token.toByteArray())
                }

                return token
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

}