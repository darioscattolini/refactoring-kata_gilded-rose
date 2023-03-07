package com.gildedrose

class GildedRose(items: Array<Item>) {
    val items = items.map { UpdatableItem.fromItem(it) }

    fun updateQuality() {
        items.forEach { it.updateQuality() }
    }
}

