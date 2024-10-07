package com.eurospace.bowling

import com.eurospace.bowling.io.UserInteraction
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.util.*

/**
 * Command line service to play the game
 */
@Service
class AppRunner(
    private val scanner: Scanner
) : CommandLineRunner by UserInteraction(scanner)
