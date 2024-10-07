package com.eurospace.bowling.data

sealed interface Bonus {

    val additionalPins: Int

    data class Strike(
        val firstBonus: Int,
        val secondBonus: Int
    ) : Bonus {
        override val additionalPins: Int
            get() = firstBonus + secondBonus
    }

    data class Spare(override val additionalPins: Int) : Bonus

    data object NoBonus : Bonus {
        override val additionalPins: Int = 0
    }
}
