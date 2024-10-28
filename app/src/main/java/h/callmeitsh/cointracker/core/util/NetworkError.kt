package h.callmeitsh.cointracker.core.util

enum class NetworkError : Error {
    NO_INTERNET_CONNECTION,
    TIMEOUT,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN,
    NOT_FOUND
}