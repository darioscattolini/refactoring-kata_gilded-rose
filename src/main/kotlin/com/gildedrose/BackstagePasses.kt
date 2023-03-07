package com.gildedrose

class BackstagePasses(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        sellIn--

        val qualityVariationAmount = when (sellIn) {
            in 11..Double.POSITIVE_INFINITY.toInt() -> 1
            in 6..10 -> 2
            in 0..5 -> 3
            else -> -quality
        }

        modifyQualityBy(qualityVariationAmount)
    }

}
