package ru.deepdarkaddition.engine

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.saveddata.SavedData
import net.minecraft.world.level.storage.DimensionDataStorage

class DeepDarkAdditionSaveData() : WorldDataHandler() {
    override val DATA_NAME = "deep_dark_addition_data"

    var diarys: String = ""

    companion object {
        fun load(nbt: CompoundTag): DeepDarkAdditionSaveData {
            val data = DeepDarkAdditionSaveData()
            data.diarys = nbt.getString("colectedDiarys")
            return data
        }

        fun getSavedData(sLevel: ServerLevel): DeepDarkAdditionSaveData {
            val storage = sLevel.dataStorage

            return storage.computeIfAbsent(::load, ::DeepDarkAdditionSaveData, DeepDarkAdditionSaveData().DATA_NAME)
        }
    }
}