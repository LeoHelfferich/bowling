package com.eurospace.bowling.data

import net.jqwik.api.Data
import net.jqwik.api.ForAll
import net.jqwik.api.FromData
import net.jqwik.api.Property
import net.jqwik.api.Table
import net.jqwik.api.Tuple
import net.jqwik.api.Tuple.Tuple2
import net.jqwik.api.constraints.IntRange
import net.jqwik.api.constraints.NotBlank
import net.jqwik.api.constraints.NotEmpty
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Property
    fun `should not throw on correct input data`(
        @ForAll @NotEmpty @NotBlank name: String,
        @ForAll @IntRange(min = 0) index: Int
    ) {
        assertDoesNotThrow { Player(index, name) }
    }

    @Property
    @FromData("badTuples")
    fun `should throw on invalid input`(
        @ForAll @IntRange(min = 0) index: Int,
        @ForAll name: String
    ) {
        assertThrows<IllegalArgumentException> { Player(index, name) }
    }

    @Data
    fun badTuples(): Iterable<Tuple2<Int, String>> = Table.of(
        Tuple.of(-1, "Leo"),
        Tuple.of(0, ""),
        Tuple.of(1, " ")
    )

}