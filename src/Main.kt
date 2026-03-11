// kotlin finance tracker
// console program to manage income and expenses

import java.util.Scanner

// data class to store one transaction
data class Transaction(
    val id: Int,
    val type: String,
    val description: String,
    val amount: Double
)

// class that controls all finance operations
class FinanceTracker {

    private val transactions = mutableListOf<Transaction>()
    private var nextId = 1

    // this function adds a new transaction to the list
    fun addTransaction(type: String, description: String, amount: Double) {
        val transaction = Transaction(nextId, type, description, amount)
        transactions.add(transaction)
        nextId++
        println("transaction added successfully.\n")
    }

    // this function shows all saved transactions
    fun viewTransactions() {
        if (transactions.isEmpty()) {
            println("no transactions available.\n")
        } else {
            println("\n--- transaction list ---")
            for (t in transactions) {
                println("id: ${t.id} | ${t.type} | ${t.description} | $${t.amount}")
            }
            println()
        }
    }

    // this function calculates the current balance
    fun calculateBalance(): Double {
        var balance = 0.0

        for (t in transactions) {
            when (t.type.lowercase()) {
                "income" -> balance += t.amount
                "expense" -> balance -= t.amount
            }
        }

        return balance
    }

    // this function deletes a transaction by id
    fun deleteTransaction(id: Int) {
        val removed = transactions.removeIf { it.id == id }

        if (removed) {
            println("transaction deleted successfully.\n")
        } else {
            println("transaction not found.\n")
        }
    }
}

// main function that runs the program
fun main() {

    val scanner = Scanner(System.`in`)
    val tracker = FinanceTracker()
    var running = true

    println("welcome to kotlin finance tracker")

    while (running) {

        println("""
            |1. add income
            |2. add expense
            |3. view transactions
            |4. view balance
            |5. delete transaction
            |6. exit
        """.trimMargin())

        print("choose an option: ")

        when (scanner.nextLine()) {

            "1" -> {
                print("enter description: ")
                val description = scanner.nextLine()

                print("enter amount: ")
                val amount = scanner.nextLine().toDouble()

                tracker.addTransaction("income", description, amount)
            }

            "2" -> {
                print("enter description: ")
                val description = scanner.nextLine()

                print("enter amount: ")
                val amount = scanner.nextLine().toDouble()

                tracker.addTransaction("expense", description, amount)
            }

            "3" -> tracker.viewTransactions()

            "4" -> {
                val balance = tracker.calculateBalance()
                println("current balance: $$balance\n")
            }

            "5" -> {
                print("enter transaction id: ")
                val id = scanner.nextLine().toInt()
                tracker.deleteTransaction(id)
            }

            "6" -> {
                println("exiting program...")
                running = false
            }

            else -> println("invalid option.\n")
        }
    }

    println("program finished.")
}