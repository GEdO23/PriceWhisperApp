package br.com.pricewhisper.repository

import br.com.pricewhisper.models.Product
import java.io.IOException

interface IProductRepository {
    /**
     * Collects the [Product] List from the Firebase Realtime Database
     * @param onRequestSuccess What should happen if the request succeeds
     * @param onRequestFailure What should happen if the request fails
     * @see Product
     */
    fun getProductListFromFirebase(
        onRequestSuccess: (products: HashMap<String, Product?>?) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    )

    /**
     * Gets a [Product] from the Firebase Realtime Database [Product] List by its ID
     * @param id The identifier of the [Product] to be found
     * @param onRequestSuccess What should happen if the request succeeds
     * @param onRequestFailure What should happen if the request fails
     * @see Product
     */
    fun getProductFromFirebaseById(
        id: String,
        onRequestSuccess: (productFound: Product?) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    )

    /**
     * Saves a new [Product] on the Firebase Realtime Database [Product] List
     * @param product The [Product] to be saved
     * @param onRequestSuccess What should happen if the request succeeds
     * @param onRequestFailure What should happen if the request fails
     * @see Product
     */
    fun postProductToFirebase(
        product: Product,
        onRequestSuccess: (productSaved: Product) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    )

    /**
     * Updates a [Product] in the Firebase Realtime Database [Product] List
     * @param id The identifier of the [Product] to be replaced
     * @param newProduct The new [Product] to replace the old [Product]
     * @param onRequestSuccess What should happen if the request succeeds
     * @param onRequestFailure What should happen if the request fails
     * @see Product
     */
    fun updateProductInFirebase(
        id: String,
        newProduct: Product,
        onRequestSuccess: (newProduct: Product?) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    )

    /**
     * Deletes a [Product] in the Firebase Realtime Database [Product] List
     * @param id The identifier of the [Product] to be deleted
     * @param onRequestSuccess What should happen if the request succeeds
     * @param onRequestFailure What should happen if the request fails
     * @see Product
     */
    fun deleteProductInFirebase(
        id: String,
        onRequestSuccess: (productDeleted: Product) -> Unit,
        onRequestFailure: (e: IOException) -> Unit
    )
}