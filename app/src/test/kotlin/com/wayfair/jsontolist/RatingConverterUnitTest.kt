package com.wayfair.jsontolist


import com.wayfair.jsontolist.util.RatingConverter
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class RatingConverterUnitTest {

    private var ratingConverter : RatingConverter? = null


    @Before
    fun setup() {
        ratingConverter = RatingConverter()
    }


    @Test
    fun testcases() {
        assertEquals("4", ratingConverter?.convertRating(4.1))
        assertEquals("4.5", ratingConverter?.convertRating(4.5))
        assertEquals("4.5", ratingConverter?.convertRating(4.6))
        assertEquals("5", ratingConverter?.convertRating(4.75))
        assertEquals("4.5", ratingConverter?.convertRating(4.74448))
    }
}