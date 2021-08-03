/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SleepDatabaseDao {

    // insert single night into table
    @Insert
    suspend fun insert(night: SleepNight)

    // updates a single night
    @Update
    suspend fun update(night: SleepNight)

    // returns all columns (*) (= one SleepNight) from our table
    // where nightId (column in DB) matches given key (see our function param)
    // will return one SleepNight because key is unique or NULL
    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
    suspend fun get(key: Long): SleepNight?

    // clears complete table
    @Query("DELETE FROM daily_sleep_quality_table")
    suspend fun clear()

    // Room supports LiveData, just gets all nights once and then attach observers
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>

    // Fetches current night (with trick by ordering them and limiting to last night)
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    suspend fun getTonight(): SleepNight?

}
