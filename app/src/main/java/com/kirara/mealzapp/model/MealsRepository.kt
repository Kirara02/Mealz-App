package com.kirara.mealzapp.model

import com.kirara.mealzapp.model.api.MealsWebService
import com.kirara.mealzapp.model.response.MealResponse
import com.kirara.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealResponse>()
    suspend fun getMeals() : MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance?: synchronized(this){
            instance ?: MealsRepository().also { instance = it }
        }
    }
}