# ClothesRetailer
An app that displays clothing items with a wish list and checkout basket.

Language: Kotlin
Libraries: Androidx(Jetpack), Coroutines, data binding, LiveData, ViewModels, Room among others.
Min SDK: 19
Compile SDK: 29

This app uses the MVVM app architecture pattern throughtout. ViewModels with data binding and LiveData
allow changes in the underlying Room data to be observed. UI then is updated through reactive programming.
Coroutines are used in addition to LiveData for reactive programming.

I decided not to use RxJava as I wanted to come to terms more with Kotlin Coroutines which seem to be faster to 
implement.

The app launches a launch activity (appcompat) which is the splash activty. This screen insures that all products are downloaded
at the beginning before the home nav/fragents are displayed. I thought it better to cache the data with a splash instead of displaying a loading
 indicator on the home screen.
 
 The fragment/adapters use data binding throughout, though not always consistently. I could have passed the addToShoppingCart call
 in the product/wishlist fragments in the xml file, but I ran into errors I could not solve fast enough for my patience.
 I instead implemented the calls in the initialization method of the respective view holders.


