package com.eurospace.bowling.data

import com.eurospace.bowling.data.Frame.EmptyFrame

/**
 * Representation of a Player
 */
data class Player(
    val index: Int,
    val name: String,
    val frames: MutableMap<Int, Frame> = (1..10).associateWith { EmptyFrame }.toMutableMap()
) {
    val totalScore: Int
        get() = frames.values.sumOf { it.totalScore }

    init {
        require(frames.size == 10) { "A player must have exactly 10 frames" }
        require(name.isNotBlank()) { "A player must have a name" }
        require(index >= 0) { "A player must have a non-negative index" }
    }
}