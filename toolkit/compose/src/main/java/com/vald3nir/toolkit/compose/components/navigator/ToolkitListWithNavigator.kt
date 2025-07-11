package com.vald3nir.toolkit.compose.components.navigator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vald3nir.toolkit.compose.R


data class Account(
    val id: Int,
    val title: String,
    val icon: Int
)


@Composable
fun AccountListComponent(callback: (Account) -> Unit) {
    val accounts = listOf(
        Account(1, "Conta de Luz", R.drawable.ic_android),
        Account(2, "Conta de Água", R.drawable.ic_android),
        Account(3, "Internet", R.drawable.ic_android)
    )
    LazyColumn {
        itemsIndexed(accounts) { index, item ->
            AccountItemListComponent(item, callback)
        }
    }
}


@Composable
fun AccountItemListComponent(account: Account, callback: (Account) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { callback(account) }
    ) {
        Icon(
            painter = painterResource(id = account.icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = account.title,
            modifier = Modifier.weight(1f)
        )
        Button(onClick = { callback(account) }) {
            Text("Pagar")
        }
    }
}

sealed class Screen(val route: String) {
    object AccountScreen : Screen("lista_de_contas")
    object PaymentScreen : Screen("pagamento")
}

@Preview
@Composable
fun AccountScreenDemo() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.AccountScreen.route) {

        composable(Screen.AccountScreen.route) {
            AccountListComponent { account ->
                navController.navigate(Screen.PaymentScreen.route)
            }
        }

        composable(Screen.PaymentScreen.route) {
            PaymentScreenDemo()
        }
    }
}


@Composable
fun PaymentScreenDemo() {
    // Implementar a UI para pagamento da conta
    Text(text = "Tela de Pagamento")
}

