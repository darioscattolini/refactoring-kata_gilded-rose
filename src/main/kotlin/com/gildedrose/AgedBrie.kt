package com.gildedrose

class AgedBrie(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        sellIn--
    }

}
