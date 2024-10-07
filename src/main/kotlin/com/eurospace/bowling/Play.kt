package com.eurospace.bowling

import com.eurospace.bowling.data.Game
import com.eurospace.bowling.data.Game.GameAtStart
import com.eurospace.bowling.data.Game.GameInProgress
import com.eurospace.bowling.data.Game.GameOver

/**
 * Main class to play a game of bowling.
 *
 * Can be used to play a new game, or to continue a game that was previously started.
 */
class Play(
    private val game: Game
) {

    /**
     * Play a game of bowling and return the final state of the game
     */
    fun gamedThrough(): GameOver = when (game) {
        is GameAtStart -> finishedFromStart()
        is GameInProgress -> finishedFromIntermediate()
        is GameOver -> game
    }

    private fun finishedFromIntermediate(): GameOver {
        TODO("Not yet implemented")
    }

    private fun finishedFromStart(): GameOver {
        TODO("Not yet implemented")
    }
}
