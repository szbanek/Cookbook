package com.example.recipes2

class Recipe(val name: String, private val recipe: String, private val imageId : Int) {
    companion object {
        val recipes = arrayOf(
            arrayOf(
            Recipe("Jajecznica", "Składniki: \n 3 jajka \n 10 g masła" +
                    "\n\n Sposób przygotowania: \n Jajka wymieszać, posolić." +
                    "Roztopić masło na patelni, wlać jajka i smażyć kilka minut, do pożądanego"+
                    "stopnia ścięcia.", R.drawable.jajecznica),
            Recipe("Płatki śniadaniowe", "Składniki: \n 200g płatków\n 100ml mleka\n" +
            "Sposób przygotowania: \n Wsyp płatki do miski i zalej mlekiem.", R.drawable.platki),
                Recipe("Kaszka", "Składniki: \n 100g kaszki\n" +
                        "Sposób przygotowania: \n Wsyp kaszkę do miski i zalej gorącą wodą.\n" +
                    "Rozmieszaj dokładnie i po chwili możesz jacząć jeść", R.drawable.kaszka),
                Recipe("Kanapki", "Składniki: \n pieczywo \n 10g masła\n ser \n szynka \n pomidor\n " +
                    "Sposób przygotowania: \n Posmaruj chleb masłem po czym połóż na nim ser, szynkę i pomidora.", R.drawable.kanapki),
                Recipe("Jogurt", "Składniki: \n jogurt\n Sposób przygotowania: \n Otwórz jogurt i go zjedz", R.drawable.jogurt)
            ),
            arrayOf(
            Recipe("Schabowy", "Składniki: \n Schab 200g \n bółka tarta \n 1 jajko \n " +
                "Sposób przygotowania: \n Ubij mięso, po czym zamocz w rozbitym jajku i obtocz w bółce tartej.\n" +
                "Tak przygotowanego smaż na patelni przez 10 min.", R.drawable.schabowy),
            Recipe("Naleśniki", "Składniki: \n 200g mąki \n 100ml wody gazowanej \n 300ml mleka" +
                    "Sposób przygotowania: \n Wrzuć składniki do miski po czym dokładnie je rozmieszaj." +
                "Następnie smaż na patelni.", R.drawable.nalesniki)
            )
        )
    }

    fun getRecipe(): String {
        return recipe
    }

    fun getImageId(): Int {
        return imageId
    }

    fun fetchName(): String {
        return name
    }

    override fun toString(): String {
        return name
    }
}
