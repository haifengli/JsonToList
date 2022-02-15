package com.wayfair.jsontolist.util

import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

class RatingConverter {
    /**
     *  Rounds rating to the nearest 0.5 (ex, 1.5, 2, 3.5, 4)
     */
    fun convertRating(rating: Double): String {
        val floorVal = floor(rating)
        val ceilingVal = ceil(rating)
        val format = DecimalFormat("#.#")

        if (floorVal == ceilingVal) {
            return format.format(floorVal)
        }

        val midValue = floorVal + 0.5
        val distToCeil = ceilingVal - rating
        val distToMid = abs(midValue - rating)
        val distToFloor = rating - floorVal

        val smallestDist = distToCeil.coerceAtMost(distToMid.coerceAtMost(distToFloor))
        if (smallestDist == distToCeil) {
            return format.format(ceilingVal)
        } else if (smallestDist == distToMid) {
            return format.format(midValue)
        }

        return format.format(floorVal)
    }
}