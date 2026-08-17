package com.example.p2plending.data

/** Logged-in investor profile. */
data class User(
    val name: String,
    val email: String,
    val balance: Long,
    val totalInvested: Long,
    val earnings: Long,
    val activeLoans: Int
)

/** A loan offer that an investor can fund. */
data class Loan(
    val id: String,
    val borrower: String,
    val purpose: String,
    val amount: Long,
    val fundedPercent: Int,
    val interestRate: Double,
    val tenorMonths: Int,
    val grade: String
)

/**
 * In-memory data source. Replace with a real API/database later.
 * Demo credentials: demo@lendly.id / lendly123
 */
object MockData {

    private const val DEMO_EMAIL = "demo@lendly.id"
    private const val DEMO_PASSWORD = "lendly123"

    val demoUser = User(
        name = "Insani Muchlis",
        email = DEMO_EMAIL,
        balance = 12_450_000,
        totalInvested = 48_000_000,
        earnings = 3_920_000,
        activeLoans = 7
    )

    val loans = listOf(
        Loan("LN-2481", "Warung Bu Siti", "Working capital for grocery stock", 15_000_000, 82, 16.5, 6, "A"),
        Loan("LN-2482", "Rizky Print Shop", "Buy a second digital printer", 25_000_000, 45, 18.0, 12, "B"),
        Loan("LN-2483", "Kopi Senja", "Open a second coffee outlet", 40_000_000, 68, 19.5, 12, "B"),
        Loan("LN-2484", "Bengkel Jaya Motor", "Spare part inventory", 10_000_000, 95, 15.0, 3, "A"),
        Loan("LN-2485", "Tani Makmur", "Seed and fertilizer for harvest", 8_500_000, 30, 21.0, 6, "C"),
        Loan("LN-2486", "Laundry Bersih", "Two new washing machines", 12_000_000, 57, 17.5, 9, "B")
    )

    /** Returns the user when credentials match, otherwise null. */
    fun login(email: String, password: String): User? =
        if (email.trim().equals(DEMO_EMAIL, ignoreCase = true) && password == DEMO_PASSWORD) {
            demoUser
        } else {
            null
        }
}

/** Formats a rupiah amount, e.g. 12450000 -> "Rp12.450.000". */
fun Long.toRupiah(): String {
    val digits = toString()
    val grouped = StringBuilder()
    for ((index, char) in digits.withIndex()) {
        if (index > 0 && (digits.length - index) % 3 == 0) grouped.append('.')
        grouped.append(char)
    }
    return "Rp$grouped"
}
