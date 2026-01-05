package ru.deepdarkaddition.engine

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.saveddata.SavedData
import java.util.UUID

abstract class WorldDataHandler : SavedData() {
    /*
    В классе который наследуется от этого, обязательно должно быть это:

    companion object {

        fun load(nbt: CompoundTag): ИмяВашегоКласса {
            val data = ИмяВашегоКласса()
            Тут вы присваивайте значения ваших данных вашим переменным!
            что то вроде: if (nbt.contains("ваш ключ данных") имя_вашей_переменной = nbt.putВашТипДанных("ваш ключ данных")
            return data
        }

        fun get(sLevel: ServerLevel): ИмяВашегоКласса {
            return sLevel.dataStorage.computeIfAbsent(
                { tag -> load(tag) },
                { ИмяВашегоКласса() },
                ИмяВашегоКласса().DATA_NAME
            )
        }
    }

    */
    abstract val DATA_NAME: String

    enum class typesOfDataToSave {
        NONE,
        UUID,
        STRING,
        LONG,
        LONGARRAY,
        FLOAT,
        INT,
        INTARRAY,
        SHORT,
        DOUBLE,
        BYTE,
        BYTEARRAY
    }

    private var selectedTypeOfDataToSave: typesOfDataToSave = typesOfDataToSave.NONE

    var uuidDataToSave: Pair<String?, UUID?> = Pair<String?, UUID?>(null, null)
    var stringDataToSave: Pair<String?, String?> = Pair<String?, String?>(null, null)
    var longDataToSave: Pair<String?, Long?> = Pair<String?, Long?>(null, null)
    var longArrayDataToSave: Pair<String?, LongArray?> = Pair<String?, LongArray?>(null, null)
    var floatDataToSave: Pair<String?, Float?> = Pair<String?, Float?>(null, null)
    var intDataToSave: Pair<String?, Int?> = Pair<String?, Int?>(null, null)
    var intArrayDataToSave: Pair<String?, IntArray?> = Pair<String?, IntArray?>(null, null)
    var shortDataToSave: Pair<String?, Short?> = Pair<String?, Short?>(null, null)
    var doubleDataToSave: Pair<String?, Double?> = Pair<String?, Double?>(null, null)
    var byteDataToSave: Pair<String?, Byte?> = Pair<String?, Byte?>(null, null)
    var byteArrayDataToSave: Pair<String?, ByteArray?> = Pair<String?, ByteArray?>(null, null)

    fun saveData(key: String, value: UUID) {
        selectedTypeOfDataToSave = typesOfDataToSave.UUID
        uuidDataToSave = Pair(key, value)
        this.setDirty()
    }

    fun saveData(key: String, value: String) {
        val nbt: CompoundTag = CompoundTag()
        selectedTypeOfDataToSave = typesOfDataToSave.STRING
        stringDataToSave = Pair(key, value)

        this.save(nbt)

        this.setDirty()
    }

    fun saveData(key: String, value: Float) {
        selectedTypeOfDataToSave = typesOfDataToSave.FLOAT
        floatDataToSave = Pair(key, value)
        this.setDirty()
    }

    fun saveData(key: String, value: Int) {
        selectedTypeOfDataToSave = typesOfDataToSave.INT
        intDataToSave = Pair(key, value)
        this.setDirty()
    }

    fun saveData(key: String, value: Double) {
        selectedTypeOfDataToSave = typesOfDataToSave.DOUBLE
        doubleDataToSave = Pair(key, value)
        this.setDirty()
    }

    override fun save(nbtData: CompoundTag): CompoundTag {
        if (selectedTypeOfDataToSave == typesOfDataToSave.UUID) {
            if (uuidDataToSave.first != null && uuidDataToSave.second != null) {
                nbtData.putUUID(uuidDataToSave.first!!, uuidDataToSave.second!!)
            }
        } else if (selectedTypeOfDataToSave == typesOfDataToSave.STRING) {
            if (stringDataToSave.first != null && stringDataToSave.second != null) {
                nbtData.putString(stringDataToSave.first!!, stringDataToSave.second!!)
            }
        } else if (selectedTypeOfDataToSave == typesOfDataToSave.FLOAT) {
            if (floatDataToSave.first != null && floatDataToSave.second != null) {
                nbtData.putFloat(floatDataToSave.first!!, floatDataToSave.second!!)
            }
        } else if (selectedTypeOfDataToSave == typesOfDataToSave.INT) {
            if (intDataToSave.first != null && intDataToSave.second != null) {
                nbtData.putInt(intDataToSave.first!!, intDataToSave.second!!)
            }
        } else if (selectedTypeOfDataToSave == typesOfDataToSave.DOUBLE) {
            if (doubleDataToSave.first != null && doubleDataToSave.second != null) {
                nbtData.putDouble(doubleDataToSave.first!!, doubleDataToSave.second!!)
            }
        }

        return nbtData
    }
}