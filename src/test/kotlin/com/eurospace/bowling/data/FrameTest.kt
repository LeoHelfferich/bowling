package com.eurospace.bowling.data

import com.eurospace.bowling.frame
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.assertj.core.api.Assertions.assertThat

class FrameTest : ShouldSpec({

    should("be able to have a total score between 0 and 30") {
        checkAll(
            Arb.frame()
        ) { frame ->
            assertThat(frame.totalScore).isBetween(0, 30)
        }
    }

})
