package ru.zidkov.secondtask

class Generator {
    companion object {
        private val imagesList = listOf(
            "https://assets-prd.ignimgs.com/2022/07/07/stonks-1657227632995.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlElWniPOOXKatxfCnd-5rhgz_eP-DGBrJDg&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNn9dS58aELn-xmdQgNkbXpCwrBpXG9n6HZA&usqp=CAU",
            "https://napoleoncat.com/wp-content/uploads/2022/05/social-media-memes-emotional-damage-meme.jpg",
            "https://img.buzzfeed.com/buzzfeed-static/static/2023-07/3/18/campaign_images/96ef42d24976/66-teacher-memes-that-are-100-accurate-2-1481-1688409219-0_dblbig.jpg?resize=1200:*",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQT7PNMTZBufOEPrlXvjK2yRA0i_NBtZedIkg&usqp=CAU",
            "https://static.wixstatic.com/media/82daf4_ff1c0ff1c7fa4ae59bff4fae6b167008.jpg/v1/fill/w_564,h_522,al_c,lg_1,q_80/82daf4_ff1c0ff1c7fa4ae59bff4fae6b167008.jpg"
        )

        fun generateItemsData(): List<User> {
            val result = mutableListOf<User>()
            for (i in 0..100) {
                result.add(
                    User(
                        "Лучшее имя $i",
                        "Лучший заголовок $i",
                        imagesList[(Math.random() * imagesList.size).toInt()]
                    )
                )
            }
            return result.toList()
        }
    }
}