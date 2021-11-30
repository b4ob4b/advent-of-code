package com.baobab.y2018

import utils.Day
import utils.IO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Day04(input: String) : Day() {

    private val shiftsRecords = input.split("\n")
        .map { it.toShiftRecord() }
        .sortedBy { it.localDateTime }

    private val sleepyTime = shiftsRecords.scan()
        .filter { it.guard.status == Status.ASLEEP }

    override fun part1(): Int {
        val mostSleepyGuard = sleepyTime
            .groupingBy { it.guard.id }
            .eachCount()
            .toList().maxByOrNull { it.second }?.first

        val mostSleepyMinute = sleepyTime.filter { it.guard.id == mostSleepyGuard }
            .groupingBy { it.localTime }
            .eachCount()
            .toList().maxByOrNull { it.second }?.first
            ?.minute

        if (mostSleepyGuard == null || mostSleepyMinute == null) throw Exception("everybody awake")
        return mostSleepyMinute * mostSleepyGuard
    }

    override fun part2(): Int {
        val mostSleepyMinutePerGuard = sleepyTime
            .groupingBy { Pair(it.localTime, it.guard.id) }
            .eachCount()
            .toList().maxByOrNull { it.second }?.first

        val minute = mostSleepyMinutePerGuard?.first?.minute
        val guardId = mostSleepyMinutePerGuard?.second

        if (minute == null || guardId == null) throw Exception("everybody awake")
        return minute * guardId
    }

    private fun String.toShiftRecord(): ShiftRecord {
        val parts = this.split("] ")
        val dateTimeParts = parts[0].drop(1).split("-| |:".toRegex()).map { it.toInt() }
        return ShiftRecord(
            LocalDateTime.of(
                LocalDate.of(
                    dateTimeParts[0],
                    dateTimeParts[1],
                    dateTimeParts[2],
                ),
                LocalTime.of(
                    dateTimeParts[3],
                    dateTimeParts[4],
                ),
            ), parts[1]
        )
    }

    private data class ShiftTimeStamp(
        val localDate: LocalDate,
        val localTime: LocalTime,
        val guard: Guard,
    )

    private data class Guard(val id: Int, val status: Status)
    private enum class Status { AWAKE, ASLEEP }

    private data class ShiftRecord(val localDateTime: LocalDateTime, val record: String) {
        fun getGuardId() = record.split(" ")[1].drop(1).toInt()
    }

    private fun List<ShiftRecord>.getCurrent(localDateTime: LocalDateTime): ShiftRecord {
        return this.filter { it.localDateTime <= localDateTime }.last()
    }

    private fun List<ShiftRecord>.scan(): List<ShiftTimeStamp> {
        var currentTime = shiftsRecords.first().localDateTime
        var guardId = -1
        val shiftTimeStamps = mutableSetOf<ShiftTimeStamp>()
        while (currentTime <= shiftsRecords.last().localDateTime) {
            val currentRecord = shiftsRecords.getCurrent(currentTime)
            var status = Status.AWAKE
            if (currentRecord.record.contains("Guard")) {
                guardId = currentRecord.getGuardId()
            }
            if (currentRecord.record.contains("asleep")) {
                status = Status.ASLEEP
            }
            if (currentRecord.record.contains("awake")) {
                status = Status.AWAKE
            }
            shiftTimeStamps.add(
                ShiftTimeStamp(
                    currentTime.toLocalDate(),
                    currentTime.toLocalTime(),
                    Guard(guardId, status)
                )
            )
            currentTime = currentTime.plusMinutes(1)
        }
        return shiftTimeStamps.toList()
    }
}

fun main() {
    val sample = IO.readFile(2018, 4, IO.TYPE.SAMPLE)
    val input = IO.readFile(2018, 4, IO.TYPE.INPUT)

    Day04(input).solve()
}
