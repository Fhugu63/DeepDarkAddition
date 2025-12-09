package ru.deepdarkaddition.engine

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.saveddata.SavedData
import net.minecraft.world.level.storage.DimensionDataStorage

class DeepDarkAdditionSaveData() : SavedData() {
    var diarys: String = ""

    override fun save(pCompoundTag: CompoundTag): CompoundTag {
        pCompoundTag.putString("colectedDiarys", diarys)
        return pCompoundTag
    }

    companion object {
        const val DATA_NAME = "deep_dark_addition_data"

        fun load(nbt: CompoundTag): DeepDarkAdditionSaveData {
            val data = DeepDarkAdditionSaveData()
            data.diarys = nbt.getString("colectedDiarys")
            return data
        }

        fun getSavedData(sLevel: ServerLevel): DeepDarkAdditionSaveData {
            val storage = sLevel.dataStorage

            return storage.computeIfAbsent(::load, ::DeepDarkAdditionSaveData, DATA_NAME)
        }
    }
}