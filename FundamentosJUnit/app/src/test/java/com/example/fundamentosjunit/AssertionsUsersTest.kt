package com.example.fundamentosjunit

import org.junit.*
import org.junit.Assert.*

class AssertionsUsersTest {
    //Variables globales
    private lateinit var bot: User
    private lateinit var assertions: Assertions

    @Before
    fun setup() {
        /*
        Este metodo se ejecuta antes de cualquier otra funcion con la etiqueta @Test
        Se utiliza para inicializar objetos que serán usado en los tests
        */
        assertions = Assertions()
        bot = User(name = "8bit", age = 1, isHuman = false)
        juan = User(name = "Juan", age = 18, isHuman = true)

        println("Before")
    }

    @After
    fun tearDown() {
        /*
        Este metodo se ejecuta despues de las funciones que tienen la etiqueta @Test
        Se usa para reciclar recursos, colocar variables en nulo, etc
        */

        bot = User()
        juan = User()
        println("After")
    }

    companion object {
        private lateinit var juan: User

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            /*
            Este metodo se ejecuta cuando la clase se esta creando
            */
            juan = User(name = "Juan", age = 18, isHuman = true)

            println("BeforeClass")
        }

        @AfterClass
        @JvmStatic
        fun tearDownCommon() {
            /*
            Este metodo se ejecuta despues de todos los tests
            */
            juan = User()
            println("AfterClass")
        }
    }

    @Test
    fun checkHumanTest() {
        assertFalse("mensaje cuando el test false booleano falla", assertions.checkHuman(bot))
        assertTrue("mensaje cuando el test true booleano falla", assertions.checkHuman(juan))

        println("checkHumanTest")
    }

    @Test
    fun checkNoNullUserTest() {
        assertNotNull("El usuario no es nulo", juan)

        println("checkNoNullUserTest")
    }

    @Test
    fun checkNotSameUserTest() {
        assertNotSame(bot, juan)
        println("checkNotSameUserTest")
    }

    @Test
    fun checkSameUserTest() {
        val copyJuan = juan

        assertSame(juan, copyJuan)
        println("checkSameUserTest")
    }
}