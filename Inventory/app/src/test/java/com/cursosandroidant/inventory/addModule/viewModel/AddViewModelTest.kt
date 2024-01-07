package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//Realizar un test con ViewModel y LiveData

@RunWith(AndroidJUnit4::class)
class AddViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addProductTest() {
        //Se instancia el viewmodel
        val addViewModel = AddViewModel()

        //Se crea un product mockup
        val product = Product(
            id = 17,
            name = "Xbox",
            quantity = 20,
            photoUrl = "https://www.trustedreviews.com/wp-content/uploads/sites/54/2020/10/20201026_152400-920x690.jpg",
            score = 4.8,
            totalVotes = 56
        )

        //Se crea un observer
        val observer = Observer<Boolean> {}

        //Se usa un try..finally para usar el observer
        //Luego liberarlo
        try {
            addViewModel.getResult().observeForever(observer)

            //Se envia el objeto de prueba al viewmodel
            addViewModel.addProduct(product)
            val result = addViewModel.getResult().value
            assertThat(result, Matchers.`is`(true))

        } finally {
            addViewModel.getResult().removeObserver(observer)
        }
    }
}