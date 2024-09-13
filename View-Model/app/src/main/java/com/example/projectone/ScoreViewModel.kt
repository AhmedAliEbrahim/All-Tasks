package com.example.projectone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private val _teamAScore = MutableLiveData(0)
    val teamAScore: LiveData<Int> get() = _teamAScore

    private val _teamBScore = MutableLiveData(0)
    val teamBScore: LiveData<Int> get() = _teamBScore

    fun increaseScoreForTeamA() {
        _teamAScore.value = (_teamAScore.value ?: 0) + 1
    }

    fun increaseScoreForTeamB() {
        _teamBScore.value = (_teamBScore.value ?: 0) + 1
    }

    fun getWinner(): String {
        return when {
            _teamAScore.value!! > _teamBScore.value!! -> "Team A"
            _teamAScore.value!! < _teamBScore.value!! -> "Team B"
            else -> "Draw"
        }
    }
}
