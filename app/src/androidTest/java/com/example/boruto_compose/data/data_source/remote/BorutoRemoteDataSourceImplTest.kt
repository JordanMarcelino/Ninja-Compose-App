package com.example.boruto_compose.data.data_source.remote

import com.example.boruto_compose.domain.model.ApiResponse
import com.example.boruto_compose.domain.model.Ninja
import kotlinx.coroutines.runBlocking
import okio.IOException

const val PREV_PAGE = "prevPage"
const val NEXT_PAGE = "nextPage"

class FakeBorutoRemoteDataSourceAt : BorutoRemoteDataSource {

    val ninjas: Map<Int, List<Ninja>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5
        )
    }

    private var page1 = listOf(
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
    private val page2 by lazy {
        listOf(
            Ninja(
                id = 4,
                name = "Boruto",
                image = "/static/boruto.png",
                about = "Boruto Uzumaki (うずまきボルト, Uzumaki Boruto) is a shinobi from Konohagakure's Uzumaki clan and a direct descendant of the Hyūga clan through his mother. While initially resentful of his father and his absence since becoming Hokage, Boruto eventually comes to respect his father and duties.",
                rating = 4.9,
                power = 95,
                month = "Mar",
                day = "27th",
                family = listOf(
                    "Naruto",
                    "Hinata",
                    "Hanabi",
                    "Himawari",
                    "Kawaki"
                ),
                abilities = listOf(
                    "Karma",
                    "Jogan",
                    "Rasengan",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Wind",
                    "Water"
                )
            ),
            Ninja(
                id = 5,
                name = "Sarada",
                image = "/static/sarada.jpg",
                about = "Sarada Uchiha (うちはサラダ, Uchiha Sarada) is a kunoichi from Konohagakure's Uchiha clan. Because she was raised only by her mother without having her father around, Sarada initially struggles to understand who she is or what she's supposed to be. After meeting him with the help of Naruto Uzumaki, Sarada comes to believe that she is defined by the connections she has with others, and as a member of Team Konohamaru, she seeks to someday become Hokage so that she can connect with as many people as possible.",
                rating = 4.9,
                power = 95,
                month = "Mar",
                day = "31st",
                family = listOf(
                    "Sasuke Uchiha",
                    "Sakura Uchiha"
                ),
                abilities = listOf(
                    "Sharingan",
                    "Strength",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Wind",
                    "Fire"
                )
            ),
            Ninja(
                id = 6,
                name = "Mitsuki",
                image = "/static/mitsuki.jpg",
                about = "Mitsuki (ミツキ, Mitsuki) is a synthetic human that was created as a partial clone of Orochimaru. Immigrating to Konohagakure to confirm whether or not Boruto Uzumaki was his \"sun\", he became a shinobi and was placed on Team Konohamaru. Mitsuki was created as a clone of Orochimaru, being cultivated from the same embryo as at least one older \"Mitsuki\", and raised in a test tube.",
                rating = 4.9,
                power = 95,
                month = "Jul",
                day = "25th",
                family = listOf(
                    "Orochimaru",
                    "Log"
                ),
                abilities = listOf(
                    "Senin Mode",
                    "Transformation",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Wind"
                )
            )
        )
    }
    private val page3 by lazy {
        listOf(
            Ninja(
                id = 7,
                name = "Kawaki",
                image = "/static/kawaki.jpg",
                about = "Kawaki (カワキ, Kawaki) is a child raised by Kara to be the future vessel for Isshiki Ōtsutsuki and the key to the fulfilment of their greatest wish.[1] After being brought to Konohagakure by Team 7, he is taken in by Naruto Uzumaki who raises him as his own, during which he develops a brotherly bond with Boruto Uzumaki to solve the mystery of the Kāma.",
                rating = 4.2,
                power = 92,
                month = "Jan",
                day = "1st",
                family = listOf(
                    "Kokatsu"
                ),
                abilities = listOf(
                    "Karma",
                    "Transformation",
                    "Strength"
                ),
                natureTypes = listOf(
                    "Fire"
                )
            ),
            Ninja(
                id = 8,
                name = "Orochimaru",
                image = "/static/orochimaru.jpg",
                about = "Orochimaru (大蛇丸, Orochimaru) is one of Konohagakure's legendary Sannin. With a life-ambition to learn all of the world's secrets, Orochimaru seeks immortality so that he might live all of the lives necessary to accomplish his task. After being caught red-handed performing unethical experiments on his fellow citizens for the sake of this immortality, Orochimaru defected from Konoha.",
                rating = 4.5,
                power = 97,
                month = "Oct",
                day = "27th",
                family = listOf(
                    "Mitsuki",
                    "Log"
                ),
                abilities = listOf(
                    "Senin Mode",
                    "Transformation",
                    "Science"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Wind",
                    "Fire",
                    "Earth",
                    "Water"
                )
            ),
            Ninja(
                id = 9,
                name = "Kakashi",
                image = "/static/kakashi.png",
                about = "Kakashi Hatake (はたけカカシ, Hatake Kakashi) is a shinobi of Konohagakure's Hatake clan. Famed as Kakashi of the Sharingan (写輪眼のカカシ, Sharingan no Kakashi), he is one of Konoha's most talented ninja, regularly looked to for advice and leadership despite his personal dislike of responsibility. To his students on Team 7, Kakashi emphasises the importance of teamwork; he himself received this lesson, along with the Sharingan, from his childhood friend, Obito Uchiha.",
                rating = 4.5,
                power = 96,
                month = "Sep",
                day = "15th",
                family = listOf(
                    "Sakumo"
                ),
                abilities = listOf(
                    "Intelligence",
                    "Strength"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Wind",
                    "Fire",
                    "Earth",
                    "Water"
                )
            )
        )
    }
    private val page4 by lazy {
        listOf(
            Ninja(
                id = 10,
                name = "Isshiki",
                image = "/static/ishiki.jpg",
                about = "A thousand years ago, Isshiki came to Earth alongside Kaguya with the objective to plant a Tree to harvest its Chakra Fruit. While Kaguya, being lower-ranked, was planned to be sacrificed to create the Chakra Fruit, she instead turned on Isshiki, leaving him on the verge of death after destroying Isshiki's lower half. Encountering Jigen and not having the strength to implant a Kāma on him, Isshiki devised a desperate plan and shrunk himself to enter the monk's ear in order to survive his injury by absorbing Jigen's nutrients.",
                rating = 5.0,
                power = 100,
                month = "Jan",
                day = "1st",
                family = listOf(
                    "Otsutsuki Clan"
                ),
                abilities = listOf(
                    "Sukunahikona",
                    "Daikokuten",
                    "Byakugan",
                    "Space–Time",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Fire"
                )
            ),
            Ninja(
                id = 11,
                name = "Momoshiki",
                image = "/static/momoshiki.jpg",
                about = "Momoshiki Ōtsutsuki (大筒木モモシキ, Ōtsutsuki Momoshiki) was a member of the Ōtsutsuki clan's main family, sent to investigate the whereabouts of Kaguya and her God Tree and then attempting to cultivate a new one out of the chakra of the Seventh Hokage. In the process of being killed by Boruto Uzumaki, Momoshiki placed a Kāma on him, allowing his spirit to remain intact through the mark.",
                rating = 3.9,
                power = 98,
                month = "Jan",
                day = "1st",
                family = listOf(
                    "Otsutsuki Clan"
                ),
                abilities = listOf(
                    "Rinnegan",
                    "Byakugan",
                    "Strength"
                ),
                natureTypes = listOf(
                    "Fire",
                    "Lightning",
                    "Wind",
                    "Water",
                    "Earth"
                )
            ),
            Ninja(
                id = 12,
                name = "Urashiki",
                image = "/static/urashiki.jpg",
                about = "Urashiki Ōtsutsuki (大筒木ウラシキ, Ōtsutsuki Urashiki) was a low-ranking member of the Ōtsutsuki clan's main family, sent to assist Momoshiki and Kinshiki on their mission to investigate Kaguya's whereabouts and gather the chakra of the God Tree on Earth. Compared to his comrades, Urashiki had been shown to have a rather laid-back and jovial personality. He was quite willing to joke along with Momoshiki and Kinshiki, and disparaged on how serious they are.",
                rating = 3.4,
                power = 95,
                month = "Jan",
                day = "1st",
                family = listOf(
                    "Otsutsuki Clan"
                ),
                abilities = listOf(
                    "Space–Time",
                    "Rinnegan",
                    "Byakugan"
                ),
                natureTypes = listOf(
                    "Fire",
                    "Lightning",
                    "Wind",
                    "Earth"
                )
            )
        )
    }
    private val page5 by lazy {
        listOf(
            Ninja(
                id = 13,
                name = "Code",
                image = "/static/code.jpg",
                about = "Code (コード, Kōdo) is the last active Inner from Kara. Carrying Isshiki Ōtsutsuki's legacy within him, he inherits the Ōtsutsuki Clan's will to become a Celestial Being and continually evolve. At the time Kawaki was brought to Kara, Code was one of fifteen candidates in Jigen and Amado's Ōtsutsuki ritual to screen for a Kāma vessel for Isshiki. Only Kawaki survived to become an actual vessel.",
                rating = 4.8,
                power = 99,
                month = "Jan",
                day = "1st",
                family = listOf(
                    "Unknown"
                ),
                abilities = listOf(
                    "White Karma",
                    "Transformation",
                    "Genjutsu"
                ),
                natureTypes = listOf(
                    "Unknown"
                )
            ),
            Ninja(
                id = 14,
                name = "Amado",
                image = "/static/amado.jpg",
                about = "Amado (アマド, Amado) is a former Inner from the organisation Kara and the head of its research and development division. He has since defected to Konohagakure, where he used a mix of bluffs and gifts to gain official citizenship for the Hokage's protection. Amado had a daughter who died twelve years prior to the reign of the Seventh Hokage. In his quest to kill Isshiki Ōtsutsuki, Amado joined Kara and was granted the rank of Inner, serving as the head of its research and development division.",
                rating = 5.0,
                power = 90,
                month = "Jan",
                day = "1st",
                family = listOf(
                    "Unknown"
                ),
                abilities = listOf(
                    "Science",
                    "Intelligence",
                    "Trickery"
                ),
                natureTypes = listOf(
                    "Unknown"
                )
            ),
            Ninja(
                id = 15,
                name = "Koji",
                image = "/static/koji.jpg",
                about = "Koji Kashin (果心居士, Kashin Koji) is a clone of Jiraiya that was created by Amado for the purpose of killing Isshiki Ōtsutsuki. A former Inner of Kara, he was in charge of the sector on the outskirts of the Land of Fire. An enigmatic man, Koji has a very stoic and straightforward nature that follows a no-nonsense view. Arrogant as he may appear, he has consistently shown himself to be a very rational and perceptive man.",
                rating = 4.5,
                power = 90,
                month = "Jan",
                day = "1st",
                family = listOf(
                    "Jiraiya"
                ),
                abilities = listOf(
                    "Senin Mode",
                    "Rasengan",
                    "Shadow Clone"
                ),
                natureTypes = listOf(
                    "Fire",
                    "Earth"
                )
            )
        )
    }

    private var exception = false

    fun setPage1Empty() {
        page1 = emptyList()
    }

    fun returnException(){
        exception = true
    }

    override suspend fun getNinjas(page: Int): ApiResponse {
        if (exception) throw IOException()

        require(page in 1..5)

        return runBlocking {
            ApiResponse(
                success = true,
                message = "OK",
                prevPage = calculatePage(page)[PREV_PAGE],
                nextPage = calculatePage(page)[NEXT_PAGE],
                ninjas = ninjas[page]!!,
                lastUpdated = System.currentTimeMillis()
            )
        }
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        if (page1.isEmpty()){
            return mapOf(
                PREV_PAGE to null,
                NEXT_PAGE to null
            )
        }
        var prevPage: Int? = page
        var nextPage: Int? = page

        nextPage = if (page in 1..4) nextPage?.plus(1)
        else null

        prevPage = if (page in 2..5) prevPage?.minus(1)
        else null

        return mapOf(
            PREV_PAGE to prevPage,
            NEXT_PAGE to nextPage
        )
    }

    override suspend fun searchNinjas(query: String): ApiResponse {
        return ApiResponse(
            success = false
        )
    }
}