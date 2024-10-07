package com.eurospace.bowling

import com.eurospace.bowling.data.Game.GameOver
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.assertj.core.api.Assertions.assertThat

/*
Information on Kotest and Property Based Testing:
https://kotest.io/docs/proptest/property-based-testing.html
 */

const val PERFECT_GAME_SCORE = 300

class PlayTest : ShouldSpec({

    should("play though a game and have valid finished state") {
        checkAll(
            Arb.gameAtStart()
        ) { game ->
            val play = Play(game)

            // ---
            val result = play.gamedThrough()
            // ---

            // State Testing
            assertThat(result).isInstanceOf(GameOver::class.java)
            assertThat(result.currentFrame).isNull()
            assertThat(result.currentPlayer).isNull()
            assertThat(result.isFinished).isTrue()
            assertThat(result.isOngoing).isFalse()
            assertThat(result.players).containsExactlyInAnyOrderElementsOf(game.players)

            // Corridor Testing: validity of scores
            assertThat(result.players).allSatisfy { player ->
                assertThat(player.totalScore).isBetween(0, PERFECT_GAME_SCORE)
            }
            assertThat(result.totalScore).isBetween(0, game.players.size * PERFECT_GAME_SCORE)
        }
    }



})
