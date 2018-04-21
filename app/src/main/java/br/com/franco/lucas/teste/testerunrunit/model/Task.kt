package br.com.franco.lucas.teste.testerunrunit.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by MacMini on 16/03/2018.
 */
open class Task() : RealmObject(), Parcelable{

    @PrimaryKey
    var id : Long = 0
    var uid: Long = 0
    var _permission_: Boolean? = null
    var title : String? = ""
    var is_working_on : Boolean? = null
    var responsible_id : String? = ""
    var user_id : String = ""
    var type_id : Long = 0
    var project_id : Long = 0
    var close_date: Date? = null
    var priority : String? = ""
    var is_closed : Boolean? = null
    var on_going : Boolean? = null
    var created_at : Date? = null
    var start_date : Date? = null
    var current_worked_time : String = ""
    var approved : Boolean? = null
    var is_scheduled : Boolean? = null
    var client_name : String = ""
    var project_name : String = ""
    var type_name : String = ""
    var user_name : String = ""
    var responsible_name : String = ""
    var team_name : String? = ""
    var task_state_name : String? = ""
    var task_status_name : String? = ""
    var type_color : String? = ""
    var state : String? = ""
    var overdue : String? = ""
    var time_worked : Int? = 0
    var time_pending : Int? = 0
    var time_total : Int? = 0
    var time_progress : Double? = 0.0
    var activities_7_days_ago : Int? = 0
    var activities_6_days_ago : Int? = 0
    var activities_5_days_ago : Int? = 0
    var activities_4_days_ago : Int? = 0
    var activities_3_days_ago : Int? = 0
    var activities_2_days_ago : Int? = 0
    var activities_1_days_ago : Int? = 0
    var activities : Int? = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        uid = parcel.readLong()
        _permission_ = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        title = parcel.readString()
        is_working_on = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        responsible_id = parcel.readString()
        user_id = parcel.readString()
        type_id = parcel.readLong()
        project_id = parcel.readLong()
        priority = parcel.readString()
        is_closed = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        on_going = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        current_worked_time = parcel.readString()
        approved = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        is_scheduled = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        client_name = parcel.readString()
        project_name = parcel.readString()
        type_name = parcel.readString()
        user_name = parcel.readString()
        responsible_name = parcel.readString()
        team_name = parcel.readString()
        task_state_name = parcel.readString()
        task_status_name = parcel.readString()
        type_color = parcel.readString()
        state = parcel.readString()
        overdue = parcel.readString()
        time_worked = parcel.readValue(Int::class.java.classLoader) as? Int
        time_pending = parcel.readValue(Int::class.java.classLoader) as? Int
        time_total = parcel.readValue(Int::class.java.classLoader) as? Int
        time_progress = parcel.readValue(Double::class.java.classLoader) as? Double
        activities_7_days_ago = parcel.readValue(Int::class.java.classLoader) as? Int
        activities_6_days_ago = parcel.readValue(Int::class.java.classLoader) as? Int
        activities_5_days_ago = parcel.readValue(Int::class.java.classLoader) as? Int
        activities_4_days_ago = parcel.readValue(Int::class.java.classLoader) as? Int
        activities_3_days_ago = parcel.readValue(Int::class.java.classLoader) as? Int
        activities_2_days_ago = parcel.readValue(Int::class.java.classLoader) as? Int
        activities_1_days_ago = parcel.readValue(Int::class.java.classLoader) as? Int
        activities = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(uid)
        parcel.writeValue(_permission_)
        parcel.writeString(title)
        parcel.writeValue(is_working_on)
        parcel.writeString(responsible_id)
        parcel.writeString(user_id)
        parcel.writeLong(type_id)
        parcel.writeLong(project_id)
        parcel.writeString(priority)
        parcel.writeValue(is_closed)
        parcel.writeValue(on_going)
        parcel.writeString(current_worked_time)
        parcel.writeValue(approved)
        parcel.writeValue(is_scheduled)
        parcel.writeString(client_name)
        parcel.writeString(project_name)
        parcel.writeString(type_name)
        parcel.writeString(user_name)
        parcel.writeString(responsible_name)
        parcel.writeString(team_name)
        parcel.writeString(task_state_name)
        parcel.writeString(task_status_name)
        parcel.writeString(type_color)
        parcel.writeString(state)
        parcel.writeString(overdue)
        parcel.writeValue(time_worked)
        parcel.writeValue(time_pending)
        parcel.writeValue(time_total)
        parcel.writeValue(time_progress)
        parcel.writeValue(activities_7_days_ago)
        parcel.writeValue(activities_6_days_ago)
        parcel.writeValue(activities_5_days_ago)
        parcel.writeValue(activities_4_days_ago)
        parcel.writeValue(activities_3_days_ago)
        parcel.writeValue(activities_2_days_ago)
        parcel.writeValue(activities_1_days_ago)
        parcel.writeValue(activities)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }


}


