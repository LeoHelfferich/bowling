package com.eurospace.bowling.data

/**
 * Represents a game of bowling.
 */
sealed interface Game {
    val players: List<Player>
    val currentFrame: Int?
    val currentPlayer: Player?
    val totalScore: Int
        get() = players.sumOf { it.totalScore }

    val isOngoing: Boolean
    val isFinished: Boolean

    /**
     * Represents a game at the start
     */
    class GameAtStart(
        override val players: List<Player>
    ) : Game {
        override val currentFrame: Int? = null
        override val currentPlayer: Player? = null
        override val isFinished: Boolean = false
        override val isOngoing: Boolean = false
    }

    /**
     * Represents a game in progress
     */
    class GameInProgress(
        override val players: List<Player>,
        override val currentFrame: Int,
        override val currentPlayer: Player
    ) : Game {
        override val isFinished: Boolean = false
        override val isOngoing: Boolean = true
    }

    /**
     * Represents a finished game
     */
    class GameOver(
        override val players: List<Player>
    ) : Game {
        override val currentFrame: Int? = null
        override val currentPlayer: Player? = null
        override val isFinished: Boolean = true
        override val isOngoing: Boolean = false
    }
}
