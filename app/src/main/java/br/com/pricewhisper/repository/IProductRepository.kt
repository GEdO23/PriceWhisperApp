package br.com.pricewhisper.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import br.com.pricewhisper.models.Product

interface IProductRepository {
    /**
     * Collects the [Product] List from the Firebase Realtime Database
     * @param productList The [Product] List that will be replaced by the new [Product] List
     * @see Product
     */
    fun getProductListFromFirebase(
        productList: SnapshotStateList<Product>
    )

    /**
     * Gets a [Product] from the Firebase Realtime Database [Product] List by its ID
     * @param id The identifier of the [Product] to be found
     * @see Product
     */
    fun getProductFromFirebaseById(
        id: String
    )

    /**
     * Saves a new [Product] on the Firebase Realtime Database [Product] List
     * @param product The [Product] to be saved
     * @see Product
     */
    fun postProductToFirebase(
        product: Product
    )

    /**
     * Updates a [Product] in the Firebase Realtime Database [Product] List
     * @param id The identifier of the [Product] to be replaced
     * @param newProduct The new [Product] to replace the old [Product]
     * @see Product
     */
    fun updateProductInFirebase(
        id: String,
        newProduct: Product
    )

    /**
     * Deletes a [Product] in the Firebase Realtime Database [Product] List
     * @param id The identifier of the [Product] to be deleted
     * @see Product
     */
    fun deleteProductInFirebase(
        id: String
    )
}