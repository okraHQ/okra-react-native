package com.reactlibrary.banks

import com.reactlibrary.interfaces.BankServices
import com.reactlibrary.models.Enums
import com.reactlibrary.models.HoverStrategy


class FidelityBank : BankServices {
    override fun getActionCount(): Int {
        return 2
    }

    override fun getIndex(): Int {
        return index
    }

    override fun setIndex(index: Int): Int {
        Companion.index = index
        return Companion.index
    }

    @Throws(Exception::class)
    override fun getActionByIndex(index: Int): HoverStrategy {
        return when (index) {
            1 -> accounts
            2 -> accountBalance
            else -> accounts
        }
    }

    @Throws(Exception::class)
    override fun getNextAction(): HoverStrategy {
        if (index >= actionCount) {
            index = 0
        }
        return getActionByIndex(index + 1)
    }

    override fun hasNext(): Boolean {
        return index < actionCount
    }

    @Throws(Exception::class)
    override fun getBvn(): HoverStrategy {
        throw Exception("Not implemented")
    }

    @Throws(Exception::class)
    override fun getAccounts(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "d85c09f7",
                "Fidelity Bank",
                "Fetching accounts",
                0
        )
        hoverStrategy.id = "accounts"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.isFirstAction = true
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    override fun getAccountBalance(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "f6ccd22e",
                "Fidelity Bank",
                "Fetching account balance",
                10000
        )
        hoverStrategy.id = "balance"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun getTransactions(): HoverStrategy {
        throw Exception("Not implemented")
    }

    @Throws(Exception::class)
    override fun confirmPayment(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "f6ccd22e",
                "Fidelity Bank",
                "Verifying Payment",
                10000
        )
        hoverStrategy.id = "verify-payment"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun makePayment(isInternal: Boolean, hasMultipleAccounts: Boolean): HoverStrategy {
        val actionId = if(hasMultipleAccounts) "d4a13fcc" else "68646f9d"

        val hoverStrategy = HoverStrategy(
                actionId,
                "Fidelity Bank",
                "Fetching account balance",
                10000
        )
        hoverStrategy.id = "payment"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    companion object {
       private var index = 1
    }
}