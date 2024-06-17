package dev.kaccelero.models

import kotlin.test.Test
import kotlin.test.assertEquals

class IModelTest {

    @Test
    fun testParentIdIsUnit() {
        val model = object : IModel<Long, Unit, Unit> {
            override val id: Long = 1
        }
        assertEquals(Unit, model.parentId)
    }

}
