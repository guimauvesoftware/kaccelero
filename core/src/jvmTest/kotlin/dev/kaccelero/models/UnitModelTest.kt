package dev.kaccelero.models

import kotlin.test.Test
import kotlin.test.assertEquals

class UnitModelTest {

    @Test
    fun testIdIsUnit() {
        assertEquals(Unit, UnitModel.id)
    }

}
