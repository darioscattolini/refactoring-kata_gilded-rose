package com.gildedrose

abstract class UpdatableItem protected constructor(item: Item) : Item(item.name, item.sellIn, item.quality) {
    abstract fun updateQuality()

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