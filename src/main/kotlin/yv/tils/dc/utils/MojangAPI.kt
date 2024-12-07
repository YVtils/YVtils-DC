package yv.tils.dc.utils

import yv.tils.dc.YVtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.util.*

class MojangAPI {
    /**
     * Get the UUID of a player by their name
     * @param playerName The name of the player
     * @return The UUID of the player
     * @throws Exception
     */
    fun name2uuid(playerName: String): UUID? {
        try {
            val url = "https://api.mojang.com/users/profiles/minecraft/$playerName"
            val map: MutableMap<String, String> = getWebsite(url)

            map["id"] = map["id"]!!.substring(0, 8) + "-" +
                    map["id"]!!.substring(8, 12) + "-" +
                    map["id"]!!.substring(12, 16) + "-" +
                    map["id"]!!.substring(16, 20) + "-" +
                    map["id"]!!.substring(20)

            return UUID.fromString(map["id"])
        } catch (e: Exception) {
            YVtils.instance.logger.warning(e.message)
        }
        return null
    }

    private fun getWebsite(url: String): MutableMap<String, String> {
        val map: MutableMap<String, String> = HashMap()

        val connection = URI(url).toURL().openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val content = StringBuilder()

            reader.use {
                it.lines().forEach { line ->
                    content.append(line)
                }
            }

            reader.close()
            connection.disconnect()

            val websiteContent = content.toString()
            val list = websiteContent.split(",")

            for (string: String in list) {
                var s = string
                s = s.replace("{", "")
                s = s.replace("}", "")
                s = s.replace("\"", "")
                s = s.replace(" ", "")

                map[s.split(":")[0]] = s.split(":")[1]
            }
        } else {
            YVtils.instance.logger.warning("HTTP request failed with response code: $responseCode")
        }
        return map
    }
}
