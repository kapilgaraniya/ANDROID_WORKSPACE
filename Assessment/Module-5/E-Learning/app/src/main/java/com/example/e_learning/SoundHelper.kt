package com.example.e_learning

import android.content.Context
import android.media.MediaPlayer

class SoundHelper(private val context: Context) {

    fun playSound(correct: Boolean) {
        var soundRes = if (correct) R.raw.correct_choice_43861 else R.raw.buzzer_4_83895
        var mediaPlayer = MediaPlayer.create(context, soundRes)
        mediaPlayer.start()
    }
}
