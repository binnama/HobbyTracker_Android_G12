package hiof.g12

import dagger.hilt.android.testing.HiltAndroidTest
import hiof.g12.features.convertToMinutesAndSeconds
import org.junit.Test
import java.util.Date


// Noen kappe unit tester på DateConverter funksjon convertToMinutesAndSeconds()
@HiltAndroidTest
class DateConverterTest {

    @Test
    fun verify_DateConverterTest_returnsActivityStillRunningWhenStopIsNull() {
        val startDate = Date()
        val stopDate: Date? = null

        val result = convertToMinutesAndSeconds(startDate, stopDate)

        assert(result == "Activity still running")
    }

    @Test
    fun verify_DateConverterTest_returnsExpectedMinutesAndSeconds() {

        // Tester om denne returnerer 00:00 fordi start og slutt tid som starter på samme tid, vil bli 00:00.
        val startDate = Date()
        val stopDate = Date()

        val result = convertToMinutesAndSeconds(startDate, stopDate)

        assert(result == "00:00")
    }

    @Test

    fun verify_DateConvertTest_returnsUnexpectedMinutesAndSeconds() {
        // Skal returnere 00:00, men her tester jeg om resulten ikke er lik 01:00.
        val startDate = Date()
        val stopDate = Date()

        val result = convertToMinutesAndSeconds(startDate, stopDate)

        assert(result != "01:00")
    }
}