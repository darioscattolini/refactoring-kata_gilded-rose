package com.gildedrose

abstract class UpdatableItem protected constructor(private val item: Item) {
    val name: String
        get() = item.name

    val sellIn: Int
        get() = item.sellIn

    val quality: Int
        get() = item.quality

    protected val baseQualityVariation: Int
        get() = if (item.sellIn < 0) -2 else -1

    private val maxQuality = 50
    private val minQuality = 0
    private val positiveInfinity = Double.POSITIVE_INFINITY.toInt()

    abstract fun updateQuality()

    protected fun updateSellIn() {
        item.sellIn--
    }

    protected fun modifyQualityBy(amount: Int) {
        item.quality = when (quality + amount) {
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
                item.name.startsWith("Aged Brie", ignoreCase = true) -> AgedBrie(item)
                else -> RegularItem(item)
            }
        }
    }
}