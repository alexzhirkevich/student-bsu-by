@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

package github.alexzhirkevich.studentbsuby.util

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SettingsListener
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.CoreFoundation.CFDictionaryAddValue
import platform.CoreFoundation.CFDictionaryCreateMutable
import platform.CoreFoundation.CFMutableDictionaryRef
import platform.CoreFoundation.CFTypeRef
import platform.CoreFoundation.CFRelease
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFAllocatorDefault
import platform.CoreFoundation.kCFBooleanTrue
import platform.CoreFoundation.kCFTypeDictionaryKeyCallBacks
import platform.CoreFoundation.kCFTypeDictionaryValueCallBacks
import platform.Foundation.*
import platform.Security.SecItemAdd
import platform.Security.SecItemCopyMatching
import platform.Security.SecItemDelete
import platform.Security.SecItemUpdate
import platform.Security.kSecAttrAccount
import platform.Security.kSecAttrService
import platform.Security.kSecClass
import platform.Security.kSecClassGenericPassword
import platform.Security.kSecMatchLimit
import platform.Security.kSecMatchLimitAll
import platform.Security.kSecMatchLimitOne
import platform.Security.kSecReturnAttributes
import platform.Security.kSecReturnData
import platform.Security.kSecValueData

/**
 * [ObservableSettings] backed by the iOS Keychain ([kSecClassGenericPassword] items with
 * [kSecAttrService] = [service]). Replaces Android EncryptedSharedPreferences for secure storage.
 * */
class KeychainSettings(private val service: String) : ObservableSettings {

    private val listeners = mutableMapOf<String, MutableList<() -> Unit>>()

    override val keys: Set<String>
        get() = keychainOp(
            null,
            kSecReturnAttributes to kCFBooleanTrue,
            kSecMatchLimit to kSecMatchLimitAll
        ) { query ->
            memScoped {
                val result = alloc<CFTypeRefVar>()
                val status = SecItemCopyMatching(query, result.ptr)
                if (status == 0) {
                    val array = CFBridgingRelease(result.value) as? NSArray
                    if (array == null) {
                        emptySet()
                    } else {
                        (0 until array.count.toInt()).mapNotNull { index ->
                            val item = array.objectAtIndex(index.toULong()) as? NSDictionary
                            item?.objectForKey("acct") as? String
                        }.toSet()
                    }
                } else {
                    emptySet()
                }
            }
        }

    override val size: Int get() = keys.size

    override fun clear() {
        val cleared = keys
        keychainOp(null) { query ->
            SecItemDelete(query)
        }
        cleared.forEach(::invokeListeners)
    }

    override fun remove(key: String) {
        keychainOp(key) { query ->
            SecItemDelete(query)
        }
        invokeListeners(key)
    }

    override fun hasKey(key: String): Boolean = keychainOp(key) { query ->
        SecItemCopyMatching(query, null) == 0
    }

    override fun putInt(key: String, value: Int) = putStringValue(key, value.toString())

    override fun getInt(key: String, defaultValue: Int): Int =
        getIntOrNull(key) ?: defaultValue

    override fun getIntOrNull(key: String): Int? = getStringValue(key)?.toIntOrNull()

    override fun putLong(key: String, value: Long) = putStringValue(key, value.toString())

    override fun getLong(key: String, defaultValue: Long): Long =
        getLongOrNull(key) ?: defaultValue

    override fun getLongOrNull(key: String): Long? = getStringValue(key)?.toLongOrNull()

    override fun putString(key: String, value: String) = putStringValue(key, value)

    override fun getString(key: String, defaultValue: String): String =
        getStringOrNull(key) ?: defaultValue

    override fun getStringOrNull(key: String): String? = getStringValue(key)

    override fun putFloat(key: String, value: Float) = putStringValue(key, value.toString())

    override fun getFloat(key: String, defaultValue: Float): Float =
        getFloatOrNull(key) ?: defaultValue

    override fun getFloatOrNull(key: String): Float? = getStringValue(key)?.toFloatOrNull()

    override fun putDouble(key: String, value: Double) = putStringValue(key, value.toString())

    override fun getDouble(key: String, defaultValue: Double): Double =
        getDoubleOrNull(key) ?: defaultValue

    override fun getDoubleOrNull(key: String): Double? = getStringValue(key)?.toDoubleOrNull()

    override fun putBoolean(key: String, value: Boolean) = putStringValue(key, value.toString())

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        getBooleanOrNull(key) ?: defaultValue

    override fun getBooleanOrNull(key: String): Boolean? =
        getStringValue(key)?.toBooleanStrictOrNull()

    override fun addIntListener(
        key: String,
        defaultValue: Int,
        callback: (Int) -> Unit
    ): SettingsListener = addListener(key) { callback(getInt(key, defaultValue)) }

    override fun addLongListener(
        key: String,
        defaultValue: Long,
        callback: (Long) -> Unit
    ): SettingsListener = addListener(key) { callback(getLong(key, defaultValue)) }

    override fun addStringListener(
        key: String,
        defaultValue: String,
        callback: (String) -> Unit
    ): SettingsListener = addListener(key) { callback(getString(key, defaultValue)) }

    override fun addFloatListener(
        key: String,
        defaultValue: Float,
        callback: (Float) -> Unit
    ): SettingsListener = addListener(key) { callback(getFloat(key, defaultValue)) }

    override fun addDoubleListener(
        key: String,
        defaultValue: Double,
        callback: (Double) -> Unit
    ): SettingsListener = addListener(key) { callback(getDouble(key, defaultValue)) }

    override fun addBooleanListener(
        key: String,
        defaultValue: Boolean,
        callback: (Boolean) -> Unit
    ): SettingsListener = addListener(key) { callback(getBoolean(key, defaultValue)) }

    override fun addIntOrNullListener(
        key: String,
        callback: (Int?) -> Unit
    ): SettingsListener = addListener(key) { callback(getIntOrNull(key)) }

    override fun addLongOrNullListener(
        key: String,
        callback: (Long?) -> Unit
    ): SettingsListener = addListener(key) { callback(getLongOrNull(key)) }

    override fun addStringOrNullListener(
        key: String,
        callback: (String?) -> Unit
    ): SettingsListener = addListener(key) { callback(getStringOrNull(key)) }

    override fun addFloatOrNullListener(
        key: String,
        callback: (Float?) -> Unit
    ): SettingsListener = addListener(key) { callback(getFloatOrNull(key)) }

    override fun addDoubleOrNullListener(
        key: String,
        callback: (Double?) -> Unit
    ): SettingsListener = addListener(key) { callback(getDoubleOrNull(key)) }

    override fun addBooleanOrNullListener(
        key: String,
        callback: (Boolean?) -> Unit
    ): SettingsListener = addListener(key) { callback(getBooleanOrNull(key)) }

    private fun addListener(key: String, block: () -> Unit): SettingsListener {
        listeners.getOrPut(key) { mutableListOf() }.add(block)
        return Listener(key, block)
    }

    private fun invokeListeners(key: String) {
        listeners[key]?.toList()?.forEach { it.invoke() }
    }

    private inner class Listener(
        private val key: String,
        private val block: () -> Unit
    ) : SettingsListener {
        override fun deactivate() {
            listeners[key]?.remove(block)
        }
    }

    private fun getStringValue(key: String): String? = getData(key)?.let {
        NSString.create(data = it, encoding = NSUTF8StringEncoding) as String?
    }

    private fun putStringValue(key: String, value: String) {
        val data = NSString.create(string = value)
            .dataUsingEncoding(NSUTF8StringEncoding) ?: return
        putData(key, data)
        invokeListeners(key)
    }

    private fun getData(key: String): NSData? = keychainOp(
        key,
        kSecReturnData to kCFBooleanTrue,
        kSecMatchLimit to kSecMatchLimitOne
    ) { query ->
        memScoped {
            val result = alloc<CFTypeRefVar>()
            val status = SecItemCopyMatching(query, result.ptr)
            if (status == 0) {
                CFBridgingRelease(result.value) as? NSData
            } else {
                null
            }
        }
    }

    private fun putData(key: String, data: NSData) {
        val cfData = CFBridgingRetain(data)
        try {
            if (hasKey(key)) {
                keychainOp(key) { query ->
                    val attributes = CFDictionaryCreateMutable(
                        kCFAllocatorDefault,
                        1,
                        kCFTypeDictionaryKeyCallBacks.ptr,
                        kCFTypeDictionaryValueCallBacks.ptr
                    )
                    try {
                        CFDictionaryAddValue(attributes, kSecValueData, cfData)
                        SecItemUpdate(query, attributes)
                    } finally {
                        CFRelease(attributes)
                    }
                }
            } else {
                keychainOp(key, kSecValueData to cfData) { query ->
                    SecItemAdd(query, null)
                }
            }
        } finally {
            CFBridgingRelease(cfData)
        }
    }

    private fun <T> keychainOp(
        key: String?,
        vararg extras: Pair<CFTypeRef?, CFTypeRef?>,
        block: (CFMutableDictionaryRef?) -> T
    ): T {
        val cfService = CFBridgingRetain(service)
        val cfKey = key?.let { CFBridgingRetain(it) }
        val query = CFDictionaryCreateMutable(
            kCFAllocatorDefault,
            (3 + extras.size).toLong(),
            kCFTypeDictionaryKeyCallBacks.ptr,
            kCFTypeDictionaryValueCallBacks.ptr
        )
        return try {
            CFDictionaryAddValue(query, kSecClass, kSecClassGenericPassword)
            CFDictionaryAddValue(query, kSecAttrService, cfService)
            if (cfKey != null) {
                CFDictionaryAddValue(query, kSecAttrAccount, cfKey)
            }
            extras.forEach { (k, v) ->
                CFDictionaryAddValue(query, k, v)
            }
            block(query)
        } finally {
            CFRelease(query)
            cfKey?.let { CFBridgingRelease(it) }
            CFBridgingRelease(cfService)
        }
    }
}
