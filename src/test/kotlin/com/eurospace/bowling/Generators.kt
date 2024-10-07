package com.eurospace.bowling

import com.eurospace.bowling.data.Bonus
import com.eurospace.bowling.data.Bonus.Spare
import com.eurospace.bowling.data.Bonus.Strike
import com.eurospace.bowling.data.Frame
import com.eurospace.bowling.data.Frame.InGameFrame.LastFrame
import com.eurospace.bowling.data.Frame.InGameFrame.NotLastFrame
import com.eurospace.bowling.data.Game
import com.eurospace.bowling.data.Game.GameAtStart
import com.eurospace.bowling.data.Player
import com.eurospace.bowling.data.Roll
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.choice
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.of
import io.kotest.property.arbitrary.string

/**
 * Arbitrary generator for [Roll] with 0 to 10 pins
 */
internal fun Arb.Companion.roll(): Arb<Roll> = Arb.int(min = 0, max = 10).map { Roll(it) }

/**
 * Arbitrary generator for [Spare] [Bonus]
 */
internal fun Arb.Companion.spare(): Arb<Bonus> = Arb.roll().map { Spare(it.pins) }

/**
 * Arbitrary generator for [Strike] [Bonus]
 */
internal fun Arb.Companion.strike(): Arb<Bonus> = Arb.bind(
    Arb.roll(),
    Arb.roll()
) { roll1, roll2 -> Strike(roll1.pins, roll2.pins) }

/**
 * Arbitrary generator for [Bonus]
 */
internal fun Arb.Companion.bonus(): Arb<Bonus> = Arb.choice(spare(), strike(), Arb.of(Bonus.NoBonus))

internal fun Arb.Companion.inGameFrame(): Arb<Frame> =
    Arb.bind(
        Arb.roll(),
        Arb.roll(),
        Arb.bonus()
    ) { roll1, roll2, bonus -> NotLastFrame(roll1, roll2, bonus) }

internal fun Arb.Companion.lastFrame(): Arb<Frame> =
    Arb.bind(
        Arb.roll(),
        Arb.roll(),
        Arb.roll(),
        Arb.bonus()
    ) { roll1, roll2, roll3, bonus -> LastFrame(roll1, roll2, roll3, bonus) }

internal fun Arb.Companion.emptyFrame(): Arb<Frame> = Arb.of(Frame.EmptyFrame)

internal fun Arb.Companion.frame(): Arb<Frame> = Arb.choice(emptyFrame(), inGameFrame(), lastFrame())

/**
 * Arbitrary generator for [Player] with 1 to 10 characters
 */
internal fun Arb.Companion.player(): Arb<Player> = Arb.bind(
    Arb.string(1..10), // don't need empty or long names
    Arb.int(range = 1..10)
) { name, index -> Player(index, name) }

/**
 * Arbitrary generator for [Game] at start, with 1 to 10 players
 */
internal fun Arb.Companion.gameAtStart(): Arb<Game> = Arb.list(Arb.player(), 1..10)
    .map { GameAtStart(it) }
