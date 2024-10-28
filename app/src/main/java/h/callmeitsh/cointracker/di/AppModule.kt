package h.callmeitsh.cointracker.di

import h.callmeitsh.cointracker.core.data.network.HttpClientFactory
import h.callmeitsh.cointracker.crypto.data.network.RemoteCoinDataSource
import h.callmeitsh.cointracker.crypto.domain.CoinDataSource
import h.callmeitsh.cointracker.crypto.presentation.viewModel.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}
