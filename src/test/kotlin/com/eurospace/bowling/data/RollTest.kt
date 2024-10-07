package com.eurospace.bowling.data

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RollTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `should not throw on correct input data`(
        pins: Int
    ) {
        assertDoesNotThrow { Roll(pins) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 11])
    fun `should throw on invalid input`(
        pins: Int
    ) {
        assertThrows<IllegalArgumentException> { Roll(pins) }
    }
}