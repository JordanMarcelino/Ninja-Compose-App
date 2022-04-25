package com.example.boruto_compose.data.data_source.remote

import com.example.boruto_compose.domain.model.ApiResponse
import com.example.boruto_compose.domain.model.Ninja
import kotlinx.coroutines.runBlocking

class FakeBorutoRemoteDataSource : BorutoRemoteDataSource {

    private val page1 by lazy {
        listOf(
            Ninja(
                id = 1,
                name = "Sasuke",
                image = "/static/sasuke.jpg",
                about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                rating = 5.0,
                power = 98,
                month = "July",
                day = "23rd",
                family = listOf(
                    "Fugaku",
                    "Mikoto",
                    "Itachi",
                    "Sarada",
                    "Sakura"
                ),
                abilities = listOf(
                    "Sharingan",
                    "Rinnegan",
                    "Sussano",
                    "Amateratsu",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Fire",
                    "Wind",
                    "Earth",
                    "Water"
                )
            ),
            Ninja(
                id = 2,
                name = "Naruto",
                image = "/static/naruto.jpg",
                about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
                rating = 5.0,
                power = 98,
                month = "Oct",
                day = "10th",
                family = listOf(
                    "Minato",
                    "Kushina",
                    "Boruto",
                    "Himawari",
                    "Hinata"
                ),
                abilities = listOf(
                    "Rasengan",
                    "Rasen-Shuriken",
                    "Shadow Clone",
                    "Senin Mode"
                ),
                natureTypes = listOf(
                    "Wind",
                    "Earth",
                    "Lava",
                    "Fire"
                )
            ),
            Ninja(
                id = 3,
                name = "Sakura",
                image = "/static/sakura.jpg",
                about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
                rating = 4.5,
                power = 92,
                month = "Mar",
                day = "28th",
                family = listOf(
                    "Kizashi",
                    "Mebuki",
                    "Sarada",
                    "Sasuke"
                ),
                abilities = listOf(
                    "Chakra Control",
                    "Medical Ninjutsu",
                    "Strength",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Earth",
                    "Water",
                    "Fire"
                )
            )
        )
    }

    override suspend fun getNinjas(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun searchNinjas(query: String): ApiResponse {
        return runBlocking {
            val response = decideGivenNinjas(query)
            ApiResponse(
                success = true,
                ninjas = response
            )
        }
    }

    private fun decideGivenNinjas(query: String?): List<Ninja> {
        if (query.isNullOrBlank()) return emptyList()
        val ninjaList = mutableListOf<Ninja>()

        page1.forEach { ninja ->
            if (ninja.name.contains(query, true)) {
                ninjaList.add(ninja)
            }
        }

        return ninjaList
    }
}