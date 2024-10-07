package com.eurospace.bowling.data

/**
 * Represents a frame in a game of bowling.
 */
sealed interface Frame {

    val totalScore: Int

    data object EmptyFrame : Frame {
        override val totalScore: Int = 0
    }

    sealed interface InGameFrame : Frame {
        val firstRoll: Roll
        val secondRoll: Roll
        val bonus: Bonus

        data class NotLastFrame(
            override val firstRoll: Roll,
            override val secondRoll: Roll,
            override val bonus: Bonus
        ) : InGameFrame {
            override val totalScore: Int
                get() = firstRoll.pins + secondRoll.pins + bonus.additionalPins
        }

        data class LastFrame(
            override val firstRoll: Roll,
            override val secondRoll: Roll,
            private val thirdRoll: Roll,
            override val bonus: Bonus
        ) : InGameFrame {
            override val totalScore: Int
                get() = firstRoll.pins + secondRoll.pins + thirdRoll.pins + bonus.additionalPins
        }
    }
}


@JvmInline
value class Roll(val pins: Int) {
    init {
        require(pins in 0..10) { "A roll must have between 0 and 10 pins" }
    }
}