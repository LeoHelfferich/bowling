package com.eurospace.bowling.io

import com.eurospace.bowling.Play
import com.eurospace.bowling.data.Game.GameAtStart
import com.eurospace.bowling.data.Game.GameOver
import com.eurospace.bowling.data.Player
import org.springframework.boot.CommandLineRunner
import java.util.*

/**
 * Main class to interact with the user and play the game
 */
class UserInteraction(
    private val scanner: Scanner
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        printSeparator()
        printWelcome()

        val game = preparedGame()
        val finishedGame = finishedFor(game)

        printEndWithStatisticsOf(finishedGame)
        printSeparator()
    }

    // ----------------------------

    private fun finishedFor(game: GameAtStart): GameOver {
        return try {
            Play(game).gamedThrough()
        } catch (e: Throwable) {
            println("An error occurred while playing the game: ${e.message}")
            GameOver(game.players)
        }
    }

    private fun preparedGame(): GameAtStart {
        println("How many players are going to play?")
        val number = scanner.nextInt() // Ignore invalid input for now

        val players = (1..number).map {
            println("What is the name of player $it?")
            val name = scanner.next()
            Player(it, name)
        }

        return GameAtStart(players)
    }

    private fun printEndWithStatisticsOf(finishedGame: GameOver) {
        println("Thank you for playing the Bowling Game!")

        println("The final total score:${finishedGame.totalScore}")
        finishedGame.players.forEach { player ->
            println("\t${player.name} scored ${player.totalScore}")
        }

        println("Good bye!")
    }

    private fun printWelcome() {
        println("Welcome to the Bowling Game!")
    }

    private fun printSeparator() {
        println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
    }
}
