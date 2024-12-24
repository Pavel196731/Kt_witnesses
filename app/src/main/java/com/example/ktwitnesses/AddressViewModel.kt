import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ktwitnesses.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddressViewModel : ViewModel() {
    private val _addresses = MutableStateFlow<List<String>>(emptyList())
    val addresses: StateFlow<List<String>> = _addresses

    private val _selectedAddress = MutableStateFlow<String?>(null)
    val selectedAddress: StateFlow<String?> = _selectedAddress

    fun loadAddresses(context: Context) {
        val loadedAddresses = context.resources.getStringArray(R.array.addresses).toList()
        _addresses.value = loadedAddresses
        _selectedAddress.value = loadedAddresses.firstOrNull()
    }

    fun selectAddress(address: String) {
        _selectedAddress.value = address
    }
}