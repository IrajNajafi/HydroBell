package com.irajnajafi1988gmail.hydrobell.data.roomDatabase.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.ActivityLevel
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Environment
import com.irajnajafi1988gmail.hydrobell.ui.feature.setupUserProfile.model.Gender

class UserProfileConverters {

    //GenderConverters
    @TypeConverter
    fun fromGender(gender: Gender): String = gender.name

    @TypeConverter
    fun toGender(value: String): Gender = try {
        Gender.valueOf(value)
    } catch (e: Exception) {
        Gender.NONE
    }


    //ActivityConverters
    @TypeConverter
    fun fromActivity(activity: ActivityLevel): String = activity.name

    @TypeConverters
    fun toActivityLaval(value: String): ActivityLevel = try {
        ActivityLevel.valueOf(value)
    } catch (e: kotlin.Exception) {
        ActivityLevel.NONE
    }

    //Environment
    @TypeConverter
    fun fromEnvironment(env: Environment): String = env.name

    @TypeConverter
    fun toEnvironment(value: String): Environment = try {
        Environment.valueOf(value)
    } catch (e: Exception) {
        Environment.NONE
    }

}