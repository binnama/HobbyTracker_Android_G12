package hiof.g12.features

import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit


// Lagde egen funksjon for å konvertere dato hentet fra databasen til en mer lesbar dato.
// Hentet fra:  https://developer.android.com/reference/kotlin/android/icu/text/SimpleDateFormat
fun convertDateToLocalFormat(input: Date): String {
    val formattedDate = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault()).format(input)
    return formattedDate
}


// Lagde egen funksjon for å konvertere tiden til minutter og sekunder
fun convertToMinutesAndSeconds(start: Date, stop: Date?): String {

    // I tilfelle en aktivitet ikke er enda avsluttet.
    if (stop == null) {
        return "Activity still running"
    }

    val stopTimeMilliSeconds = stop.time
    val startTimeMilliSeconds = start.time

    val timeSpentMillis = maxOf(0L, stopTimeMilliSeconds - startTimeMilliSeconds)

    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeSpentMillis) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeSpentMillis) % 60
    val formattedTimeSpent = String.format("%02d:%02d", minutes, seconds)
    return formattedTimeSpent
}