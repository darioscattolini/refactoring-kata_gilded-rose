package com.gildedrose

abstract class UpdatableItem protected constructor(item: Item) : Item(item.name, item.sellIn, item.quality) {
    private val maxQuality = 50
    private val minQuality = 0
    private val positiveInfinity = Double.POSITIVE_INFINITY.toInt()

    abstract fun updateQuality()

    public fun modifyQualityBy(amount: Int) {
        quality = when (quality + amount) {
            in maxQuality..positiveInfinity -> maxQuality
            in minQuality until maxQuality -> quality + amount
            else -> minQuality
        }
    }

    companion object {
        fun fromItem(item: Item): UpdatableItem {
            return when {
                item.name.startsWith("Sulfuras", ignoreCase = true) -> Sulfuras(item)
                item.name.startsWith("Conjured", ignoreCase = true) -> ConjuredItem(item)
                item.name.startsWith("Backstage Passes", ignoreCase = true) -> BackstagePasses(item)
                item.name.startsWith("Aged Brie", ignoreCase = false) -> AgedBrie(item)
                else -> RegularItem(item)
            }
        }
    }
}