@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.osama.foodiescompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.RestaurantMenu
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Icecream
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.compose.AsyncImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.tooling.preview.Preview

private val PrimaryYellow = Color(0xFFF5C400)
private val WarmOrange = Color(0xFFFFA726)
private val SuccessGreen = Color(0xFF35C76E)
private val ErrorRed = Color(0xFFFF6B6B)
private val AppBackground = Color(0xFFF5F5F5)
private val DarkBackground = Color(0xFF141414)
private val DarkSurface = Color(0xFF1E1E1E)
private val DarkCard = Color(0xFF262626)
private val TextSecondary = Color(0xFF7B7B7B)

private val LightColors = lightColorScheme(
    primary = PrimaryYellow,
    secondary = WarmOrange,
    tertiary = SuccessGreen,
    background = AppBackground,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color(0xFF1A1A1A),
    onSurface = Color(0xFF1A1A1A),
)

private val DarkColors = darkColorScheme(
    primary = PrimaryYellow,
    secondary = WarmOrange,
    tertiary = SuccessGreen,
    background = DarkBackground,
    surface = DarkSurface,
    surfaceContainer = DarkCard,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val prepTimeMinutes: Int = 0,
    val cookTimeMinutes: Int = 0,
    val servings: Int = 1,
    val difficulty: String = "Easy",
    val cuisine: String = "Global",
    val caloriesPerServing: Int = 0,
    val tags: List<String> = emptyList(),
    val userId: Int = 0,
    val image: String = "",
    val rating: Double = 4.5,
    val reviewCount: Int = 0,
    val mealType: List<String> = emptyList(),
) {
    val totalMinutes: Int
        get() = prepTimeMinutes + cookTimeMinutes

    val displayPrice: Double
        get() {
            val computed = 6 + (caloriesPerServing / 55.0) + (rating * 1.8) + (servings * 0.4)
            return (computed * 100).roundToInt() / 100.0
        }

    val subtitle: String
        get() = listOf(cuisine, difficulty, mealType.firstOrNull()).filterNotNull().filter { it.isNotBlank() }.joinToString(" • ")

    val etaLabel: String
        get() = if (totalMinutes > 0) "$totalMinutes min" else "25 min"
}

data class RecipeListResponse(
    val recipes: List<Recipe> = emptyList(),
    val total: Int = 0,
    val skip: Int = 0,
    val limit: Int = 0,
)

data class LocalCartItem(
    val id: Long,
    val recipeId: Int,
    val title: String,
    val subtitle: String,
    val image: String,
    val price: Double,
    val quantity: Int,
    val sizeLabel: String,
    val extras: List<String> = emptyList(),
    val note: String = "",
    val etaMinutes: Int = 20,
)

data class LocalOrder(
    val id: String,
    val title: String,
    val thumbnail: String,
    val createdAt: String,
    val status: String,
    val total: Double,
    val items: List<LocalCartItem>,
)

data class UserSession(
    val name: String,
    val email: String,
)

interface RecipeApi {
    @GET("recipes")
    suspend fun getRecipes(
        @Query("limit") limit: Int = 20,
        @Query("skip") skip: Int = 0,
        @Query("sortBy") sortBy: String? = null,
        @Query("order") order: String? = null,
    ): RecipeListResponse

    @GET("recipes/search")
    suspend fun searchRecipes(@Query("q") query: String): RecipeListResponse

    @GET("recipes/tags")
    suspend fun getTags(): List<String>

    @GET("recipes/tag/{tag}")
    suspend fun getRecipesByTag(@Path("tag") tag: String): RecipeListResponse

    @GET("recipes/{id}")
    suspend fun getRecipe(@Path("id") id: Int): Recipe
}

class FoodRepository {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private val api: RecipeApi by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    suspend fun getRecipes(limit: Int = 24, skip: Int = 0, sortBy: String = "rating", order: String = "desc"): List<Recipe> =
        api.getRecipes(limit = limit, skip = skip, sortBy = sortBy, order = order).recipes

    suspend fun searchRecipes(query: String): List<Recipe> = if (query.isBlank()) emptyList() else api.searchRecipes(query).recipes
    suspend fun getTags(): List<String> = api.getTags()
    suspend fun getRecipesByTag(tag: String): List<Recipe> = api.getRecipesByTag(tag).recipes
    suspend fun getRecipe(id: Int): Recipe = api.getRecipe(id)
}

class LocalStore(context: android.content.Context) {
    private val gson = Gson()
    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile("foodies_store.preferences_pb") },
    )

    private object Keys {
        val userName = stringPreferencesKey("user_name")
        val userEmail = stringPreferencesKey("user_email")
        val darkMode = booleanPreferencesKey("dark_mode")
        val favoriteIds = stringPreferencesKey("favorite_ids")
        val cartItems = stringPreferencesKey("cart_items")
        val orders = stringPreferencesKey("orders")
    }

    val sessionFlow: Flow<UserSession?> = dataStore.data.catch { emit(emptyPreferences()) }.map { prefs ->
        val email = prefs[Keys.userEmail]
        val name = prefs[Keys.userName]
        if (email.isNullOrBlank() || name.isNullOrBlank()) null else UserSession(name, email)
    }

    val darkModeFlow: Flow<Boolean> = dataStore.data.catch { emit(emptyPreferences()) }.map { it[Keys.darkMode] ?: false }

    val favoriteIdsFlow: Flow<Set<Int>> = dataStore.data.catch { emit(emptyPreferences()) }.map { prefs ->
        (prefs[Keys.favoriteIds] ?: "").split(',').mapNotNull { it.trim().takeIf(String::isNotBlank)?.toIntOrNull() }.toSet()
    }

    val cartFlow: Flow<List<LocalCartItem>> = dataStore.data.catch { emit(emptyPreferences()) }.map { prefs ->
        parseCartList(prefs[Keys.cartItems] ?: "[]")
    }

    val ordersFlow: Flow<List<LocalOrder>> = dataStore.data.catch { emit(emptyPreferences()) }.map { prefs ->
        parseOrderList(prefs[Keys.orders] ?: "[]")
    }

    suspend fun saveSession(name: String, email: String) {
        dataStore.edit { prefs ->
            prefs[Keys.userName] = name
            prefs[Keys.userEmail] = email
        }
    }

    suspend fun clearSession() {
        dataStore.edit { prefs ->
            prefs.remove(Keys.userName)
            prefs.remove(Keys.userEmail)
        }
    }

    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { prefs -> prefs[Keys.darkMode] = enabled }
    }

    suspend fun toggleFavorite(recipeId: Int) {
        dataStore.edit { prefs ->
            val current = (prefs[Keys.favoriteIds] ?: "").split(',').mapNotNull { it.trim().takeIf(String::isNotBlank)?.toIntOrNull() }.toMutableSet()
            if (!current.add(recipeId)) current.remove(recipeId)
            prefs[Keys.favoriteIds] = current.sorted().joinToString(",")
        }
    }

    suspend fun addToCart(item: LocalCartItem) {
        dataStore.edit { prefs ->
            val current = parseCartList(prefs[Keys.cartItems] ?: "[]").toMutableList()
            val existingIndex = current.indexOfFirst { existing ->
                existing.recipeId == item.recipeId && existing.sizeLabel == item.sizeLabel && existing.note == item.note
            }
            if (existingIndex >= 0) {
                val existing = current[existingIndex]
                current[existingIndex] = existing.copy(quantity = existing.quantity + item.quantity)
            } else {
                current.add(item)
            }
            prefs[Keys.cartItems] = gson.toJson(current)
        }
    }

    suspend fun updateCartQuantity(itemId: Long, quantity: Int) {
        dataStore.edit { prefs ->
            val current = parseCartList(prefs[Keys.cartItems] ?: "[]").mapNotNull { item ->
                when {
                    item.id != itemId -> item
                    quantity <= 0 -> null
                    else -> item.copy(quantity = quantity)
                }
            }
            prefs[Keys.cartItems] = gson.toJson(current)
        }
    }

    suspend fun removeCartItem(itemId: Long) {
        dataStore.edit { prefs ->
            val updated = parseCartList(prefs[Keys.cartItems] ?: "[]").filterNot { it.id == itemId }
            prefs[Keys.cartItems] = gson.toJson(updated)
        }
    }

    suspend fun placeOrder(cartItems: List<LocalCartItem>, total: Double) {
        if (cartItems.isEmpty()) return
        dataStore.edit { prefs ->
            val currentOrders = parseOrderList(prefs[Keys.orders] ?: "[]").toMutableList()
            val order = LocalOrder(
                id = System.currentTimeMillis().toString(),
                title = cartItems.first().title,
                thumbnail = cartItems.first().image,
                createdAt = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date()),
                status = "Preparing",
                total = total,
                items = cartItems,
            )
            currentOrders.add(0, order)
            prefs[Keys.orders] = gson.toJson(currentOrders)
            prefs[Keys.cartItems] = "[]"
        }
    }

    private fun parseCartList(raw: String): List<LocalCartItem> = runCatching {
        gson.fromJson<List<LocalCartItem>>(raw, object : TypeToken<List<LocalCartItem>>() {}.type) ?: emptyList()
    }.getOrDefault(emptyList())

    private fun parseOrderList(raw: String): List<LocalOrder> = runCatching {
        gson.fromJson<List<LocalOrder>>(raw, object : TypeToken<List<LocalOrder>>() {}.type) ?: emptyList()
    }.getOrDefault(emptyList())
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val query: String = "",
    val selectedTag: String = "All",
    val tags: List<String> = emptyList(),
    val recipes: List<Recipe> = emptyList(),
    val featuredRecipes: List<Recipe> = emptyList(),
    val deals: List<Recipe> = emptyList(),
    val exploreMore: List<Recipe> = emptyList(),
)

class HomeViewModel(private val repository: FoodRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var masterList: List<Recipe> = emptyList()
    private val cachedDetails = mutableMapOf<Int, Recipe>()
    private var searchJob: Job? = null

    init {
        refreshHome()
    }

    fun refreshHome() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = masterList.isEmpty(), isRefreshing = masterList.isNotEmpty(), errorMessage = null) }
            runCatching {
                val recipes = repository.getRecipes(limit = 24, sortBy = "rating", order = "desc")
                val tags = repository.getTags().take(8)
                masterList = recipes
                recipes.forEach { cachedDetails[it.id] = it }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        errorMessage = null,
                        tags = listOf("All") + tags,
                        recipes = recipes,
                        featuredRecipes = recipes.take(3),
                        deals = recipes.drop(3).take(6),
                        exploreMore = recipes.drop(9),
                        selectedTag = if (it.selectedTag.isBlank()) "All" else it.selectedTag,
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(isLoading = false, isRefreshing = false, errorMessage = throwable.message ?: "Could not load menu data.")
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            if (query.isBlank()) {
                if (_uiState.value.selectedTag == "All") {
                    _uiState.update { it.copy(recipes = masterList, errorMessage = null) }
                } else {
                    onTagSelected(_uiState.value.selectedTag)
                }
                return@launch
            }
            _uiState.update { it.copy(isRefreshing = true, errorMessage = null) }
            runCatching { repository.searchRecipes(query.trim()) }
                .onSuccess { results ->
                    results.forEach { cachedDetails[it.id] = it }
                    _uiState.update {
                        it.copy(
                            isRefreshing = false,
                            recipes = results,
                            featuredRecipes = results.take(3),
                            deals = results.drop(3).take(6),
                            exploreMore = results.drop(9),
                            errorMessage = if (results.isEmpty()) "No menu items matched your search." else null,
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update { it.copy(isRefreshing = false, errorMessage = throwable.message ?: "Search failed.") }
                }
        }
    }

    fun onTagSelected(tag: String) {
        _uiState.update { it.copy(selectedTag = tag, query = if (tag == "All") it.query else "") }
        if (tag == "All") {
            _uiState.update { it.copy(recipes = masterList, featuredRecipes = masterList.take(3), deals = masterList.drop(3).take(6), exploreMore = masterList.drop(9), errorMessage = null) }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true, errorMessage = null) }
            runCatching { repository.getRecipesByTag(tag) }
                .onSuccess { results ->
                    results.forEach { cachedDetails[it.id] = it }
                    _uiState.update {
                        it.copy(
                            isRefreshing = false,
                            recipes = results,
                            featuredRecipes = results.take(3),
                            deals = results.drop(3).take(6),
                            exploreMore = results.drop(9),
                            errorMessage = if (results.isEmpty()) "No items were found for $tag." else null,
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update { it.copy(isRefreshing = false, errorMessage = throwable.message ?: "Could not filter menu.") }
                }
        }
    }

    suspend fun getRecipe(recipeId: Int): Recipe? {
        cachedDetails[recipeId]?.let { return it }
        return runCatching { repository.getRecipe(recipeId) }.onSuccess { cachedDetails[recipeId] = it }.getOrNull()
    }

    fun cachedRecipes(): List<Recipe> = cachedDetails.values.toList().ifEmpty { masterList }

    class Factory(private val repository: FoodRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(repository) as T
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { FoodiesApp() }
    }
}

private object AppRoutes {
    const val Splash = "splash"
    const val Login = "login"
    const val Signup = "signup"
    const val Main = "main"
    const val Detail = "detail/{recipeId}"
    const val Cart = "cart"
    const val Checkout = "checkout"
    const val OrderSuccess = "order_success"
    const val Favorites = "favorites"
    const val Orders = "orders"
    const val Notifications = "notifications"
    const val Payment = "payment"
    const val Reviews = "reviews"
    fun detail(recipeId: Int) = "detail/$recipeId"
}

private val LocalFoodStore = androidx.compose.runtime.staticCompositionLocalOf<LocalStore> { error("LocalStore not provided") }

@Composable
fun FoodiesApp() {
    val context = LocalContext.current
    val store = remember { LocalStore(context.applicationContext) }
    val repository = remember { FoodRepository() }
    val darkMode by store.darkModeFlow.collectAsState(initial = false)
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory(repository))

    val colorScheme: ColorScheme = if (darkMode) DarkColors else LightColors
    MaterialTheme(colorScheme = colorScheme) {
        CompositionLocalProvider(LocalFoodStore provides store) {
            Surface(modifier = Modifier.fillMaxSize()) {
                NavHost(navController = navController, startDestination = AppRoutes.Splash) {
                    composable(AppRoutes.Splash) {
                        SplashScreen(store = store, onFinished = { loggedIn ->
                            navController.navigate(if (loggedIn) AppRoutes.Main else AppRoutes.Login) {
                                popUpTo(AppRoutes.Splash) { inclusive = true }
                            }
                        })
                    }
                    composable(AppRoutes.Login) {
                        LoginScreen(store = store, onLoginSuccess = {
                            navController.navigate(AppRoutes.Main) { popUpTo(AppRoutes.Login) { inclusive = true } }
                        }, onSignupClick = { navController.navigate(AppRoutes.Signup) })
                    }
                    composable(AppRoutes.Signup) {
                        SignupScreen(store = store, onSignupSuccess = {
                            navController.navigate(AppRoutes.Main) { popUpTo(AppRoutes.Login) { inclusive = true } }
                        }, onBack = { navController.popBackStack() })
                    }
                    composable(AppRoutes.Main) {
                        MainShell(
                            homeViewModel = homeViewModel,
                            onOpenDetail = { navController.navigate(AppRoutes.detail(it)) },
                            onOpenCart = { navController.navigate(AppRoutes.Cart) },
                            onOpenFavorites = { navController.navigate(AppRoutes.Favorites) },
                            onOpenOrders = { navController.navigate(AppRoutes.Orders) },
                            onOpenNotifications = { navController.navigate(AppRoutes.Notifications) },
                            onOpenPayment = { navController.navigate(AppRoutes.Payment) },
                            onOpenReviews = { navController.navigate(AppRoutes.Reviews) },
                            onLogout = {
                                navController.navigate(AppRoutes.Login) { popUpTo(AppRoutes.Main) { inclusive = true } }
                            },
                        )
                    }
                    composable(AppRoutes.Detail, arguments = listOf(navArgument("recipeId") { type = NavType.IntType })) { backStackEntry ->
                        val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
                        DetailScreen(recipeId, homeViewModel, onBack = { navController.popBackStack() }, onOpenCart = { navController.navigate(AppRoutes.Cart) })
                    }
                    composable(AppRoutes.Cart) {
                        CartScreen(homeViewModel, onBack = { navController.popBackStack() }, onOpenDetail = { navController.navigate(AppRoutes.detail(it)) }, onCheckout = { navController.navigate(AppRoutes.Checkout) })
                    }
                    composable(AppRoutes.Checkout) {
                        CheckoutScreen(onBack = { navController.popBackStack() }, onPaymentComplete = {
                            navController.navigate(AppRoutes.OrderSuccess) { popUpTo(AppRoutes.Cart) { inclusive = true } }
                        })
                    }
                    composable(AppRoutes.OrderSuccess) {
                        OrderSuccessScreen(onGoMenu = {
                            navController.navigate(AppRoutes.Main) { popUpTo(AppRoutes.Main) { inclusive = true } }
                        })
                    }
                    composable(AppRoutes.Favorites) {
                        FavoritesScreen(homeViewModel, onBack = { navController.popBackStack() }, onOpenDetail = { navController.navigate(AppRoutes.detail(it)) })
                    }
                    composable(AppRoutes.Orders) { OrdersScreen(onBack = { navController.popBackStack() }) }
                    composable(AppRoutes.Notifications) { NotificationsScreen(onBack = { navController.popBackStack() }) }
                    composable(AppRoutes.Payment) { PaymentMethodScreen(onBack = { navController.popBackStack() }) }
                    composable(AppRoutes.Reviews) { ProfileReviewsScreen(homeViewModel, onBack = { navController.popBackStack() }) }
                }
            }
        }
    }
}

@Composable
private fun SplashScreen(store: LocalStore, onFinished: (Boolean) -> Unit) {
    val session by store.sessionFlow.collectAsState(initial = null)
    LaunchedEffect(session) {
        delay(1200)
        onFinished(session != null)
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFFC49A00), Color(0xFF1B1E3D)))).padding(24.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.size(96.dp).background(Color.White.copy(alpha = 0.16f), CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.Rounded.Restaurant, contentDescription = null, tint = Color.White, modifier = Modifier.size(46.dp))
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text("Foodies", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text("현대적인 음식 주문 서비스를 경험해보세요", color = Color.White.copy(alpha = 0.85f), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(30.dp))
            CircularProgressIndicator(color = PrimaryYellow, trackColor = Color.White.copy(alpha = 0.2f))
        }
    }
}

private const val LOGIN_TOP_IMAGE = "https://cdn.dummyjson.com/recipe-images/1.webp"
private const val LOGIN_SIDE_IMAGE = "https://cdn.dummyjson.com/recipe-images/11.webp"
private const val LOGIN_BOTTOM_IMAGE = "https://cdn.dummyjson.com/recipe-images/18.webp"

@Composable
private fun LoginScreen(store: LocalStore, onLoginSuccess: () -> Unit, onSignupClick: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("demo@foodies.app") }
    var password by rememberSaveable { mutableStateOf("1234") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            AuthHero()
            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
                Text("로그인", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it; errorMessage = null },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    label = { Text("이메일 또는 사용자 이름") },
                    leadingIcon = { Icon(Icons.Outlined.MailOutline, contentDescription = null) },
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it; errorMessage = null },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    label = { Text("비밀번호") },
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility, contentDescription = null)
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )
                errorMessage?.let {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
                Spacer(modifier = Modifier.height(22.dp))
                Button(
                    onClick = {
                        when {
                            !email.contains("@") -> errorMessage = "유효한 이메일 주소를 입력해주세요."
                            password.length < 4 -> errorMessage = "비밀번호는 최소 4자 이상이어야 합니다."
                            else -> scope.launch {
                                val formattedName = email.substringBefore("@").replaceFirstChar { char ->
                                    if (char.isLowerCase()) char.titlecase() else char.toString()
                                }
                                store.saveSession(formattedName, email)
                                onLoginSuccess()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(58.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = Color.Black),
                ) { Text("로그인", fontWeight = FontWeight.Bold) }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text("계정이 없으신가요? ")
                    Text("회원가입", color = PrimaryYellow, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable(onClick = onSignupClick))
                }
            }
        }
    }
}

@Composable
private fun SignupScreen(store: LocalStore, onSignupSuccess: () -> Unit, onBack: () -> Unit) {
    var fullName by rememberSaveable { mutableStateOf("John Doe") }
    var email by rememberSaveable { mutableStateOf("john@foodies.app") }
    var phone by rememberSaveable { mutableStateOf("+92 311 1234567") }
    var password by rememberSaveable { mutableStateOf("1234") }
    var confirmPassword by rememberSaveable { mutableStateOf("1234") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
                IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null) }
            }
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text("계정 생성", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Foodies를 이용하시려면 정보를 입력해주세요.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(fullName, { fullName = it; errorMessage = null }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), singleLine = true, label = { Text("이름") }, leadingIcon = { Icon(Icons.Outlined.PersonOutline, null) })
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(email, { email = it; errorMessage = null }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), singleLine = true, label = { Text("이메일") }, leadingIcon = { Icon(Icons.Outlined.MailOutline, null) })
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(phone, { phone = it; errorMessage = null }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), singleLine = true, label = { Text("전화번호") }, leadingIcon = { Icon(Icons.Outlined.Phone, null) })
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(password, { password = it; errorMessage = null }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), singleLine = true, label = { Text("비밀번호") }, leadingIcon = { Icon(Icons.Outlined.Lock, null) }, visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation())
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(confirmPassword, { confirmPassword = it; errorMessage = null }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), singleLine = true, label = { Text("비밀번호 확인") }, leadingIcon = { Icon(Icons.Outlined.Lock, null) }, trailingIcon = { IconButton(onClick = { passwordVisible = !passwordVisible }) { Icon(if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility, null) } }, visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation())
                errorMessage?.let {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        when {
                            fullName.isBlank() -> errorMessage = "이름을 입력해주세요."
                            !email.contains("@") -> errorMessage = "유효한 이메일 주소를 입력해주세요."
                            phone.length < 8 -> errorMessage = "유효한 전화번호를 입력해주세요."
                            password.length < 4 -> errorMessage = "비밀번호는 최소 4자 이상이어야 합니다."
                            password != confirmPassword -> errorMessage = "비밀번호가 일치하지 않습니다."
                            else -> scope.launch { store.saveSession(fullName, email); onSignupSuccess() }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = Color.Black),
                ) { Text("회원가입", fontWeight = FontWeight.Bold) }
            }
        }
    }
}

@Composable
private fun AuthHero() {
    Box(modifier = Modifier.fillMaxWidth().height(290.dp).background(Brush.verticalGradient(listOf(Color(0xFFF3F3F3), Color(0xFFE8E8E8))))) {
        AsyncImage(model = LOGIN_TOP_IMAGE, contentDescription = null, modifier = Modifier.size(108.dp).align(Alignment.TopEnd).padding(top = 26.dp, end = 12.dp), contentScale = ContentScale.Crop)
        AsyncImage(model = LOGIN_SIDE_IMAGE, contentDescription = null, modifier = Modifier.size(96.dp).align(Alignment.TopStart).padding(top = 42.dp, start = 12.dp), contentScale = ContentScale.Crop)
        AsyncImage(model = LOGIN_BOTTOM_IMAGE, contentDescription = null, modifier = Modifier.size(84.dp).align(Alignment.BottomStart).padding(start = 28.dp, bottom = 20.dp), contentScale = ContentScale.Crop)
        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Rounded.Restaurant, contentDescription = null, tint = PrimaryYellow, modifier = Modifier.size(52.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Foodies", fontSize = 38.sp, fontWeight = FontWeight.Bold, color = PrimaryYellow)
        }
    }
}

private enum class MainTab { Menu, Orders, Favorites, Profile }

@Composable
private fun MainShell(
    homeViewModel: HomeViewModel,
    onOpenDetail: (Int) -> Unit,
    onOpenCart: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenOrders: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenPayment: () -> Unit,
    onOpenReviews: () -> Unit,
    onLogout: () -> Unit,
) {
    var currentTab by rememberSaveable { mutableStateOf(MainTab.Menu) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (currentTab == MainTab.Menu || currentTab == MainTab.Favorites) {
                FloatingActionButton(onClick = onOpenCart, containerColor = PrimaryYellow, contentColor = Color.Black) {
                    Icon(Icons.Rounded.ShoppingCart, contentDescription = null)
                }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = currentTab == MainTab.Menu, onClick = { currentTab = MainTab.Menu }, icon = { Icon(Icons.Outlined.RestaurantMenu, null) }, label = { Text("메뉴") })
                NavigationBarItem(selected = currentTab == MainTab.Orders, onClick = { currentTab = MainTab.Orders }, icon = { Icon(Icons.Outlined.ReceiptLong, null) }, label = { Text("주문") })
                NavigationBarItem(selected = currentTab == MainTab.Favorites, onClick = { currentTab = MainTab.Favorites }, icon = { Icon(Icons.Outlined.FavoriteBorder, null) }, label = { Text("찜") })
                NavigationBarItem(selected = currentTab == MainTab.Profile, onClick = { currentTab = MainTab.Profile }, icon = { Icon(Icons.Outlined.PersonOutline, null) }, label = { Text("프로필") })
            }
        },
    ) { innerPadding ->
        when (currentTab) {
            MainTab.Menu -> MenuScreen(homeViewModel, onOpenDetail, Modifier.fillMaxSize(), innerPadding)
            MainTab.Orders -> OrdersScreen({}, Modifier.fillMaxSize(), true, innerPadding)
            MainTab.Favorites -> FavoritesScreen(homeViewModel, {}, onOpenDetail, Modifier.fillMaxSize(), true, innerPadding)
            MainTab.Profile -> ProfileScreen(onOpenOrders, onOpenFavorites, onOpenNotifications, onOpenPayment, onOpenReviews, onLogout, Modifier.fillMaxSize(), innerPadding)
        }
    }
}

@Composable
private fun MenuScreen(homeViewModel: HomeViewModel, onOpenDetail: (Int) -> Unit, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues()) {
    val uiState by homeViewModel.uiState.collectAsState()
    val store = LocalFoodStore.current
    val favoriteIds by store.favoriteIdsFlow.collectAsState(initial = emptySet())
    val scope = rememberCoroutineScope()

    LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 110.dp)) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryYellow)
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.RestaurantMenu, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Foodies", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(14.dp))
                FoodiesSearchField(value = uiState.query, onValueChange = homeViewModel::onQueryChanged, placeholder = "검색...")
            }
        }
        item {
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 48.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = PrimaryYellow) }
            } else {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                    uiState.errorMessage?.let {
                        ErrorMessageCard(it, onRetry = homeViewModel::refreshHome)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        uiState.tags.forEach { tag ->
                            TagPill(text = if (tag == "All") "전체" else tag, selected = tag == uiState.selectedTag) { homeViewModel.onTagSelected(tag) }
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    if (uiState.query.isBlank() && uiState.selectedTag == "All") {
                        SectionHeader("인기 급상승")
                        Spacer(modifier = Modifier.height(14.dp))
                        FeaturedGrid(uiState.featuredRecipes, onOpenDetail)
                        Spacer(modifier = Modifier.height(22.dp))
                        PromoCard(uiState.deals.firstOrNull(), onOpenDetail)
                        Spacer(modifier = Modifier.height(24.dp))
                        SectionHeader("오늘의 할인")
                        Spacer(modifier = Modifier.height(14.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(uiState.deals) { recipe ->
                                DealCard(recipe, favoriteIds.contains(recipe.id), { scope.launch { store.toggleFavorite(recipe.id) } }, { onOpenDetail(recipe.id) })
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        SectionHeader("더 보기")
                        Spacer(modifier = Modifier.height(14.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            uiState.exploreMore.forEach { recipe ->
                                ExploreCard(recipe, favoriteIds.contains(recipe.id), { scope.launch { store.toggleFavorite(recipe.id) } }, { onOpenDetail(recipe.id) })
                            }
                        }
                    } else {
                        SectionHeader(if (uiState.query.isBlank()) (if (uiState.selectedTag == "All") "전체" else uiState.selectedTag) else "검색 결과")
                        Spacer(modifier = Modifier.height(14.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            uiState.recipes.forEach { recipe ->
                                ExploreCard(recipe, favoriteIds.contains(recipe.id), { scope.launch { store.toggleFavorite(recipe.id) } }, { onOpenDetail(recipe.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FeaturedGrid(recipes: List<Recipe>, onOpenDetail: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        recipes.chunked(2).forEach { rowItems ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                rowItems.forEach { recipe ->
                    Card(modifier = Modifier.weight(1f).height(140.dp).clickable { onOpenDetail(recipe.id) }, shape = RoundedCornerShape(18.dp)) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(model = recipe.image, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                            Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.72f)))))
                            Column(modifier = Modifier.align(Alignment.BottomStart).padding(12.dp)) {
                                Text(recipe.tags.firstOrNull() ?: recipe.cuisine, color = PrimaryYellow, fontWeight = FontWeight.SemiBold)
                                Text(recipe.name, color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun PromoCard(recipe: Recipe?, onOpenDetail: (Int) -> Unit) {
    if (recipe == null) return
    Card(modifier = Modifier.fillMaxWidth().clickable { onOpenDetail(recipe.id) }, shape = RoundedCornerShape(20.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(recipe.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                SmallMetaText(recipe.subtitle)
                Spacer(modifier = Modifier.height(10.dp))
                Text("Starting from", color = TextSecondary, fontSize = 12.sp)
                Text("$${String.format("%.0f", recipe.displayPrice)}", color = PrimaryYellow, fontWeight = FontWeight.Bold)
            }
            AsyncImage(model = recipe.image, contentDescription = null, modifier = Modifier.width(112.dp).height(112.dp).clip(RoundedCornerShape(18.dp)), contentScale = ContentScale.Crop)
        }
    }
}

@Composable
private fun DealCard(recipe: Recipe, isFavorite: Boolean, onFavorite: () -> Unit, onClick: () -> Unit) {
    Card(modifier = Modifier.width(240.dp).clickable(onClick = onClick), shape = RoundedCornerShape(18.dp)) {
        Column {
            Box {
                AsyncImage(model = recipe.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(150.dp), contentScale = ContentScale.Crop)
                TimeChip(recipe.etaLabel, modifier = Modifier.align(Alignment.BottomStart).padding(10.dp))
                IconButton(onClick = onFavorite, modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder, contentDescription = null, tint = if (isFavorite) PrimaryYellow else Color.White)
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(recipe.name, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), maxLines = 1)
                    RatingChip(recipe.rating)
                }
                SmallMetaText(recipe.subtitle)
            }
        }
    }
}

@Composable
private fun ExploreCard(recipe: Recipe, isFavorite: Boolean, onFavorite: () -> Unit, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(20.dp)) {
        Column {
            Box {
                AsyncImage(model = recipe.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(190.dp), contentScale = ContentScale.Crop)
                Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                    TimeChip(recipe.etaLabel)
                    IconButton(onClick = onFavorite, modifier = Modifier.background(Color.White.copy(alpha = 0.16f), CircleShape)) {
                        Icon(if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder, contentDescription = null, tint = if (isFavorite) PrimaryYellow else Color.White)
                    }
                }
                RatingChip(recipe.rating, modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp))
            }
            Column(modifier = Modifier.padding(14.dp)) {
                Text(recipe.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                SmallMetaText(recipe.subtitle)
                Spacer(modifier = Modifier.height(8.dp))
                Text("${recipe.ingredients.take(3).joinToString()}…", color = TextSecondary, maxLines = 2)
            }
        }
    }
}

@Composable
private fun ErrorMessageCard(message: String, onRetry: () -> Unit) {
    Card(shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(message, color = MaterialTheme.colorScheme.onErrorContainer, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("다시 시도하려면 탭하세요", color = MaterialTheme.colorScheme.onErrorContainer, modifier = Modifier.clickable(onClick = onRetry))
        }
    }
}

private val SIZE_OPTIONS = listOf("8\"", "10\"", "12\"")
private val EXTRA_OPTIONS = listOf("Texas Barbeque", "Char Donay")

@Composable
private fun DetailScreen(recipeId: Int, homeViewModel: HomeViewModel, onBack: () -> Unit, onOpenCart: () -> Unit) {
    val store = LocalFoodStore.current
    val favoriteIds by store.favoriteIdsFlow.collectAsState(initial = emptySet())
    val scope = rememberCoroutineScope()
    val recipeState = produceState<Recipe?>(initialValue = null, recipeId) { value = homeViewModel.getRecipe(recipeId) }
    val recipe = recipeState.value
    var quantity by rememberSaveable { mutableIntStateOf(1) }
    var selectedSize by rememberSaveable { mutableStateOf(SIZE_OPTIONS[1]) }
    val selectedExtras = remember { mutableStateListOf<String>() }
    var note by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe?.name ?: "상세 정보") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } },
                actions = {
                    IconButton(onClick = { if (recipe != null) scope.launch { store.toggleFavorite(recipe.id) } }) {
                        Icon(
                            if (recipe != null && favoriteIds.contains(recipe.id)) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = if (recipe != null && favoriteIds.contains(recipe.id)) PrimaryYellow else MaterialTheme.colorScheme.onSurface,
                        )
                    }
                },
            )
        },
        bottomBar = {
            recipe?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("$${String.format("%.2f", calculateTotal(it, selectedSize, quantity, selectedExtras.size))}", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        SmallMetaText("수량 $quantity")
                    }
                    Button(
                        onClick = {
                            val totalPrice = calculateTotal(it, selectedSize, quantity, selectedExtras.size)
                            val item = LocalCartItem(
                                id = System.currentTimeMillis(),
                                recipeId = it.id,
                                title = it.name,
                                subtitle = it.subtitle,
                                image = it.image,
                                price = totalPrice / quantity,
                                quantity = quantity,
                                sizeLabel = selectedSize,
                                extras = selectedExtras.toList(),
                                note = note,
                                etaMinutes = it.totalMinutes.takeIf { minutes -> minutes > 0 } ?: 25,
                            )
                            scope.launch {
                                store.addToCart(item)
                                onOpenCart()
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = Color.Black),
                    ) {
                        Icon(Icons.Rounded.ShoppingCart, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("장바구니 담기", fontWeight = FontWeight.Bold)
                    }
                }
            }
        },
    ) { innerPadding ->
        if (recipe == null) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = PrimaryYellow) }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding), contentPadding = PaddingValues(bottom = 110.dp)) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(model = recipe.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(270.dp), contentScale = ContentScale.Crop)
                        Box(modifier = Modifier.fillMaxWidth().height(270.dp).background(Brush.verticalGradient(listOf(Color.Black.copy(alpha = 0.55f), Color.Transparent, Color.Black.copy(alpha = 0.6f)))))
                        Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                            Text(recipe.name, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            Text(recipe.subtitle, color = Color.White.copy(alpha = 0.85f), fontSize = 13.sp)
                        }
                    }
                }
                item {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            RatingChip(recipe.rating)
                            TimeChip(recipe.etaLabel)
                        }
                        Text(recipe.ingredients.take(5).joinToString(), color = MaterialTheme.colorScheme.onSurfaceVariant)
                        SectionHeader("사이즈 선택")
                        SIZE_OPTIONS.forEach { option ->
                            SelectableRow(option, "$${String.format("%.2f", basePriceFor(recipe, option))}", selectedSize == option) { selectedSize = option }
                        }
                        SectionHeader("수량")
                        QuantitySelector(quantity, onMinus = { if (quantity > 1) quantity-- }, onPlus = { quantity++ })
                        SectionHeader("소스 추가")
                        EXTRA_OPTIONS.forEach { extra ->
                            ExtraRow(extra, if (extra.contains("Texas")) "$6" else "$8", selectedExtras.contains(extra)) {
                                if (selectedExtras.contains(extra)) selectedExtras.remove(extra) else selectedExtras.add(extra)
                            }
                        }
                        SectionHeader("요청 사항")
                        OutlinedTextField(value = note, onValueChange = { note = it }, modifier = Modifier.fillMaxWidth(), minLines = 4, shape = RoundedCornerShape(18.dp), placeholder = { Text("예: 마요네즈 빼주세요, 덜 맵게 등") })
                        SectionHeader("품절 시 선택")
                        Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
                            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text("주문에서 제외")
                                Icon(Icons.Rounded.CheckCircle, contentDescription = null, tint = PrimaryYellow)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectableRow(title: String, subtitle: String, selected: Boolean, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = onClick, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(title, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(subtitle)
                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier = Modifier.size(18.dp).clip(CircleShape).background(if (selected) PrimaryYellow else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)))
            }
        }
    }
}

@Composable
private fun QuantitySelector(quantity: Int, onMinus: () -> Unit, onPlus: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
        QuantityCard("-", onMinus)
        Text(quantity.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        QuantityCard("+", onPlus)
    }
}

@Composable
private fun QuantityCard(label: String, onClick: () -> Unit) {
    Card(onClick = onClick, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp), contentAlignment = Alignment.Center) { Text(label, fontWeight = FontWeight.Bold) }
    }
}

@Composable
private fun ExtraRow(label: String, amount: String, checked: Boolean, onCheckedChange: () -> Unit) {
    Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = checked, onCheckedChange = { onCheckedChange() })
                Text(label)
            }
            Text(amount, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

private fun basePriceFor(recipe: Recipe, sizeLabel: String): Double {
    val multiplier = when (sizeLabel) {
        "8\"" -> 1.0
        "10\"" -> 1.35
        else -> 1.7
    }
    return recipe.displayPrice * multiplier
}

private fun calculateTotal(recipe: Recipe, sizeLabel: String, quantity: Int, extras: Int): Double {
    val extraCharge = extras * 2.0
    return (basePriceFor(recipe, sizeLabel) + extraCharge) * quantity
}

@Composable
private fun CartScreen(homeViewModel: HomeViewModel, onBack: () -> Unit, onOpenDetail: (Int) -> Unit, onCheckout: () -> Unit) {
    val store = LocalFoodStore.current
    val cartItems by store.cartFlow.collectAsState(initial = emptyList())
    val state by homeViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val subtotal = cartItems.sumOf { it.price * it.quantity }

    Scaffold(topBar = {
        TopAppBar(title = { Text("장바구니") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } })
    }) { innerPadding ->
        if (cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Outlined.ShoppingBag, contentDescription = null, modifier = Modifier.size(64.dp), tint = PrimaryYellow)
                    Spacer(modifier = Modifier.height(14.dp))
                    Text("장바구니가 비어 있습니다", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    SmallMetaText("메뉴를 추가한 후 다시 확인해주세요.")
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                items(cartItems, key = { it.id }) { item ->
                    CartItemRow(item, onDecrease = { scope.launch { store.updateCartQuantity(item.id, item.quantity - 1) } }, onIncrease = { scope.launch { store.updateCartQuantity(item.id, item.quantity + 1) } }, onRemove = { scope.launch { store.removeCartItem(item.id) } })
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    SectionHeader("함께 많이 주문하는 메뉴")
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(state.deals.take(4)) { recipe ->
                            SuggestedMiniCard(recipe) { onOpenDetail(recipe.id) }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(shape = RoundedCornerShape(18.dp)) {
                        Column(modifier = Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            SummaryLine("주문 금액", "$${String.format("%.2f", subtotal)}")
                            SummaryLine("배달비", "무료")
                            SummaryLine("서비스 이용료", "$2.50")
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("총 결제 금액", fontWeight = FontWeight.Bold)
                                Text("$${String.format("%.2f", subtotal + 2.5)}", fontWeight = FontWeight.Bold, color = PrimaryYellow)
                            }
                            Button(onClick = onCheckout, modifier = Modifier.fillMaxWidth().height(54.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = Color.Black), shape = RoundedCornerShape(16.dp)) {
                                Text("주문하러 가기", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CartItemRow(item: LocalCartItem, onDecrease: () -> Unit, onIncrease: () -> Unit, onRemove: () -> Unit) {
    Card(shape = RoundedCornerShape(20.dp)) {
        Column(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(model = item.image, contentDescription = null, modifier = Modifier.size(72.dp).clip(RoundedCornerShape(16.dp)), contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(item.title, fontWeight = FontWeight.Bold)
                    SmallMetaText(item.subtitle)
                    SmallMetaText(item.sizeLabel + if (item.extras.isNotEmpty()) " • ${item.extras.joinToString()}" else "")
                }
                IconButton(onClick = onRemove) { Icon(Icons.Outlined.Close, contentDescription = null) }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("$${String.format("%.2f", item.price * item.quantity)}", fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    QuantityCard("-", onDecrease)
                    Text(item.quantity.toString(), fontWeight = FontWeight.Bold)
                    QuantityCard("+", onIncrease)
                }
            }
        }
    }
}

@Composable
private fun SuggestedMiniCard(recipe: Recipe, onClick: () -> Unit) {
    Card(modifier = Modifier.width(180.dp).clickable(onClick = onClick), shape = RoundedCornerShape(18.dp)) {
        Column {
            AsyncImage(model = recipe.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(120.dp), contentScale = ContentScale.Crop)
            Column(modifier = Modifier.padding(10.dp)) {
                Text(recipe.name, fontWeight = FontWeight.Bold, maxLines = 1)
                SmallMetaText(recipe.subtitle)
                Spacer(modifier = Modifier.height(6.dp))
                Text("$${String.format("%.0f", recipe.displayPrice)}", color = PrimaryYellow, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SummaryLine(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value)
    }
}

@Composable
private fun CheckoutScreen(onBack: () -> Unit, onPaymentComplete: () -> Unit) {
    val store = LocalFoodStore.current
    val cart by store.cartFlow.collectAsState(initial = emptyList())
    val subtotal = cart.sumOf { it.price * it.quantity }
    var instructions by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Scaffold(topBar = { TopAppBar(title = { Text("주문하기") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } }) }) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                SectionHeader("추가 요청 사항")
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(value = instructions, onValueChange = { instructions = it }, modifier = Modifier.fillMaxWidth(), minLines = 3, shape = RoundedCornerShape(18.dp), placeholder = { Text("예: 생일 파티용 음식입니다") }, leadingIcon = { Icon(Icons.Outlined.EditNote, null) })
            }
            item {
                Card(shape = RoundedCornerShape(22.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        SectionHeader("결제 수단")
                        Box(modifier = Modifier.fillMaxWidth().background(Brush.horizontalGradient(listOf(Color(0xFFD8B03C), Color(0xFF7A67FF))), RoundedCornerShape(20.dp)).padding(18.dp)) {
                            Column {
                                Icon(Icons.Outlined.CreditCard, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
                                Spacer(modifier = Modifier.height(18.dp))
                                Text("John Doe", color = Color.White, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("•••• 4871", color = Color.White.copy(alpha = 0.88f))
                            }
                        }
                        SectionHeader("주문 요약")
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("상품 (${cart.sumOf { it.quantity }})"); Text("$${String.format("%.2f", subtotal)}") }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("서비스 이용료"); Text("$2.50") }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("배달비"); Text("무료") }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("총 결제 금액", fontWeight = FontWeight.Bold); Text("$${String.format("%.2f", subtotal + 2.5)}", fontWeight = FontWeight.Bold, color = PrimaryYellow) }
                        Button(onClick = { scope.launch { store.placeOrder(cart, subtotal + 2.5); onPaymentComplete() } }, enabled = cart.isNotEmpty(), modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = Color.Black)) {
                            Text("결제하기", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OrdersScreen(onBack: () -> Unit, modifier: Modifier = Modifier, inlineMode: Boolean = false, contentPadding: PaddingValues = PaddingValues()) {
    val store = LocalFoodStore.current
    val orders by store.ordersFlow.collectAsState(initial = emptyList())

    val content: @Composable () -> Unit = {
        if (orders.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(contentPadding), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Outlined.ReceiptLong, contentDescription = null, modifier = Modifier.size(64.dp), tint = PrimaryYellow)
                    Text("주문 내역이 없습니다", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    SmallMetaText("첫 주문을 완료하면 내역이 여기에 표시됩니다.")
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(contentPadding), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                items(orders) { order -> OrderCard(order) }
            }
        }
    }

    if (inlineMode) {
        content()
    } else {
        Scaffold(modifier = modifier, topBar = { TopAppBar(title = { Text("내 주문 내역") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } }) }) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) { content() }
        }
    }
}

@Composable
private fun OrderCard(order: LocalOrder) {
    Card(shape = RoundedCornerShape(20.dp)) {
        Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AsyncImage(model = order.thumbnail, contentDescription = null, modifier = Modifier.fillMaxWidth().height(150.dp), contentScale = ContentScale.Crop)
            Text(order.title, fontWeight = FontWeight.Bold)
            SmallMetaText(order.createdAt)
            SmallMetaText("${order.items.sumOf { it.quantity }}개의 상품")
            Surface(color = when (order.status) {
                "Completed" -> SuccessGreen.copy(alpha = 0.16f)
                "Cancelled" -> ErrorRed.copy(alpha = 0.16f)
                else -> PrimaryYellow.copy(alpha = 0.18f)
            }, shape = RoundedCornerShape(50)) {
                Text(when(order.status) {
                    "Completed" -> "완료됨"
                    "Cancelled" -> "취소됨"
                    else -> order.status
                }, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), color = when (order.status) {
                    "Completed" -> SuccessGreen
                    "Cancelled" -> ErrorRed
                    else -> Color(0xFFD28A00)
                }, fontWeight = FontWeight.SemiBold)
            }
            Text("$${String.format("%.2f", order.total)}", fontWeight = FontWeight.Bold, color = PrimaryYellow)
        }
    }
}

@Composable
private fun FavoritesScreen(homeViewModel: HomeViewModel, onBack: () -> Unit, onOpenDetail: (Int) -> Unit, modifier: Modifier = Modifier, inlineMode: Boolean = false, contentPadding: PaddingValues = PaddingValues()) {
    val store = LocalFoodStore.current
    val favoriteIds by store.favoriteIdsFlow.collectAsState(initial = emptySet())
    val favorites = homeViewModel.cachedRecipes().filter { favoriteIds.contains(it.id) }.sortedBy { it.name }

    val content: @Composable () -> Unit = {
        if (favorites.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(contentPadding), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
                    Text("찜한 메뉴가 없습니다", fontWeight = FontWeight.Bold)
                    Text("메뉴의 하트 아이콘을 눌러 찜해보세요.")
                }
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.fillMaxSize().padding(contentPadding)) {
                items(favorites) { recipe -> ExploreCard(recipe, true, {}, { onOpenDetail(recipe.id) }) }
            }
        }
    }

    if (inlineMode) {
        content()
    } else {
        Scaffold(modifier = modifier, topBar = { TopAppBar(title = { Text("찜한 목록") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } }) }) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) { content() }
        }
    }
}

@Composable
private fun ProfileScreen(onOpenOrders: () -> Unit, onOpenFavorites: () -> Unit, onOpenNotifications: () -> Unit, onOpenPayment: () -> Unit, onOpenReviews: () -> Unit, onLogout: () -> Unit, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues()) {
    val store = LocalFoodStore.current
    val session by store.sessionFlow.collectAsState(initial = null)
    val darkMode by store.darkModeFlow.collectAsState(initial = false)
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)

    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.size(78.dp).background(PrimaryYellow.copy(alpha = 0.2f), CircleShape), contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.PersonOutline, contentDescription = null, tint = PrimaryYellow, modifier = Modifier.size(42.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(session?.name ?: "John Doe", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                SmallMetaText(session?.email ?: "london.uk@example.com")
            }
        }
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SettingsSectionTitle("계정 설정")
                ProfileRow(Icons.Outlined.FavoriteBorder, "찜한 목록", onOpenFavorites)
                ProfileRow(Icons.Outlined.ReceiptLong, "내 주문 내역", onOpenOrders)
                ProfileRow(Icons.Outlined.CreditCard, "결제 수단", onOpenPayment)
                ProfileRow(Icons.Outlined.StarOutline, "내 리뷰", onOpenReviews)
                SettingsSectionTitle("일반 설정")
                ProfileRow(Icons.Outlined.NotificationsNone, "알림", onOpenNotifications)
                Card(shape = RoundedCornerShape(18.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.Info, contentDescription = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("다크 모드", modifier = Modifier.weight(1f))
                        Switch(checked = darkMode, onCheckedChange = { enabled -> scope.launch { store.setDarkMode(enabled) } })
                    }
                }
                ProfileRow(Icons.Outlined.Logout, "로그아웃") {
                    scope.launch { store.clearSession(); onLogout() }
                }
            }
        }
    }
}

@Composable
private fun SettingsSectionTitle(title: String) {
    Text(title, modifier = Modifier.padding(start = 4.dp, top = 8.dp), color = PrimaryYellow, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun ProfileRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(18.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text(title, modifier = Modifier.weight(1f))
            Text(">", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun NotificationsScreen(onBack: () -> Unit) {
    val notificationItems = listOf(
        "주문하신 음식이 준비되었습니다" to "2분 전",
        "음식이 배달 중입니다" to "50분 전",
        "주문이 성공적으로 접수되었습니다" to "1시간 전",
        "전화번호를 인증해주세요" to "5일 전",
        "Foodies에 오신 것을 환영합니다" to "1주일 전",
    )
    Scaffold(topBar = { TopAppBar(title = { Text("알림") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } }) }) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(notificationItems) { item ->
                Card { Column(modifier = Modifier.padding(16.dp)) { Text(item.first, fontWeight = FontWeight.SemiBold); SmallMetaText(item.second) } }
            }
        }
    }
}

@Composable
private fun PaymentMethodScreen(onBack: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("결제 수단") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } }) }) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Box(modifier = Modifier.fillMaxWidth().background(Brush.horizontalGradient(listOf(Color(0xFF4563D1), Color(0xFFD1B53A))), RoundedCornerShape(22.dp)).padding(20.dp)) {
                Column {
                    Icon(Icons.Outlined.CreditCard, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
                    Text("홍길동", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 18.dp))
                    Text("•••• 2459", color = Color.White.copy(alpha = 0.9f))
                }
            }
            OutlinedTextField(value = "홍길동", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("카드 소유자 이름") })
            OutlinedTextField(value = "1234 1234 1234 4871", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("카드 번호") })
            OutlinedTextField(value = "02/2027", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("만료일") })
            OutlinedTextField(value = "123", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("CVC") })
            Button(onClick = {}, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = Color.Black), shape = RoundedCornerShape(18.dp)) {
                Text("주문 취소", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun ProfileReviewsScreen(homeViewModel: HomeViewModel, onBack: () -> Unit) {
    val state by homeViewModel.uiState.collectAsState()
    val items = state.recipes.take(3)
    Scaffold(topBar = { TopAppBar(title = { Text("내 리뷰") }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) } }) }) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(items) { recipe ->
                Card(shape = RoundedCornerShape(20.dp)) {
                    Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        AsyncImage(model = recipe.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(180.dp), contentScale = ContentScale.Crop)
                        Text(recipe.name, fontWeight = FontWeight.Bold)
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            repeat(5) { index -> Icon(Icons.Rounded.Star, contentDescription = null, tint = if (index < recipe.rating.toInt()) PrimaryYellow else Color.LightGray) }
                        }
                        Text("UI가 매우 깔끔하고 음식 카드 레이아웃이 훌륭합니다. 주문 과정도 따라하기 쉽네요.", color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderSuccessScreen(onGoMenu: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(220.dp).background(Color(0xFFF4F4F4), CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.Rounded.Icecream, contentDescription = null, tint = PrimaryYellow, modifier = Modifier.size(118.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("주문이 완료되었습니다", textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("최대한 빨리 준비해 드리겠습니다", textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = onGoMenu, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = Color.Black)) {
                Text("메뉴로 이동", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun FoodiesSearchField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, placeholder: String = "Search...") {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        singleLine = true,
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
    )
}

@Composable
private fun SectionHeader(title: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun RatingChip(rating: Double, modifier: Modifier = Modifier) {
    Surface(modifier = modifier, shape = RoundedCornerShape(20.dp), color = Color.White.copy(alpha = 0.92f)) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Star, contentDescription = null, tint = PrimaryYellow, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(String.format("%.1f", rating), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun TimeChip(label: String, modifier: Modifier = Modifier) {
    Surface(modifier = modifier, shape = RoundedCornerShape(20.dp), color = Color.Black.copy(alpha = 0.64f)) {
        Text(label, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 12.sp, color = Color.White)
    }
}

@Composable
private fun TagPill(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = if (selected) PrimaryYellow else MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(22.dp))
            .border(width = if (selected) 0.dp else 1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), shape = RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Text(text, color = if (selected) Color.Black else TextSecondary, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal)
    }
}

@Composable
private fun SmallMetaText(text: String) {
    Text(text, color = TextSecondary, fontSize = 12.sp)
}
