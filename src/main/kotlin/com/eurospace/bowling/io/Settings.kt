package com.eurospace.bowling.io

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class Settings {

    @Bean
    fun scanner(): Scanner = Scanner(System.`in`)
}
