package github.alexzhirkevich.studentbsuby.util

import com.russhwolf.settings.ObservableSettings
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

inline fun <reified T> sharedPreferences(
    preferences: ObservableSettings,
    default: T,
    noinline onChanged : (T) -> Unit = {},
) : PreferencePersistence<T>{
    return sharedPreferences(T::class, preferences, default, onChanged)
}

@Suppress("unchecked_cast")
fun <T> sharedPreferences(
    clazz: KClass<*>,
    preferences: ObservableSettings,
    default: T,
    onChanged : (T) -> Unit = {},
) : PreferencePersistence<T> {
    return when(clazz) {
        Boolean::class -> BooleanPreferencePersistence(
            preferences = preferences,
            onChanged = onChanged as (Boolean) -> Unit,
            default = default as Boolean
        ) as PreferencePersistence<T>

        String::class -> StringPreferencePersistence(
            preferences = preferences,
            onChanged = onChanged as (String) -> Unit,
            default = default as String
        ) as PreferencePersistence<T>

        Int::class -> IntPreferencePersistence(
            preferences =  preferences,
            onChanged = onChanged as (Int) -> Unit,
            default = default as Int
        ) as PreferencePersistence<T>

        Long::class -> LongPreferencePersistence(
            preferences = preferences,
            onChanged = onChanged as (Long) -> Unit,
            default = default as Long
        ) as PreferencePersistence<T>

        Float::class -> FloatPreferencePersistence(
            preferences = preferences,
            onChanged = onChanged as (Float) -> Unit,
            default = default as Float
        ) as PreferencePersistence<T>

        else -> try {
            StringSetPreferencePersistence(
                preferences = preferences,
                onChanged = onChanged as (Set<String>) -> Unit,
                default = default as Set<String>
            ) as PreferencePersistence<T>
        }catch (c : ClassCastException){
            throw IllegalArgumentException("$clazz cannot be stored in shared preferences")
        }
    }
}


sealed class PreferencePersistence<T>(
    val preferences: ObservableSettings,
    val default : T,
    val onChanged: (T) -> Unit,
) {
    operator fun getValue(self: Any?, property: KProperty<*>) : T {
        return get(property.name) ?: default.also {
            setValue(self,property,it)
        }
    }

    protected abstract fun get(key : String) : T?

    abstract operator fun setValue(self: Any?, property: KProperty<*>, t: T)

}

class BooleanPreferencePersistence(
    preferences: ObservableSettings,
    default: Boolean,
    onChanged: (Boolean) -> Unit,
) : PreferencePersistence<Boolean>(preferences,default,onChanged) {

    override fun get(key: String): Boolean? = preferences.getBooleanOrNull(key)

    override fun setValue(self: Any?, property: KProperty<*>, t: Boolean) {
        preferences.putBoolean(property.name,t)
        onChanged(t)
    }
}

class StringPreferencePersistence(
    preferences: ObservableSettings,
    default: String,
    onChanged: (String) -> Unit,
) : PreferencePersistence<String>(preferences, default,onChanged) {

    override fun get(key: String): String? = preferences.getStringOrNull(key)

    override fun setValue(
        self : Any?,
        property: KProperty<*>,
        t: String
    ) {
        preferences.putString(property.name,t)
        onChanged(t)
    }
}

class IntPreferencePersistence(
    preferences: ObservableSettings,
    default: Int,
    onChanged: (Int) -> Unit,
) : PreferencePersistence<Int>(preferences, default,onChanged) {

    override fun get(key: String): Int? = preferences.getIntOrNull(key)

    override fun setValue(
        self : Any?,
        property: KProperty<*>,
        t: Int
    ) {
        preferences.putInt(property.name,t)
        onChanged(t)
    }
}

class LongPreferencePersistence(
    preferences: ObservableSettings,
    default: Long,
    onChanged: (Long) -> Unit,
) : PreferencePersistence<Long>(preferences,default,onChanged) {

    override fun get(key: String): Long? = preferences.getLongOrNull(key)

    override fun setValue(
        self : Any?,
        property: KProperty<*>,
        t: Long
    ) {
        preferences.putLong(property.name,t)
        onChanged(t)
    }
}

class FloatPreferencePersistence(
    preferences: ObservableSettings,
    default: Float,
    onChanged: (Float) -> Unit,
) : PreferencePersistence<Float>(preferences, default,onChanged) {

    override fun get(key: String): Float? = preferences.getFloatOrNull(key)

    override fun setValue(
        self : Any?,
        property: KProperty<*>,
        t: Float
    ) {
        preferences.putFloat(property.name,t)
        onChanged(t)
    }
}

class StringSetPreferencePersistence(
    preferences: ObservableSettings,
    default: Set<String>,
    onChanged: (Set<String>) -> Unit,
) : PreferencePersistence<Set<String>>(preferences, default,onChanged) {

    override fun get(key: String): Set<String>? = preferences.getStringOrNull(key)?.let {
        kotlin.runCatching {
            Json.decodeFromString(SetSerializer(String.serializer()), it)
        }.getOrNull()
    }

    override fun setValue(
        self : Any?,
        property: KProperty<*>,
        t: Set<String>
    ) {
        preferences.putString(property.name, Json.encodeToString(SetSerializer(String.serializer()), t))
        onChanged(t)
    }
}
